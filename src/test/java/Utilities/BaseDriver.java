package Utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriver {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (threadBrowserName.get()==null){
            threadBrowserName.set("chrome");
        }

        if (threadDriver.get() == null) {
            switch (threadBrowserName.get()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                if (!runningFromIntelij())
                {
                    // Hafizada calisma yani headless work(Jenkins) bu durumda intelij den calismaz ve option vermeliyiz
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1400,2400"); //width, height
                    threadDriver.set(new ChromeDriver(options));
                }
                else {     // Intelij den calisiyorsa asagidakini set ediyor

                    threadDriver.set(new ChromeDriver());
                }

                    System.out.println("Intellij den mi calisiyor= "+runningFromIntelij());   break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    threadDriver.set(new FirefoxDriver());
                    break;
            }
        }
        return threadDriver.get();
    }

    public static void quitDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            WebDriver webDriver=threadDriver.get();
            webDriver = null;
            threadDriver.set(webDriver);

        }
    }

         // Intelij den calistigini asagidaki koddan anliyor

        public static boolean runningFromIntelij()
        {
            System.out.println("classPath = "+System.getProperty("java.class.path"));
          //     classPath = C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3.2\lib\idea_rt.jar

        String classPath=System.getProperty("java.class.path");
            boolean answer=false;

            if (classPath.contains("cucumber-jvmFormatter.jar"))
            {
                answer=true;
            }
            else if (classPath.contains("idea_rt.jar")){
                answer=true;

            }

            // cucumber-jvmFormatter.jar

            return answer;
        }
}































