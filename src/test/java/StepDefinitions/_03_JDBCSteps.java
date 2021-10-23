package StepDefinitions;

import Pages.DialogContent;
import Pages.LeftNav;
import Utilities.DBUtilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class _03_JDBCSteps {
    @Then("^Send the query the database \"([^\"]*)\" and control match$")
    public void sendTheQueryTheDatabaseAndControlMatch(String query)  {
       // DB Query results list
        List<List<String>> dbcounryList= DBUtilities.getlistData(query);
               //   System.out.println(dbcounryList);

        // UI (WEB) list
        DialogContent dc=new DialogContent();
        List<WebElement> uiList=dc.waitVisibleListAllElement(dc.nameList);
              //  for (WebElement e:uiList){
              //      System.out.println(e.getText());
              //  }

        // Simdi Assert ile iki listeyi kontrol edecegim. sadece ulke adlarini kontrol ediyor

       for (int i = 0; i <dbcounryList.size(); i++) {

           System.out.println(dbcounryList.get(i).get(1)+" "+uiList.get(i).getText()); // kontrol icin yazildi
           Assert.assertEquals(dbcounryList.get(i).get(1),uiList.get(i).getText());
       }

        // simdi de her uc bilgiy(id, name, code) control edecek sekilde yapalim

        List<WebElement> uiListAll=dc.waitVisibleListAllElement(dc.nameListAll);

        for (int i = 0; i < uiListAll.size(); i++) {
            for (int j = 0; j < dbcounryList.get(i).size(); j++) {
               System.out.println(uiListAll.get(i).getText()+" "+dbcounryList.get(i).get(j)); // kontrol icin yazildi
               Assert.assertTrue(uiListAll.get(i).getText().contains(dbcounryList.get(i).get(j)));
            }
        }
    }

    @When("^Navigate to cities page and select country name as \"([^\"]*)\"$")
    public void navigateToCitiesPageAndSelectCountryName(String countryName)  {
        LeftNav ln=new LeftNav();
        DialogContent dc=new DialogContent();
        ln.findAndClick("setupOne");
        ln.findAndClick("parameters");
        ln.findAndClick("cities");
        dc.findAndClick("countrySelect");
        dc.listWebelementsSelect("countryAllOptions",countryName);

    }
}
