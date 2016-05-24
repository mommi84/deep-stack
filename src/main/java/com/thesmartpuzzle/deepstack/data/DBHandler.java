package com.thesmartpuzzle.deepstack.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 *
 */
public class DBHandler {
		
	public DBHandler() {
		super();
	}

	private Connection connect = null;
	// private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	// private ResultSet resultSet = null;

	protected static String host = "localhost";
	protected static String dbname = "so_dataset";
	protected static String username = "root";
	protected static String password = "";

	public void connect() {
		try {
			
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String str = "jdbc:mysql://" + host + "/" + dbname + "?" + "user="
					+ username;
			if (!password.isEmpty())
				str += "&password=" + password;

			connect = DriverManager.getConnection(str);
			
			System.out.println("Connected to "+host+"/"+dbname+" ("+this.toString()+")");
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setAutocommit(boolean value) {
		try {
			connect.setAutoCommit(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void close() {
		try {
			connect.commit();
			connect.close();
			System.out.println("Database connection closed ("+this.toString()+").");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Instance i) {
		
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO question_snippet "
					+ "(id, question_id, answer_id, text, snippet) VALUES (?, ?, ?, ?, ?);");
			
			preparedStatement.setString(1, i.getID());
			preparedStatement.setLong(2, i.getQuestionID());
			preparedStatement.setLong(3, i.getAnswerID());
			preparedStatement.setString(4, i.getQuestionText());
			preparedStatement.setString(5, i.getAnswerSnippets());
			
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR IN " + preparedStatement);
			e.printStackTrace();
		}
		
		
	}

	public void commit() {
		try {
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
