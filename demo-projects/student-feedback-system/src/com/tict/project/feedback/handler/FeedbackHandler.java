package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.FeedbackConfigParam;

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
	
	public List<FeedbackConfigParam> viewFeedback(long facultyId) {
		List<FeedbackConfigParam> result = new ArrayList<FeedbackConfigParam>();
		try {
			String query = "select t2.id, t2.attribute ,t2.attribute_desc , avg(t1.EVALUATION) evaluation"+ 
					" from feedback.feedback t1, feedback.feedback_config t2"+ 
					" group by t1.feedback_config_id,t2.id,t2.attribute_Desc having fac_sub_course_id = (select fac_sub_course_id  from feedback.fac_sub_course_xref  where faculty_id = "+facultyId+")"+
					" and t1.feedback_config_id=t2.id"+
					" order by t2.id";
			ResultSet rs = connector.executeQuery(query);
			
			while(rs.next()) {
				FeedbackConfigParam param = new FeedbackConfigParam();
				param.setId(rs.getLong("ID"));
				param.setDesc(rs.getString("attribute_desc"));
				param.setCode(rs.getString("attribute"));
				param.setScore(rs.getFloat("evaluation"));
				result.add(param);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void saveFeedback(HttpServletRequest request, long studentId) throws SQLException {
		for(FeedbackConfigParam feedbackConfigParam : feedbackConfigParams) {
			int facultyId = Integer.parseInt(request.getParameter("facultyId"));
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			String code = feedbackConfigParam.getCode();
			String value = request.getParameter(code);
			System.out.println("code: "+feedbackConfigParam.getCode()+"\tvalue: "+value);
			
			String query1 = "select * from "+FeedbackConsts.SCHEMA+".FAC_SUB_COURSE_XREF where FACULTY_ID="+facultyId+" and SUBJECT_ID="+subjectId;
			
			long facSubCourseId = -1;
			ResultSet rs1 = connector.executeQuery(query1);
			if(rs1.next()) {
				facSubCourseId = rs1.getInt("ID");
			}
			rs1.close();
			System.out.println("facSubCourseId: "+facSubCourseId);
			
			String query = "insert into "+FeedbackConsts.SCHEMA+".FEEDBACK(FEEDBACK_CONFIG_ID, STUDENT_ID, FAC_SUB_COURSE_ID, EVALUATION) values("+
			feedbackConfigParam.getId()+", "+studentId+", "+facSubCourseId+", "+value+")";
			connector.executeUpdate(query);
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
