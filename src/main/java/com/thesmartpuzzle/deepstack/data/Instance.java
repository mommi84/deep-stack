package com.thesmartpuzzle.deepstack.data;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class Instance implements Comparable<Instance> {
	
	private Long questionID;
	private String questionText;
	
	private Long answerID;
	private String answerSnippets;
	
	public Instance() {
		super();
		this.questionID = (long) Math.random() * 1000000;
		this.answerID = (long) Math.random() * 1000000;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Long getAnswerID() {
		return answerID;
	}

	public void setAnswerID(Long answerID) {
		this.answerID = answerID;
	}

	public String getAnswerSnippets() {
		return answerSnippets;
	}

	public void setAnswerSnippets(String answerSnippet) {
		this.answerSnippets = answerSnippet;
	}
	
	public String getID() {
		return questionID + "#" + answerID;
	}
	
	@Override
	public String toString() {
		return "Instance [questionID=" + questionID + ", questionText="
				+ questionText + ", answerID=" + answerID + ", |answerSnippets|="
				+ answerSnippets.length() + "]";
	}

	public int compareTo(Instance o) {
		return this.getID().compareTo(o.getID());
	}

}
