package Utilities;

import Pages.Parent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriverSingle {
    /**
     *  Interview Sorusu
     *              Singleton driver nedir?
     *      Bütün projede aynı ve tek bir driverın kullanılmasıdır.
     */

    public static WebDriver driver;

    public static WebDriver getDriver()
    {
        if (driver== null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }

        return driver;
    }

    public static void DriverQuit()
    {

        Parent.delay(5);

        if (driver != null)
        {
            driver.quit();
            driver=null;
        }
    }



}
