package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.User;

public class UserHandler extends AbstractHandler {

	private String findStudentQuery = "select ID, UNAME, FNAME, LNAME, UTYPE,EMAIL,DOB,CASTE,GENDER,NATIONALITY from "+FeedbackConsts.SCHEMA+".USER where uname = '?'";
	
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
			connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".USER(UNAME,PASSWORD,FNAME,LNAME,UTYPE) values('"+
					user.getUsername()+"', 'password', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getType()+"')");
		}
		else {
			//old user, just update record
			connector.executeUpdate("update "+FeedbackConsts.SCHEMA+".user set uname='"+
					user.getUsername()+"', fname='"+user.getFirstName()+"', lname='"+user.getLastName()+"' where uname='"+user.getUsername()+"'");
		}
		
	}
	
	public List<User> listUsers(String type) throws SQLException {
		String query = "select ID, UNAME, FNAME, LNAME, UTYPE from "+FeedbackConsts.SCHEMA+".USER where UTYPE='"+type+"'";
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
		String fname = rs.getString("FNAME");
		String lname = rs.getString("LNAME");
		String uname = rs.getString("UNAME");
		String type = rs.getString("UTYPE");
		long id = rs.getLong("ID");
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setUsername(uname);
		user.setId(id);
		user.setType(type);
		user.setEmail(rs.getString("EMAIL"));
		user.setDob(rs.getString("DOB"));
		user.setCaste(rs.getString("CASTE"));
		user.setGender(rs.getString("GENDER"));
		user.setNationality(rs.getString("NATIONALITY"));
		return user;
	}
	
	public boolean changePassword(String username, String oldPassword, String newPassword) throws SQLException {
		String query = "UPDATE "+FeedbackConsts.SCHEMA+".USER SET PASSWORD='"+newPassword+"' WHERE UNAME='"+username+"' and PASSWORD='"+oldPassword+"'";
		int response = connector.executeUpdate(query);
		if(response == 1) {
			connector.commit();
			return true;
		}
		return false;
	}
}
