package screens

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

class WelcomeScreen(driver: AppiumDriver<MobileElement>, wait: WebDriverWait ): BaseScreen(driver, wait) {

    @AndroidFindBy(id = "new_account_action")
    lateinit var getStartedButton: WebElement

    @AndroidFindBy(id = "add_wallet_intro")
    lateinit var onboardingPageLayout: WebElement
}