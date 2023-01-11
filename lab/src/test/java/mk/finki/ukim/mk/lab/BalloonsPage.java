package mk.finki.ukim.mk.lab;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends AbstractPage {

    @FindBy(css = ".delete")
    private List<WebElement> deleteButtons;
    @FindBy(css = ".edit")
    private List<WebElement> editButtons;

    @FindBy(css = "#add")
    private List<WebElement> add;
    public BalloonsPage(WebDriver driver) {
        super(driver);
    }
    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public void assertElements(int deleteButtonsNumber, int editButtonsNumber, int addNumber){
        System.out.println(this.deleteButtons.size());
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals("do not match", deleteButtonsNumber, this.deleteButtons.size());
        Assert.assertEquals("do not match", editButtonsNumber, this.editButtons.size());
        Assert.assertEquals("do not match", addNumber, this.add.size());
    }

}
