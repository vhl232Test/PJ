import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TheInternetHerokuappComMainPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@id='content']//ul//li/a[text()='Form Authentication']")
    WebElement from_Authentication;

    public TheInternetHerokuappComMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
