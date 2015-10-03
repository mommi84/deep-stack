package com.thesmartpuzzle.deepstack.regression.data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Post {

	int Id;
	int PostTypeId;
	int AcceptedAnswerId;
	int ParentId;
	int Score;
	int ViewCount;
	String Body;
	int OwnerUserId;
	int LastEditorUserId;
	java.sql.Date LastEditDate;
	java.sql.Date LastActivityDate;
	String Title;
	String Tags;
	int AnswerCount;
	int CommentCount;
	int FavoriteCount;
	java.sql.Date CreationDate;

	private Post(int id, int postTypeId, int acceptedAnswerId, int parentId,
			int score, int viewCount, String body, int ownerUserId,
			int lastEditorUserId, Date lastEditDate, Date lastActivityDate,
			String title, String tags, int answerCount, int commentCount,
			int favoriteCount, Date creationDate) {
		super();
		Id = id;
		PostTypeId = postTypeId;
		AcceptedAnswerId = acceptedAnswerId;
		ParentId = parentId;
		Score = score;
		ViewCount = viewCount;
		Body = body;
		OwnerUserId = ownerUserId;
		LastEditorUserId = lastEditorUserId;
		LastEditDate = lastEditDate;
		LastActivityDate = lastActivityDate;
		Title = title;
		Tags = tags;
		AnswerCount = answerCount;
		CommentCount = commentCount;
		FavoriteCount = favoriteCount;
		CreationDate = creationDate;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPostTypeId() {
		return PostTypeId;
	}
	public void setPostTypeId(int postTypeId) {
		PostTypeId = postTypeId;
	}
	public int getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}
	public void setAcceptedAnswerId(int acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getViewCount() {
		return ViewCount;
	}
	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public int getOwnerUserId() {
		return OwnerUserId;
	}
	public void setOwnerUserId(int ownerUserId) {
		OwnerUserId = ownerUserId;
	}
	public int getLastEditorUserId() {
		return LastEditorUserId;
	}
	public void setLastEditorUserId(int lastEditorUserId) {
		LastEditorUserId = lastEditorUserId;
	}
	public java.sql.Date getLastEditDate() {
		return LastEditDate;
	}
	public void setLastEditDate(java.sql.Date lastEditDate) {
		LastEditDate = lastEditDate;
	}
	public java.sql.Date getLastActivityDate() {
		return LastActivityDate;
	}
	public void setLastActivityDate(java.sql.Date lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public int getAnswerCount() {
		return AnswerCount;
	}
	public void setAnswerCount(int answerCount) {
		AnswerCount = answerCount;
	}
	public int getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(int commentCount) {
		CommentCount = commentCount;
	}
	public int getFavoriteCount() {
		return FavoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		FavoriteCount = favoriteCount;
	}
	public java.sql.Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(java.sql.Date creationDate) {
		CreationDate = creationDate;
	}
	
	
	public static ArrayList<Post> getPost(ResultSet rs){
		
		ArrayList<Post> posts = new ArrayList<Post>();

		try {
			while(rs.next()) {
			   Post post = new Post(
					   rs.getInt(1),
					   rs.getInt(2),
					   rs.getInt(3),
					   rs.getInt(4),
					   rs.getInt(5),
					   rs.getInt(6),
					   rs.getString(7),
					   rs.getInt(8),
					   rs.getInt(9),
					   rs.getDate(10),
					   rs.getDate(11),
					   rs.getString(12),
					   rs.getString(13),
					   rs.getInt(14),
					   rs.getInt(15),
					   rs.getInt(16),
					   rs.getDate(17)
);
			  posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		
		return posts;
		
		
	}
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", PostTypeId=" + PostTypeId
				+ ", AcceptedAnswerId=" + AcceptedAnswerId + ", ParentId="
				+ ParentId + ", Score=" + Score + ", ViewCount=" + ViewCount
				+ ", Body=" + Body + ", OwnerUserId=" + OwnerUserId
				+ ", LastEditorUserId=" + LastEditorUserId + ", LastEditDate="
				+ LastEditDate + ", LastActivityDate=" + LastActivityDate
				+ ", Title=" + Title + ", Tags=" + Tags + ", AnswerCount="
				+ AnswerCount + ", CommentCount=" + CommentCount
				+ ", FavoriteCount=" + FavoriteCount + ", CreationDate="
				+ CreationDate + "]";
	}
	
	
	
	
	
	
	
}
