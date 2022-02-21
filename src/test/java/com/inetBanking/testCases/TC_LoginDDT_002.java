package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageobjects.LoginPage;


public class TC_LoginDDT_002 extends BaseClass{
	// step2 written
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException  {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		Thread.sleep(5000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();// close Alert
			driver.switchTo().defaultContent(); // main page
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
			lp.clickLogout();
			
			Thread.sleep(5000);
			driver.switchTo().alert().accept();// close the logout alert
			driver.switchTo().defaultContent();
		}

	}

	// step3 written
	public boolean isAlertPresent() // user defined method is created to check alert is present or not
	{
		try {
			driver.switchTo().alert();
			return true;

		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	// step1 written
	@DataProvider(name = "LoginData") // LoginData providing data so we give dataProvider ="LoginData" above
	String[][] getData() throws IOException {

		String path = System.getProperty("user.dir") + "/src//test//java//com//inetbanking//testData//ExcelHP.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 1 0
			}
		}
		return logindata;

	}

}
