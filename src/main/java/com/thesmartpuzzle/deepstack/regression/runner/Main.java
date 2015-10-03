package com.thesmartpuzzle.deepstack.regression.runner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thesmartpuzzle.deepstack.regression.data.MysqlConnect;
import com.thesmartpuzzle.deepstack.regression.data.Post;



public class Main {

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException {

		
		// SE VUOI SOLO DETERMINATE COLONNE
		
		ResultSet resultSet = MysqlConnect
				.getDbCon()
				.query("SELECT Body FROM stackoverflow.posts where Body LIKE '%<code>%' AND Title LIKE '%log4j%' LIMIT 1000;");

		
		while(resultSet.next()) {
		System.out.println(resultSet.getString(1));
		}
		
		// SE VUOI TUTTE LE  COLONNE
		 resultSet = MysqlConnect
				.getDbCon()
				.query("SELECT *  FROM stackoverflow.posts where Body LIKE '%<code>%' AND Title LIKE '%log4j%' LIMIT 1000;");


		ArrayList<Post> res = Post.getPost(resultSet);

		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).toString());
		}

	}

}