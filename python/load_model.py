from gensim.models import word2vec

model = word2vec.Word2Vec.load("300features_40minwords_10context")

model.n_similarity("i exprplus exprplus".split(), "i exprequals i exprplus 1".split())