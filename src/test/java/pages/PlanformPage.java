package pages;

import core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlanformPage extends PageBase {

    @FindBy(css = "[data-testname='text_1_stream_name']")
    private WebElement plan1;

    @FindBy(css = "[type=button]")
    private WebElement continueButton;

    @FindBy(id = "planSelectionPrice-1s")
    private WebElement plan1Price;

    public PlanformPage(WebDriver driver) {
        super(driver);
    }

    public String getPlan1Text(){
        return plan1.getText();
    }

    public void choosePlan1(){
        plan1.getText();
    }

    public String getPlan1Price(){
        return waitUntilVisibleWebElement(plan1Price).getText();
    }

    public void continueWithSelectedPlan(){
        scrollToElement(continueButton).click();
    }
}
