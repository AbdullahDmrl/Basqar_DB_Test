package StepDefinitions;

import Pages.DialogContent;
import Pages.LeftNav;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

public class _02_CountryStep {
    LeftNav ln=new LeftNav();
    DialogContent dc=new DialogContent();

    @Then("^Navigate to country page$")
    public void navigate_to_country_page()  {

        ln.findAndClick("setupOne");//Setup click
        ln.findAndClick("parameters");//Parameter Click
        ln.findAndClick("Countries");//Country Click
    }

    @When("^Create a country$")
    public void create_a_country() {

        dc.findAndClick("addButton");
        dc.findAndSend("nameInput","Demirelus");
        dc.findAndSend("codeInput","dem01");
        dc.findAndClick("saveButton");

    }

    @Then("^Success message should be displayed$")
    public void success_message_should_be_displayed()  {
       dc.findAndContainsText("successMessage","successfully");
       dc.invisibilityOfElement("successMessage");
    }

    @When("^Delete added country$")
    public void deleteAddedCountry() {
        dc.findAndSend("searchInput", "Demirelus");
        dc.findAndClick("searchButton");
        dc.waitnumberOfElementsToBeLessThan(By.cssSelector("ms-delete-button.ng-star-inserted>button"),5);

        dc.findAndClick("deleteButton");
        dc.findAndClick("confirmDeleteBtn");

    }

    @When("^Create a country name as \"([^\"]*)\" code as \"([^\"]*)\"$")
    public void createACountryNameAsCodeAs(String name, String code)  {
        dc.findAndClick("addButton");
        dc.findAndSend("nameInput",name);
        dc.findAndSend("codeInput",code);
        dc.findAndClick("saveButton");

    }


    @When("^Delete the country name as \"([^\"]*)\"$")
    public void deleteTheCountryNameAs(String name)
    {
        dc.findAndDelete(name);
    }


}










