import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestForPJ {
    WebDriver driver;
    Util util = new Util();
    TheInternetHerokuappComMainPage theInternetHerokuappComMainPage;
    LoginPage loginPage;


    @BeforeClass
    public void setProp(){
        System.setProperty(util.webdriverChrom,util.chromPath);
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

        loginPage.username.sendKeys("tomsmith ");
        loginPage.password.sendKeys("SuperSecretPassword!");
        loginPage.loginButton.click();

        String messageError = loginPage.errorMassege.getText().trim();
        String newmMessageError = messageError.substring(0,messageError.indexOf("!")+1);
        System.out.println(newmMessageError);
        Assert.assertEquals(util.errormessege,newmMessageError);

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
