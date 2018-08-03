package com.barsha;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Automation extends TestBaseSetup {

	@Before
	public void setUp() throws IOException {
		initializeTestBaseSetup();
	}
	
	@Test
	public void testLogout() throws IOException {
		for (CsvRec rec : recordsMap.get("Logout")) {
			handleRecord(rec);	
		}
		waitTimer(1000);
	}
	
	@Test
	public void testRegistrationMithra() throws IOException {
		for (CsvRec rec : recordsMap.get("RegistrationMithra")) {
			handleRecord(rec);	
		}
		waitTimer(1000);
	}
	
	@Test
	public void testRegistrationMedco() throws IOException {
		for (CsvRec rec : recordsMap.get("MedcoOnly")) {
			handleRecord(rec);	
		}
		waitTimer(1000);
	}
	
	@Test
	public void testMedcoIP() throws IOException {
		for (CsvRec rec : recordsMap.get("MedcoIP")) {
			handleRecord(rec);	
		}
		waitTimer(1000);
	}
	
	
	
	@Test
	public void testMedcoPreauth() throws IOException {
		for (CsvRec rec : recordsMap.get("MedcoPreauth")) {
			handleRecord(rec);	
		}
		waitTimer(1000);
	}
	
	@Test
	public void testRegistrationMithraMedco() throws IOException {
		String[] testcases = new String[] {"RegistrationMithra", "RegistrationMedco"};
		for (String testcase : testcases) {
			for (CsvRec rec : recordsMap.get(testcase)) {
				handleRecord(rec);
				//waitTimer(5000);
			}	
		}
		waitTimer(1000);
	}
	
	@After
	public void tearDown() {
		getDriver().close();
	}
	
	//@Test
		/*public void test() throws IOException {
			int ctr=1;
			String[] testcases = new String[] {"RegistrationMithra", "RegistrationMedco"};
			//String[] testcases = new String[] {"Logout"};
			for (String testcase : testcases) {
				initializeTestBaseSetup();
				try {
				for (CsvRec rec : recordsMap.get(testcase)) {
					System.out.println(" recNo:"+ ctr++ + " "+rec.getPageName()+" "+rec.getValue()+" "+rec.getType()+" "+rec.getCssSelector());
					handleRecord(rec);
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					getDriver().close();	
				}
				System.out.println("\n\n\n Testcase "+testcase+" completed\n\n\n");
			}
			waitTimer(1000);
		}*/
}
