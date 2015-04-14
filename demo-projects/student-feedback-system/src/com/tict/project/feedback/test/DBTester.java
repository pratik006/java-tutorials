package com.tict.project.feedback.test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;

public class DBTester {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		DatabaseConnector connector = new DatabaseConnector();
		ResultSet rs = connector.executeQuery(FeedbackConsts.QUERY_VIEW_FEEDBACK.replaceAll("\\?", "802"));
		ResultSetMetaData rsm = rs.getMetaData();
		Map<String, Map<String, String>> facultyMap = new HashMap<String, Map<String, String>>();
		while(rs.next()) 
		{
			//for(int i=1;i<=rsm.getColumnCount();i++) {
			
			for(int i=1;i<=3;i++) {
				String facultyName = rs.getString(1);
				System.out.println("facultyName: "+facultyName);
				Map<String, String> subMap = null;
				if(!facultyMap.containsKey(facultyName)) {
					subMap = new HashMap<String, String>();
					facultyMap.put(facultyName, subMap);
				}
				else {
					subMap = facultyMap.get(facultyName);
				}
				
				String key = rs.getString(2);
				subMap.put(rs.getString(2), rs.getString(3));
			}
		}
		String[][] arr = new String[facultyMap.keySet().size()+1][];
		
		int ctr = 1;
		boolean flag = false;
		for(String key : facultyMap.keySet()) {
			arr[ctr] = new String[facultyMap.get(key).keySet().size()];
			int ctr2 = 0;
			if(!flag) {
				arr[0] = new String[facultyMap.get(key).keySet().size()];
			}
			for(String key2 : facultyMap.get(key).keySet()) {
				if(!flag) {
					arr[0][ctr2] = key2;
				}
				arr[ctr][ctr2++] =  facultyMap.get(key).get(key2);
				//System.out.print(key2+"\t"+facultyMap.get(key).get(key2)+"\t");
			}
			ctr++;
			flag = true;
		}
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
	}

}
