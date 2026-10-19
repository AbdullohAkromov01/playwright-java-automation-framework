package base;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void beforeClass() {

        playwright = Playwright.create();

        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));
    }

    @BeforeMethod
    public void setUp() {

        context = browser.newContext();

        page = context.newPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test failed " + result.getName());
            String screenshotPath = "screenshots/" + result.getName() + ".png";
            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(Paths.get(screenshotPath))
                            .setFullPage(true)
            );

            try {
                InputStream inputStream = new FileInputStream(screenshotPath);
                Allure.attachment(
                        "Failure Screenshot",
                        inputStream
                );

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (context != null) {
                context.close();
            }
        }
    }

    @AfterClass
    public void afterClass() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}