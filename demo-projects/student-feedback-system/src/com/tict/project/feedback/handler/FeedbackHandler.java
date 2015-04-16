package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.Faculty;
import com.tict.project.feedback.vo.FeedbackConfigParam;
import com.tict.project.feedback.vo.Subject;

public class FeedbackHandler {

	private DatabaseConnector connector;
	private List<FeedbackConfigParam> feedbackConfigParams;
 	
	public FeedbackHandler(DatabaseConnector connector) {
		this.connector = connector;
		try {
			ResultSet rs = connector.executeQuery("select * from "+FeedbackConsts.SCHEMA+".FEEDBACK_CONFIG");
			feedbackConfigParams = new ArrayList<FeedbackConfigParam>();
			while(rs.next()) {
				FeedbackConfigParam param = mapFeedbackConfigParamFromRS(rs);
				feedbackConfigParams.add(param);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<FeedbackConfigParam> getConfigParams() {
		return feedbackConfigParams;
	}
	
	public String[][] viewFeedback() throws SQLException {
		ResultSet rs = connector.executeQuery(FeedbackConsts.QUERY_VIEW_FEEDBACK);
		Map<String, Map<String, String>> facultyMap = new HashMap<String, Map<String, String>>();
		while(rs.next()) 
		{
			String facultyName = rs.getString(1);
			Map<String, String> subMap = null;
			if(!facultyMap.containsKey(facultyName)) {
				subMap = new HashMap<String, String>();
				facultyMap.put(facultyName, subMap);
			}
			else {
				subMap = facultyMap.get(facultyName);
			}
			subMap.put(rs.getString(2), rs.getString(3));
		}
		int length = facultyMap.keySet().size()+1;
		int width = -1;
		String[][] arr = new String[length][];
		
		int ctr = 1;
		boolean flag = false;
		for(String key : facultyMap.keySet()) {
			width = facultyMap.get(key).keySet().size()+1;
			arr[ctr] = new String[width];
			if(!flag) {
				arr[0] = new String[width];
				arr[0][0] = "Faculty Name";
			}
			int ctr2 = 0;
			arr[ctr][ctr2++] =  key;			
			for(String key2 : facultyMap.get(key).keySet()) {
				if(!flag) {
					arr[0][ctr2] = key2;
				}				
				arr[ctr][ctr2++] =  facultyMap.get(key).get(key2);
			}
			ctr++;
			flag = true;
		}
		return arr;
	}
	
	public Map getFeedbackSubjects(Long studentId, String subjectId, String facultyId) throws SQLException {
		String query = null;
		if(null != subjectId && !subjectId.equalsIgnoreCase("select")) {
			query = FeedbackConsts.QUERY_STUDENT_SUBJECTS_FACULTY.replaceAll("\\?", ""+studentId)+" AND T3.ID="+subjectId;
		}
		else if(null != facultyId && !facultyId.equalsIgnoreCase("select")) {
			query = FeedbackConsts.QUERY_STUDENT_SUBJECTS_FACULTY.replaceAll("\\?", ""+studentId)+" AND T4.ID="+facultyId;
		}
		else {
			query = FeedbackConsts.QUERY_STUDENT_SUBJECTS_FACULTY.replaceAll("\\?", ""+studentId);
		}
		
		
		//List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Set<Subject> subjects = new HashSet<Subject>();
		Set<Faculty> faculties = new HashSet<Faculty>();
		Set<Course> courses = new HashSet<Course>();
		Map response = new HashMap();
		Long semSubFacId = null;
		ResultSet rs = connector.executeQuery(query);
		while(rs.next()) {
			Subject subject = new Subject(rs.getLong("SUBJECT_ID"), rs.getString("SUBJECT_NAME"));
			subjects.add(subject);
			Faculty faculty = new Faculty(rs.getLong("FACULTY_ID"), rs.getString("FNAME"), rs.getString("LNAME"));
			System.out.println("adding faculty : "+faculty+"\t"+faculties.add(faculty));
			Course course = new Course(rs.getLong("COURSE_ID"), rs.getString("COURSE_NAME"));
			courses.add(course);
			semSubFacId = rs.getLong("SEM_SUB_FAC_ID");
			/*Map<String,String> map = new HashMap<String, String>();
			map.put("SUBJECT_ID", ""+rs.getLong("SUBJECT_ID"));
			map.put("SUBJECT_NAME", rs.getString("SUBJECT_NAME"));
			map.put("FACULTY_ID", ""+rs.getLong("FACULTY_ID"));			
			map.put("FNAME", ""+rs.getString("FNAME"));
			map.put("LNAME", ""+rs.getString("LNAME"));
			map.put("SEM_SUB_FAC_ID", ""+rs.getLong("SEM_SUB_FAC_ID"));
			map.put("COURSE_ID", ""+rs.getLong("COURSE_ID"));
			map.put("COURSE_NAME", ""+rs.getString("COURSE_NAME"));
			list.add(map);*/
		}
		response.put("subjects", subjects);
		response.put("faculties", faculties);
		response.put("courses", courses);
		response.put("SEM_SUB_FAC_ID", semSubFacId);
		return response;
	}
	
	public void saveFeedback(HttpServletRequest request, long studentId) throws SQLException {
		try {
			for(FeedbackConfigParam feedbackConfigParam : feedbackConfigParams) {
				String semSubFacId = request.getParameter("semSubFacId");
				System.out.println("semSubFacId: "+semSubFacId);
				String code = feedbackConfigParam.getCode();
				String value = request.getParameter(code);
				System.out.println("code: "+feedbackConfigParam.getCode()+"\tvalue: "+value);
				
				String query = "insert into "+FeedbackConsts.SCHEMA+".FEEDBACK(FEEDBACK_CONFIG_ID, STUDENT_ID, SEM_SUB_FAC_ID, EVALUATION) values("+
				feedbackConfigParam.getId()+", "+studentId+", "+semSubFacId+", "+value+")";
				connector.executeUpdate(query);
				connector.commit();
			}
		}
		catch(SQLException ex) {
			connector.rollback();
			throw ex;
		}		
	}
	
	protected FeedbackConfigParam mapFeedbackConfigParamFromRS(ResultSet rs) throws SQLException {
		FeedbackConfigParam param = new FeedbackConfigParam();
		param.setId(rs.getLong("ID"));
		param.setCode(rs.getString("ATTRIBUTE"));
		param.setDesc(rs.getString("ATTRIBUTE_DESC"));
		return param;
	}
}
