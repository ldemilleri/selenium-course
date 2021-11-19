import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTest {
    WebDriver driver;
    WebElement element;
    private JavascriptExecutor javascriptExecutor;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void BasicTest(){
        driver.get("https://www.saucedemo.com/");
        assertEquals("Swag Labs", driver.getTitle());
    }

    @Test
    public void LocateElements1Test(){

        //2. navigate to the URL
        driver.get("https://www.saucedemo.com/");
        //3. Find element
        WebElement element;
        //ID
        element = driver.findElement(By.id("user-name"));
        //Name
        //driver.findElement(By.name("name of locator"));
        //Class name
        driver.findElement(By.className("form_input"));
        //Tag name
        driver.findElement(By.tagName("input"));
        //Css selector
        //#user-name
        driver.findElement(By.cssSelector("#user-name"));
        //Xpath
        // //*[@id="user-name"]
        driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        driver.quit();

        //1. Instantiate the driver
        driver = new EdgeDriver();
        //2. navigate to the URL
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        //Link text
        driver.findElement(By.linkText("Click me using this link text!"));
        //Partial link text
        driver.findElement(By.partialLinkText("link text!"));

    }
    @Test
    public void LocateElements2Test(){
        //2. navigate to the URL
        driver.get("https://www.saucedemo.com/");
        //3. Find element //4. check the state
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        //5. take action //6. record the result
        assertTrue(element.isDisplayed());
    }
    @Test
    public void WebDriverMethods1Test(){
        driver.get("https://example.cypress.io/commands/actions");
        element = driver.findElement(By.cssSelector(".action-focus"));
        Actions actions = new Actions(driver);

        actions.moveToElement(element).click().perform();
        assertTrue(driver.findElement(By.xpath("//*[@for='password1']")).getAttribute("style").contains("color: orange;"));
    }
    @Test
    public void WebDriverMethods2Test(){
        driver.get("https://example.cypress.io/commands/cookies");
        element = driver.findElement(By.cssSelector(".set-a-cookie"));
        element.click();

        Cookie cookie = driver.manage().getCookieNamed("token");
        assertEquals("123ABC", cookie.getValue());

    }
    @Test
    public void JavascriptTest() throws InterruptedException {
        driver.navigate().to("https://ultimateqa.com/complicated-page/");
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        javascriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(1000);
    }

}
