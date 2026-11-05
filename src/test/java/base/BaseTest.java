package base;

import com.microsoft.playwright.*;
import listeners.TestListener;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.PageManager;

@Listeners(TestListener.class)
public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void beforeClass() {

        playwright = Playwright.create();

        String browserName =
                ConfigReader.getProperty("browser");

        switch (browserName.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox()
                        .launch(new BrowserType.LaunchOptions());
                break;

            case "webkit":
                browser = playwright.webkit()
                        .launch(new BrowserType.LaunchOptions());
                break;

            default:
                browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions());
        }
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