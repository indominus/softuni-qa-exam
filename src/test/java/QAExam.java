import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by zlatko.hristov on 2.4.2017 г..
 */
public class QAExam {

    private String email;

    private WebDriver driver;

    @Before
    public void setUp()
    {
        driver = new FirefoxDriver();
    }

    @Test
    public void testRegister_ValidData_ShouldBeCreateAccount() {

        driver.get("http://demo.nopcommerce.com/register");

        WebElement gender = driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.clear();
        firstName.sendKeys("Zlatko");

        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.clear();
        lastName.sendKeys("Hristov");

        Select DateOfBirthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        DateOfBirthDay.selectByValue("9");

        Select DateOfBirthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        DateOfBirthMonth.selectByValue("2");

        Select DateOfBirthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        DateOfBirthYear.selectByValue("1989");

        // https://groups.google.com/forum/#!topic/selenium-users/6JtwiZBQZho :)
        int ran;
        ran = 100 + (int)(Math.random() * ((10000 - 100) + 1));
        this.email = "signuptest" + ran + "@test.com";

        WebElement email = driver.findElement(By.id("Email"));
        email.clear();
        email.sendKeys(this.email);

        WebElement password = driver.findElement(By.id("Password"));
        password.clear();
        password.sendKeys("zlatko");

        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.clear();
        passwordConfirm.sendKeys("zlatko");

        WebElement registerBtn = driver.findElement(By.id("register-button"));
        registerBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("result")));

        assertEquals("Your registration completed", driver.findElement(By.className("result")).getText().trim());
    }

    @Test
    public void testReview_LoginUserValidData_ShouldBeSuccess() {

        this.testRegister_ValidData_ShouldBeCreateAccount();

        driver.get("http://demo.nopcommerce.com/htc-one-m8-android-l-50-lollipop");

        WebElement reviewLink = driver.findElement(By.xpath("//div[@class='product-review-links']/a[2]"));
        reviewLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='review-form']/form")));

        WebElement title = driver.findElement(By.id("AddProductReview_Title"));
        title.clear();
        title.sendKeys("Very cool");

        WebElement review = driver.findElement(By.id("AddProductReview_ReviewText"));
        review.clear();
        review.sendKeys("Many many many <br /> cool \n\r phone");

        WebElement goodStar = driver.findElement(By.id("addproductrating_4"));
        goodStar.click();

        WebElement submit = driver.findElement(By.className("write-product-review-button"));
        submit.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("result")));

        assertEquals("Product review is successfully added.", driver.findElement(By.className("result")).getText().trim());
    }

    @After
    public void terDown()
    {
    }

}
