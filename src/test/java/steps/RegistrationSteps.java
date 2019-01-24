package steps;

import core.TestBase;
import helpers.RandomNumberHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.PaymentPage;
import pages.PlanformPage;
import pages.RegistrationPage;
import pages.WelcomePage;

public class RegistrationSteps extends TestBase {

    private WelcomePage welcomePage;
    private PlanformPage platformPage;
    private RegistrationPage registrationPage;
    private PaymentPage paymentPage;

    @Before
    public void setUpRegistrationPages(){
       welcomePage = new WelcomePage(driver);
       platformPage = new PlanformPage(driver);
       registrationPage = new RegistrationPage(driver);
       paymentPage = new PaymentPage(driver);
    }

    @Test
    public void successfullyRegister() {
        System.out.println("successful registration test is started...");
        welcomePage.joinToNetflix();
        Assert.assertEquals("Expected message", welcomePage.getPlansText(), "Choose your plan.");
        welcomePage.seePlans();
        Assert.assertEquals("Expected message", platformPage.getPlan1Price(), "EUR7.99");
        platformPage.continueWithSelectedPlan();
        registrationPage.continueToRegistration();
        registrationPage.signUp(RandomNumberHelper.generateRandomEmail(), RandomNumberHelper.getRandomPassword());
        Assert.assertTrue(paymentPage.getPaymentInformation().contains("not be charged"));
        System.out.println("successfully registration test is just finish...");
    }

    @Test
    public void registerWithInvalidMail() {
        System.out.println("successful registration test is started...");
        welcomePage.joinToNetflix();
        Assert.assertEquals("Expected message", welcomePage.getPlansText(), "Choose your plan.");
        welcomePage.seePlans();
        Assert.assertEquals("Expected message", platformPage.getPlan1Price(), "EUR7.99");
        platformPage.continueWithSelectedPlan();
        registrationPage.continueToRegistration();
        registrationPage.signUp("test@test", RandomNumberHelper.getRandomPassword());
        Assert.assertTrue(registrationPage.getInputWarningMessage().equals("Please enter a valid email address"));
        System.out.println("registerwithInvalidMail test is just finish...");
    }

}