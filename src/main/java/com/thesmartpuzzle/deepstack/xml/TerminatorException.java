package com.thesmartpuzzle.deepstack.xml;

import org.xml.sax.SAXException;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class TerminatorException extends SAXException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739210852166955296L;
	
	public TerminatorException(String message) {
		super(message);
	}

}
