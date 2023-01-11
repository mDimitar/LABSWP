package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    private UserService userService;
    private WebDriver driver;

    @BeforeEach
    private void setup(){
        this.driver = new HtmlUnitDriver(true);
    }
    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test
    public void testScenario(){

        this.driver = new HtmlUnitDriver(true);
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(0,0, 0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        balloonsPage.assertElements(10,10, 1);
    }


}
