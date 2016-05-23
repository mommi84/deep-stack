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

		ArrayList<Object> results = new GSonConversion<Object>()
				.readFolder("data/csnippex/");
		for (Object r : results) {
			// System.out.println(r.answerId);
			// for(CompilationUnit unit : r.units) {
			// System.out.println(unit.body);
			// System.out.println("-----------------------------------");
			// }
			
			// FIXME format answerId as LONG, not DOUBLE
			
			System.out.println("===================================");
		}

	}

}
