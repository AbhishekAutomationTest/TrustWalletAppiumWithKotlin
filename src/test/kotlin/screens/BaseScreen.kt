package screens

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.test.assertEquals

open class BaseScreen(val driver: AppiumDriver<MobileElement>, val wait: WebDriverWait) {
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @AndroidFindBy(id = "main_bottom_navigation")
    lateinit var bottom_navigation: WebElement

    @AndroidFindBy(id = "alertTitle")
    lateinit var alertTitle: WebElement

    @AndroidFindBy(id = "android:id/button1")
    lateinit var alertCTA: WebElement

    @AndroidFindBy(id = "android:id/content")
    lateinit var contentWidget: WebElement

    fun handleDeviceSecurityAlertIfNeeded() {
        val DEVICE_SECURITY_COMPROMISED_MESSAGE = "DEVICE SECURITY COMPROMISED"
        var isPopupVisible = false
        try {
            isPopupVisible = wait.until(object : ExpectedCondition<Boolean> {
                override fun apply(driver: WebDriver?): Boolean? {
                    return alertTitle.isDisplayed && alertTitle.text.contains(DEVICE_SECURITY_COMPROMISED_MESSAGE)
                }
            })
        }
        catch (e: TimeoutException){
            println("device security compromise popup not visible. moving ahead without handling..")
        }

        if(isPopupVisible) {
            if (alertTitle.isDisplayed && alertTitle.text.contains(DEVICE_SECURITY_COMPROMISED_MESSAGE)){
                alertCTA.click()
            }
        }
    }

    fun waitToBeVisible() {
        wait.until(object : ExpectedCondition<Boolean> {
            override fun apply(driver: WebDriver?): Boolean {
                return contentWidget.isEnabled
            }
        })
    }

    fun expectBottomNavToBeVisible() {
        assertEquals(bottom_navigation.isDisplayed && bottom_navigation.isEnabled, true)
    }

    fun enterPasscode(passcode: String) {
        for(char in passcode){
            driver.keyboard.pressKey("1")
        }
    }
}


