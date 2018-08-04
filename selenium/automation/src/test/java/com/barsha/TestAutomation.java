package com.barsha;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAutomation extends TestBaseSetup {

	@BeforeClass
	public static void globalSetup() throws IOException {
		loadCsv(TESTCASE_FILE);
	}
	
	@Before
	public void setUp() throws IOException {
		initializeTestBaseSetup();
	}
	
	@Test
	public void testLogout() throws IOException {
		for (CsvRec rec : recordsMap.get("Logout")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testRegistrationMithra() {
		for (CsvRec rec : recordsMap.get("RegistrationMithra")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testRegistrationMedco() {
		for (CsvRec rec : recordsMap.get("MedcoOnly")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testMedcoIP() {
		for (CsvRec rec : recordsMap.get("MedcoIP")) {
			handleRecord(rec);	
		}
	}
	
	
	
	@Test
	public void testMedcoPreauthSave() {
		for (CsvRec rec : recordsMap.get("MedcoPreauthSave")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testMedcoPreauthInitiate() {
		for (CsvRec rec : recordsMap.get("MedcoPreauthInitiate")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testMedcoSurgery() {
		for (CsvRec rec : recordsMap.get("MedcoSurgery")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testMedcoDischarge() {
		for (CsvRec rec : recordsMap.get("MedcoDischarge")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testMedcoClaims() {
		for (CsvRec rec : recordsMap.get("MedcoClaims")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testEndToEndAutoApproval() {
		for (CsvRec rec : recordsMap.get("E2E")) {
			handleRecord(rec);	
		}
	}
	
	@Test
	public void testRegistrationMithraMedco() {
		String[] testcases = new String[] {"RegistrationMithra", "RegistrationMedco"};
		for (String testcase : testcases) {
			for (CsvRec rec : recordsMap.get(testcase)) {
				handleRecord(rec);
				//waitTimer(5000);
			}	
		}
	}
	
	@After
	public void tearDown() {
		waitTimer(1000);
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
