package com.thesmartpuzzle.deepstack.xml;

import org.xml.sax.Attributes;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class Row {

	private String uri;
	private String localName;
	private String qName;
	private Attributes attributes;

	public Row(String uri, String localName, String qName, Attributes attributes) {
		super();
		this.uri = uri;
		this.localName = localName;
		this.qName = qName;
		this.attributes = attributes;
	}

	public String getUri() {
		return uri;
	}

	public String getLocalName() {
		return localName;
	}

	public String getqName() {
		return qName;
	}

	public Attributes getAttributes() {
		return attributes;
	}


	@Override
	public String toString() {
		return "Row [uri=" + uri + ", localName=" + localName + ", qName="
				+ qName + ", #attributes="+attributes.getLength()+"]";
	}


}
