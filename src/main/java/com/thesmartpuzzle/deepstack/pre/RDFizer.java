package com.thesmartpuzzle.deepstack.pre;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.thesmartpuzzle.deepstack.data.DBHandler;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class RDFizer {

	private static final String NS = "http://www.thesmartpuzzle.com/sokd/instance/";
	private static final Property HAS_SNIPPET = ResourceFactory
			.createProperty("http://www.thesmartpuzzle.com/sokd/hasSnippet");
	private static final Resource SNIPPET = ResourceFactory
			.createProperty("http://www.thesmartpuzzle.com/sokd/Snippet");

	public static void main(String[] args) throws SQLException, FileNotFoundException {
		
		Model m = ModelFactory.createDefaultModel();
		
		DBHandler db = new DBHandler();
		db.connect();
		ResultSet rs = db.getQuestionsSnippets();
		for(int i=1; rs.next(); i++) {
			m.add(ResourceFactory.createResource(NS + i), RDF.type, SNIPPET);
			m.add(ResourceFactory.createResource(NS + i), HAS_SNIPPET, rs.getString("snippet"));
			if(i % 10000 == 0)
				System.out.println(i + " instances rdfized...");
		}
		
		RDFDataMgr.write(new FileOutputStream(new File("question-snippet.rdf")), m, Lang.RDFXML);
		db.close();
		
	}
}
