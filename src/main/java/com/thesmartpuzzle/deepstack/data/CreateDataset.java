package com.thesmartpuzzle.deepstack.data;

import hk.ust.cse.csnippex.data.CompilationUnit;
import hk.ust.cse.csnippex.data.Result;
import hk.ust.cse.csnippex.data.persistent.GSonConversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.xml.sax.Attributes;

import com.thesmartpuzzle.deepstack.xml.Row;
import com.thesmartpuzzle.deepstack.xml.RowProcessor;
import com.thesmartpuzzle.deepstack.xml.XMLManager;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class CreateDataset {

	public static final String CSNIPPEX_DATA = "data/csnippex/";
	public static final String STACK_OVERFLOW_DATA = "data/Posts.xml";

	public static void main(String[] args) throws FileNotFoundException {

		// collect answer IDs

		final HashMap<Long, Instance> answerIDs = new HashMap<Long, Instance>();

		ArrayList<Result> results = new GSonConversion<Result>()
				.readFolder(CSNIPPEX_DATA);
		for (Result r : results) {
			Instance i = new Instance();
			i.setAnswerID(r.answerId);
			StringBuilder sb = new StringBuilder();
			for (CompilationUnit cu : r.units) {
				sb.append(cu.getStringCode() + "\n");
			}
			i.setAnswerSnippets(sb.toString());
			answerIDs.put(r.answerId, i);

		}

		print("AnswerIDs collected", answerIDs.size());

		// traverse SO dump and lookup for question ID

		final HashMap<Long, TreeSet<Instance>> questionsToInstances = new HashMap<Long, TreeSet<Instance>>();

		final Cache cache = new Cache();

		XMLManager.load(STACK_OVERFLOW_DATA, new RowProcessor() {

			public void process(Row row) {

				Attributes attr = row.getAttributes();
				String parentId = attr.getValue("ParentId");

				if (parentId != null) {
					
					Long aId = Long.parseLong(attr.getValue("Id"));

					if (answerIDs.keySet().contains(aId)) {
						
						Instance i = answerIDs.get(aId);
						Long pId = Long.parseLong(parentId);
						i.setQuestionID(pId);

						TreeSet<Instance> set;
						if (questionsToInstances.containsKey(pId))
							set = questionsToInstances.get(pId);
						else {
							set = new TreeSet<Instance>();
							questionsToInstances.put(pId, set);
						}
						set.add(i);

						if (questionsToInstances.size() % 1000 == 0)
							print("Question IDs found in XML", questionsToInstances.size());

					}

				}

				if (++cache.count % 1000000 == 0)
					print("Entities processed", cache.count);

			}

		});

		// traverse SO dump and lookup for question text
		cache.count = 0;

		XMLManager.load(STACK_OVERFLOW_DATA, new RowProcessor() {

			public void process(Row row) {

				Attributes attr = row.getAttributes();
				String id = attr.getValue("Id");
				
				Long qId = Long.parseLong(id);

				if (questionsToInstances.keySet().contains(qId))
					for (Instance i : questionsToInstances.get(qId))
						i.setQuestionText(attr.getValue("Title"));

				if (++cache.count % 1000000 == 0)
					print("Entities processed", cache.count);

			}

		});

		DBHandler db = new DBHandler();
		db.connect();
		db.setAutocommit(false);
		PrintWriter pw = new PrintWriter(new File("answerIDs.output.txt"));
		long cnt = 0;
		for (Entry<Long, Instance> e : answerIDs.entrySet()) {
			db.add(e.getValue());
			pw.println(e);
			if(++cnt % 1000 == 0)
				db.commit();
		}
		pw.close();
		db.close();

//		PrintWriter pw2 = new PrintWriter(new File("questionsToInstances.output.txt"));
//		for (Entry<Long, TreeSet<Instance>> e : questionsToInstances.entrySet())
//			pw2.println(e);
//		pw2.close();

	}
	
	private static void print(String label, Number number) {
		System.out.println(label + ": "
				+ NumberFormat.getInstance().format(number));
	}

}

class Cache {
	public int count = 0;
}
