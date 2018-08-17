package com.framework.tests;
import static com.framework.lib.Driver.closeBrowser;
import static com.framework.lib.Driver.getScreenPath;
import static com.framework.lib.Driver.initTestNgSoftAssert;
import static com.framework.lib.Driver.initWebDriver;
import static com.framework.lib.Driver.softAssert;
import static com.framework.lib.MyExtentReports.reports;


import static com.framework.lib.MyExtentReports.test;
import static com.framework.lib.SoftAssertions.verifyContains;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.framework.lib.InitTests;
import com.framework.lib.RetryAnalyzer;
import com.framework.lib.SoftAssertions;
import com.framework.pages.GoogleHomePage;

/**
 * @author YugandharReddyGorrep
 * @category regression
 * @Precondition
 * @Description 
 *
 */
public class TestGoogle extends InitTests
{
	Properties p = new Properties();
	@Test(priority = 1, enabled = true, groups =
	{ "smoke" }, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"keyword"})
	public void testGoogle(String searchKey) throws IOException
	{
		
		try
		{
			test = reports.startTest("testGoogle search key--"+searchKey, "to test testGoogle");
			test.assignCategory("smoke");
			initTestNgSoftAssert(); // to initialize testng soft assertions
			initWebDriver(BROWSER_TYPE, BASEURL);
			GoogleHomePage ghome=new GoogleHomePage();
			ghome.search(searchKey);
			Thread.sleep(3000);
			verifyContains(ghome.getFirstLnkDisplayed(),searchKey);
		} catch (Exception e)
		{
			e.printStackTrace();
			SoftAssertions.fail(e, getScreenPath(new Exception().getStackTrace()[0].getMethodName()));
		} finally
		{
			
			reports.endTest(test);
			reports.flush();
			closeBrowser(BROWSER_TYPE);
			softAssert.assertAll();// to assert all testng soft assertions
		}
	}
}
