package com.thesmartpuzzle.deepstack.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clean TSV data exported from MySQL, i.e. remove tabs from text corpus.
 * 
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class SQLDataClean {

	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(new File("python/question_snippet.clean.tsv"));
		Scanner in = new Scanner(new File("question_snippet.tsv"));
		while(in.hasNextLine()) {
			String[] line = in.nextLine().split("\t");
			StringBuffer sb = new StringBuffer();
			sb.append(line[0] + "\t");
			for(int i=1; i<line.length; i++)
				sb.append(line[i] + " ");
			pw.println(sb.toString().trim());
		}
		in.close();
		pw.close();
		
	}

}
