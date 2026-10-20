package base;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import listeners.TestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.PageManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

@Listeners(TestListener.class)
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

        PageManager.setPage(page);
    }

    @AfterMethod
    public void tearDown() {
            PageManager.removePage();
            if (context != null) {
                context.close();
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