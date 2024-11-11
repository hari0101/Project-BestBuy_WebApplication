package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryListener implements IRetryAnalyzer {

private static Logger log =	LogManager.getLogger(RetryListener.class.getName());
private static int maxRetry = 3;	
private        int count = 0;	
	
	
	@Override
	public boolean retry(ITestResult resultTry) {
		
		if(!resultTry.isSuccess()) {
				
			if(this.count<maxRetry) {
				
				log.info("Retrying Test " + resultTry.getName() + "with Status : " + getStatusName(resultTry.getStatus () ) 
											+ " for the " + (this.count + 1) + " time(s).");
				
				this.count++;
				return true;
			}
			
			
		}
		
		return false;
		
	}

	public static String getStatusName(int result) {
		
		String status = null;
		
		if(result == 1) { status = "SUCCESS"; }
		if(result == 2) { status = "FAILURE"; }
		if(result == 3) { status = "SKIP"; }
		
		return status;
	}	


}
