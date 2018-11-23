package com.webdriver;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    @Test
    public void testSelenium() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://google.com/ncr");
        WebElement element = webDriver.findElement(By.name("q"));
        try {
            element.sendKeys("selenium\n"); //поиск по слову "selenium";
            element.submit();
        } catch (StaleElementReferenceException e) {
        }

        //1. проверка первого результата выдачи. 11 предшествующих - стандартные ссылки страницы

        java.util.List<WebElement> links = webDriver.findElements(By.tagName("a"));
        check(links);

        //переход на страницу Images
        element = webDriver.findElement(By.linkText("Images"));
        element.click();

        //2. проверка относится ли первая картинка в выдаче как-либо к сайту seleniumhq.org по первой подписью под картинкой
        links = webDriver.findElements(By.xpath("//*[contains(@class,'nJGrxf FnqxG')]"));
        for (int i = 0, k = 0; i < links.size(); i++) {
            if (!links.get(i).getText().equals("")) {
                if (links.get(i).getText().contains("seleniumhq.org")) {
                    System.out.println("Checked, success. First image is related to web-site: " + links.get(i).getText());
                    break;
                } else {
                    System.out.println("Checked, failure. First image is not related to web-site ");
                    break;
                }
            }
        }

        //3. проверка при возвращении, что первый рещультат выдачи совпадает с тем, что был на шаге 3
        element = webDriver.findElement(By.linkText("All"));
        element.click();
        links = webDriver.findElements(By.tagName("a"));
        check(links);
    }

    //вспомогательный метод для проверки первого результата
    public  void check(java.util.List<WebElement> links){
        for (int i = 0, k = 0; i < links.size(); i++) {
            if (!links.get(i).getText().equals("")) {
                k += 1;
                //проверка первого результата выдачи. 11 предшествующих - стандартные ссылки страницы
                if (k == 12) {
                    if (links.get(i).getText().contains("seleniumhq.org")) {
                        System.out.println("Checked, success. First result: " + links.get(i).getText());
                    } else {
                        System.out.println("Checked, failure. First result: " + links.get(i).getText());
                    }
                }
            }
        }
    }
}

