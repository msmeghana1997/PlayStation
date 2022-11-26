package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * 
 * @author TestYantra
 *
 */
public class Retry implements IRetryAnalyzer
{
    int retry=1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retry>=0)
        {
            retry--;
            return  true;
        }
        return false;
    }
}
