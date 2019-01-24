package pages;

import core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends PageBase {

    @FindBy(id = "id_email")
    private WebElement emailTextBox;

    @FindBy(id = "id_password")
    private WebElement passwordTextBox;

    @FindBy(css = "[for='cb_emailPreference']")
    private WebElement emailPreferencesCheckBox;

    @FindBy(css = "[type=submit]")
    private WebElement continueButton;

    @FindBy(css = "[type=button]")
    private WebElement continueToRegistrationButton;

    @FindBy(className = "nf-message-contents")
    private WebElement warningMessage;

    @FindBy(className = "inputError")
    private WebElement inputError;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void signUp(String email, String password){
        waitUntilVisibleWebElement(emailTextBox).sendKeys(email);
        passwordTextBox.sendKeys(password);
        emailPreferencesCheckBox.click();
        continueButton.click();
    }

    public void continueToRegistration(){
        waitUntilClickable(continueToRegistrationButton).click();
    }

    public String getSignUpWarningMessage(){
        return warningMessage.getText();
    }

    public String getInputWarningMessage(){
        return inputError.getText();
    }
}
