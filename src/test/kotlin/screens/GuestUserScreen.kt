package screens

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

class GuestUserScreen(driver: AppiumDriver<MobileElement>, wait: WebDriverWait ): BaseScreen(driver, wait) {

    @FindBy(xpath = "//android.widget.TextView[@text='I don’t have a wallet']")
    lateinit var createNewWalletOption: WebElement

    fun selectCreateNewWalletOption() {
        wait.until(object : ExpectedCondition<Boolean> {
            override fun apply(driver: WebDriver?): Boolean {
                return createNewWalletOption.isEnabled
            }
        })
        createNewWalletOption.click()
    }
}