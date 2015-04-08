package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.User;

public class UserHandler extends AbstractHandler {

	private String findStudentQuery = "select id, uname, fname, lname, utype from "+FeedbackConsts.SCHEMA+".user where uname = '?'";
	
	public UserHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public User getUser(String username) {
		try {
			ResultSet rs = connector.executeQuery(findStudentQuery.replace("?", username));
			if(rs.next()) {
				User user = mapUserFromRS(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveUser(User user) throws SQLException {
		User existingUser = getUser(user.getUsername());
		if(existingUser == null) {
			//New user, need to insert
			connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".user(uname,password,fname,lname,utype) values('"+
					user.getUsername()+"', 'password', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getType()+"')");
		}
		else {
			//old user, just update record
			connector.executeUpdate("update "+FeedbackConsts.SCHEMA+".user set uname='"+
					user.getUsername()+"', fname='"+user.getFirstName()+"', lname='"+user.getLastName()+"' where uname='"+user.getUsername()+"'");
		}
		
	}
	
	public List<User> listUsers(String type) throws SQLException {
		String query = "select id, uname, fname, lname, utype from "+FeedbackConsts.SCHEMA+".user where utype='"+type+"'";
		ResultSet rs = connector.executeQuery(query);
		List<User> users = new ArrayList<User>();
		while(rs.next()) {
			User user = mapUserFromRS(rs);
			users.add(user);
		}
		return users;
	}
	
	protected User mapUserFromRS(ResultSet rs) throws SQLException {
		User user = new User();
		String fname = rs.getString("fname");
		String lname = rs.getString("lname");
		String uname = rs.getString("uname");
		String type = rs.getString("utype");
		long id = rs.getLong("id");
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setUsername(uname);
		user.setId(id);
		user.setType(type);
		return user;
	}
}
