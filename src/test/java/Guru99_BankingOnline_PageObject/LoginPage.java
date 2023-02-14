package Guru99_BankingOnline_PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    public WebDriver driver;
    @FindBy(xpath = "//img[@alt='Guru99 Demo Sites']")
    public WebElement LoginPage_logo;
    @FindBy(xpath = "//input[@name='uid']")
    private WebElement userNameTxtField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTxtFields;
    @FindBy(xpath = "//input[@name='btnLogin']")
    private WebElement logInBtn;

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    public void enterUseerName(String username)
    {
        userNameTxtField.sendKeys(username);
    }
    public void enterPassword(String password)
    {
        passwordTxtFields.sendKeys(password);
    }
    public void clickLoginBtn()
    {
        logInBtn.click();
    }

}
