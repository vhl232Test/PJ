import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestForPJ {
    WebDriver driver;
    Util util = new Util();
    TheInternetHerokuappComMainPage theInternetHerokuappComMainPage;
    LoginPage loginPage;
    LocalDateTime date;
    DateTimeFormatter dateTimeFormatter;


    @BeforeClass
    public void setProp(){
        System.setProperty(util.webdriverChrom,util.anotherPathChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(util.urltheInternetHerokuappCom);
    }


    @Test(priority = 0)
    public void goToTheInternetHerokuappCom(){

        theInternetHerokuappComMainPage = new TheInternetHerokuappComMainPage(driver);

        theInternetHerokuappComMainPage.from_Authentication.click();
    }

    @Test(priority = 1)
    public void enterData(){
        loginPage = new LoginPage(driver);

        loginPage.username.sendKeys("tomsmith");
        loginPage.password.sendKeys("SuperSecretPassword!");
        loginPage.loginButton.click();

        // message about logged from page
        String messageLog = loginPage.messageAboutLogged.getText().trim();
        //System.out.println(messageLog);
        String newmMessageLog = messageLog.substring(0,messageLog.indexOf("!")+1);
        if (util.successfulMesage.equals(newmMessageLog)){
            //Convert web driver object to TakeScreenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
            //Call getScreenshotAs method to create image file
            File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
            //file's destination and set name file
            date = LocalDateTime.now();
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd  HH-mm-ss");
            String pathFile = "./target/screenshots/" + dateTimeFormatter.format(date) +".png";// + file.getName();
            try {
                //Copy file at destination
                FileUtils.copyFile(file,new File(pathFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
            System.out.println(newmMessageLog);
        //Assert.assertEquals(util.errormessege,newmMessageError);

    }

    /*@AfterClass
    public void closeBrowser(){
        driver.quit();
    }*/
}
