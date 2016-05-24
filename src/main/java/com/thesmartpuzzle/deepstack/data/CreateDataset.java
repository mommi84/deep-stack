package com.thesmartpuzzle.deepstack.data;

import hk.ust.cse.csnippex.data.CompilationUnit;
import hk.ust.cse.csnippex.data.Result;
import hk.ust.cse.csnippex.data.persistent.GSonConversion;

import java.util.ArrayList;

import com.google.gson.internal.LinkedTreeMap;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class CreateDataset {

	public static void main(String[] args) {

		ArrayList<Result> results = new GSonConversion<Result>()
				.readFolder("data/csnippex/");
		for (Result r : results) {
			
			System.out.println(r.answerId);
			
			
			System.out.println("===================================");
		}

	}

}
