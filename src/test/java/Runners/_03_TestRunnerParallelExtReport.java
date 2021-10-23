package Runners;

import Utilities.BaseDriver;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = {"src/test/java/FeatureFiles"},
        glue = {"StepDefinitions"},

        plugin = {
                "com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport/ExtentReportRegression.html"
                // oluşturalacak raporun yeri ve adı veriliyor.
        }
)
public class _03_TestRunnerParallelExtReport extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser)
    {

       BaseDriver.threadBrowserName.set(browser);

    }

    @AfterClass
    public static void afterClass() {
           Reporter.loadXMLConfig("src/test/java/XML_Files/ExtentReportSet.xml");

           Reporter.setSystemInfo("User Name", "A Demirel");
           Reporter.setSystemInfo("Application Name", "Basqar");
           Reporter.setSystemInfo("Operating System Info", System.getProperty("os.name").toString());
           Reporter.setSystemInfo("Department", "QAr");
           Reporter.setSystemInfo("Ek Satır", "Acıklamasi");

           Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
       }
}
