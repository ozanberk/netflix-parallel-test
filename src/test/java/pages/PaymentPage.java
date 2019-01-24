package pages;

import core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageBase {

    @FindBy(className = "stepTitle")
    private WebElement titleText;

    @FindBy(className = "narrowContainer")
    private WebElement paymentInformationText;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public String getPaymentInformation(){
        return waitUntilVisibleWebElement(paymentInformationText).getText();
    }
}
