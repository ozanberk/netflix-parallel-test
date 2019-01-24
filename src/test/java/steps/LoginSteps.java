package steps;

import core.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.SignInPage;
import pages.WelcomePage;

public class LoginSteps extends TestBase {

    private WelcomePage welcomePage;
    private SignInPage signInPage;

    @Before
    public void setUpLoginPages(){
        this.signInPage = new SignInPage(driver);
        this.welcomePage = new WelcomePage(driver);
    }

    @Test
    public void successfullyLogin(){
        welcomePage.continueToSigInPage();
        signInPage.signIn("netflixautomation@netflix.com", "netflix1234");
        Assert.assertEquals(welcomePage.getWelcomeMessage(),"Welcome back!");
    }

    @Test
    public void loginWithoutEmail(){
        welcomePage.continueToSigInPage();
        signInPage.signIn("", "netflix1234");
        Assert.assertEquals(signInPage.getEmailWarningMessage(),"Please enter a valid email or phone number.");
    }

    @Test
    public void loginWithoutPassword(){
        welcomePage.continueToSigInPage();
        signInPage.signIn("netflixautomation@netflix.com", "");
        Assert.assertEquals(signInPage.getPasswordWarningMessage()," password must contain between 4 and 60 characters.");
    }

    @Test
    public void loginWithWrongEmail(){
        welcomePage.continueToSigInPage();
        signInPage.signIn("hoppa@netflix.com", "netflix1234");
        Assert.assertTrue(signInPage.getWrongCredentialMessage().contains("we can't find an account with this email address."));
    }

    @Test
    public void loginWithWrongPassword(){
        welcomePage.continueToSigInPage();
        signInPage.signIn("netflixautomation@netflix.com", "netflix");
        Assert.assertTrue(signInPage.getWrongCredentialMessage().contains("Incorrect password."));
    }
}