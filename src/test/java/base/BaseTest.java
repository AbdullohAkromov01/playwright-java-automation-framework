package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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
    public void tearDown() {

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