package core;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    private static WebDriver driver;
    private WebDriverWait wait;

    protected PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver), this);
        this.wait = new WebDriverWait(this.driver, 10);
    }

    public WebElement waitUntilVisibleWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public WebElement scrollToElement(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public void clickWithJavaScript(WebElement we){
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
        }catch (NoSuchElementException e){
            System.out.println(e);
            return;
        }
    }
}
