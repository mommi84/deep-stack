package com.thesmartpuzzle.deepstack.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class RowHandler extends DefaultHandler {

	private final RowProcessor processor;
	private Row currentRow;
	
	public RowHandler(RowProcessor processor) {
		super();
		this.processor = processor;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentRow = new Row(uri, localName, qName, attributes);
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		processor.process(currentRow);
	}

}
