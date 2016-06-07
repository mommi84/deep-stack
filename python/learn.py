import pandas as pd

# Import various modules for string cleaning
from bs4 import BeautifulSoup
import re
from nltk.corpus import stopwords

import nltk.data

def read_data():
    # Read data from file
    train = pd.read_csv( "question_snippet.clean.tsv", header=0, delimiter="\t", quoting=3 )

    # Verify the number of snippets that were read
    print "Read %d train snippets\n" % train["snippet"].size
    
    return(train)

# Define a function to split a snippet into parsed sentences
def snippet_to_sentences(snippet, remove_stopwords=False):
    # Function to split a review into parsed sentences. Returns a 
    # list of sentences, where each sentence is a list of words
    #
    # 1. Use the NLTK tokenizer to split the snippet into sentences
    raw_sentences = snippet.strip().split(";") # tokenizer.tokenize(review.strip())
    #
    # 2. Loop over each sentence
    sentences = []
    for raw_sentence in raw_sentences:
        # If a sentence is empty, skip it
        if len(raw_sentence) > 0:
            # Otherwise, call snippet_to_wordlist to get a list of words
            sentences.append( snippet_to_wordlist( raw_sentence, \
              remove_stopwords ))
    #
    # Return the list of sentences (each sentence is a list of words,
    # so this returns a list of lists
    return sentences
    
def snippet_to_wordlist(review, remove_stopwords=False):
    # Function to convert a document to a sequence of words,
    # optionally removing stop words.  Returns a list of words.
    #
    # 1. Remove CSnippex header
    review_text = re.sub('/\*([^*]|(\*+([^*/])))*\*+/', '', review)

    #  
    # 2. Remove non-letters
    review_text = review_text.replace("+", " exprplus ")
    review_text = review_text.replace("=", " exprequals ")
    review_text = re.sub("[^a-zA-Z0-9]"," ", review_text)
    #
    # 3. Convert words to lower case and split them
    words = review_text.split()
    # words = review_text.lower().split()
    #
    # 4. Optionally remove stop words (false by default)
    if remove_stopwords:
        stops = set(stopwords.words("english"))
        words = [w for w in words if not w in stops]
    #
    # 5. Return a list of words
    return(words)

################ MAIN CODE ################

train = read_data()
sentences = []  # Initialize an empty list of sentences

print "Parsing sentences from training set"
for snippet in train["snippet"]:
    sentences += snippet_to_sentences(snippet)

# with open("sentences.txt", 'w') as thefile:
#     for item in sentences:
#         thefile.write("%s\n" % item)

# Import the built-in logging module and configure it so that Word2Vec 
# creates nice output messages
import logging
logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s',\
    level=logging.INFO)

# Set values for various parameters
num_features = 300    # Word vector dimensionality                      
min_word_count = 40   # Minimum word count                        
num_workers = 32       # Number of threads to run in parallel
context = 10          # Context window size                                                                                    
downsampling = 1e-3   # Downsample setting for frequent words

# Initialize and train the model (this will take some time)
from gensim.models import word2vec
print "Training model..."
model = word2vec.Word2Vec(sentences, workers=num_workers, \
            size=num_features, min_count = min_word_count, \
            window = context, sample = downsampling)

# If you don't plan to train the model any further, calling 
# init_sims will make the model much more memory-efficient.
model.init_sims(replace=True)

# It can be helpful to create a meaningful model name and 
# save the model for later use. You can load it later using Word2Vec.load()
model_name = "300features_40minwords_10context"
model.save(model_name)