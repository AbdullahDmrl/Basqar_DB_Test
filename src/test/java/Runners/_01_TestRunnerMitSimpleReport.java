package Runners;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



    @CucumberOptions(
        features = {"src/test/java/FeatureFiles"},  // buraya feature files ın path i verilecek.
        glue = {"StepDefinitions"},     // Step definitions ın klasörü yazılıyor.
        dryRun = false,   // true olduğunda testi çalıştırma sadece feature lara ait steplerin varlığını kontrol eder.
                          // false olduğunda ise testi çalıştırır.

            plugin = { //basit rapor oluşturan plugin
            "html:target/cucumber-report",
            "json:target/cucumber.json",
    }


)
public class _01_TestRunnerMitSimpleReport extends AbstractTestNGCucumberTests {

}
