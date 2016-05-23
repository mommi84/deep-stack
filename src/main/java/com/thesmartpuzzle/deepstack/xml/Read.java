package com.thesmartpuzzle.deepstack.xml;

import org.xml.sax.Attributes;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class Read {
	
	public static void main(String[] args) {

		XMLManager.load("data/Posts.xml", new RowProcessor() {
			
			public void process(Row row) {

				Attributes attr = row.getAttributes();
				String id = attr.getValue("Id");
				String parentId = attr.getValue("ParentId");
				String title = attr.getValue("Title");
				
				System.out.println(id + "\t" + parentId + "\t" + title);
				
			}
			
		});
		
	}

}
