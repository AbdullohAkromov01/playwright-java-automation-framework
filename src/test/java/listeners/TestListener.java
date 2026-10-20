package listeners;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PageManager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {

        Page page = PageManager.getPage();

        if (page == null) {
            System.out.println("Page is null");
            return;
        }

        String screenshotPath =
                "screenshots/" + result.getName() + ".png";

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(screenshotPath))
                        .setFullPage(true)
        );

        try (InputStream is =
                     new FileInputStream(screenshotPath)) {

            Allure.attachment(
                    "Failure Screenshot",
                    is
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Current URL: " + page.url());
        System.out.println("Test failed: " + result.getName());
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASSED: " + result.getName());
    }
}
