package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;

public class OpenBrowserAndURL {
	
	
	@Test(groups = {"smoke","regression","sanity","functional"})
	public void openBrowserAndURL() throws Exception {
		
		BaseClass.setUp();
		//Assert.assertTrue(false);
	}

}
