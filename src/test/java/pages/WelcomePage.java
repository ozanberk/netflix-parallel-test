package pages;

import core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends PageBase {

    @FindBy(css = "[class='authLinks signupBasicHeader']")
    private WebElement singInButton;

    @FindBy(className = "hero-cta-btn-txt")
    private WebElement joinButton;

    @FindBy(className = "stepTitle")
    private WebElement planText;

    @FindBy(css = "[placeholder='button_see_plans']")
    private WebElement seeThePlansButton;

    @FindBy(className = "cta-former-member-greeting")
    private WebElement welcomeText;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public void joinToNetflix(){
        joinButton.click();
    }

    public String getPlansText(){
        return planText.getText();
    }

    public void seePlans(){
        waitUntilClickable(seeThePlansButton).click();
    }

    public void continueToSigInPage(){
        singInButton.click();
    }

    public String getWelcomeMessage(){
        return welcomeText.getText();
    }
}
