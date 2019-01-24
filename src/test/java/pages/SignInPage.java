package pages;

import core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends PageBase {

    @FindBy(id = "id_userLoginId")
    private WebElement emailTextBox;

    @FindBy(id = "id_password")
    private WebElement passwordTextBox;

    @FindBy(css = "[class='btn login-button btn-submit btn-small']")
    private WebElement signInButton;

    @FindBy(css = "[class='nfInput nfEmailPhoneInput nfEmailPhoneInError login-input login-input-email'] [class='inputError']")
    private WebElement emailWarning;

    @FindBy(css = "[class='nfInput nfPasswordInput nfPasswordInError login-input login-input-password'] [class='inputError']")
    private WebElement passwordWarning;

    @FindBy(css = "[class='ui-message-container ui-message-error'] [class='ui-message-contents']")
    private WebElement createAccountWarning;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void signIn(String email, String password){
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        signInButton.click();
    }

    public String getEmailWarningMessage(){
        return emailWarning.getText();
    }

    public String getPasswordWarningMessage(){
        return passwordWarning.getText();
    }

    public String getWrongCredentialMessage(){
        return createAccountWarning.getText();
    }
}
