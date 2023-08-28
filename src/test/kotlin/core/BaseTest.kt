package core

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeTest
import screens.*
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

abstract class BaseTest {

    private val androidCaps = ProjectCapabilities.androidCapabilities()
    private lateinit var driver: AppiumDriver<MobileElement>
    private lateinit var wait: WebDriverWait

    //page Objects
    protected lateinit var welcomeScreen: WelcomeScreen
    protected lateinit var guestUserScreen: GuestUserScreen
    protected lateinit var passcodeScreen: PasscodeScreen
    protected lateinit var bioMetricLoginScreen: BioMetricLoginScreen
    protected lateinit var secretPhraseScreen: SecretPhraseScreen
    protected lateinit var consentScreen: ConsentScreen
    protected lateinit var recoveryPhaseScreen: RecoveryPhaseScreen
    protected lateinit var confirmRecoveryScreen: ConfirmRecoveryScreen
    protected lateinit var storiesScreen: StoriesScreen
    protected lateinit var walletHomeScreen: WalletHomeScreen

    @BeforeTest
    fun startServer() {
    }

    @BeforeMethod
    fun setUp() {
        val url = URL("http://127.0.0.1:4723/wd/hub")
        driver = AppiumDriver(url, androidCaps)
        driver.context("NATIVE_APP")

        //global implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        //global explicit wait
        wait = WebDriverWait(driver, 5)

        initializePageObjects(driver, wait)
        handleOnboardingFlow()

    }

    @AfterMethod
    fun clear() {
        //driver.close()
    }

    @AfterTest
    fun tearDown() {
    }

    fun initializePageObjects(driver: AppiumDriver<MobileElement>, wait: WebDriverWait) {
        welcomeScreen = WelcomeScreen(driver, wait)
        walletHomeScreen = WalletHomeScreen(driver, wait)
        guestUserScreen = GuestUserScreen(driver, wait)
       passcodeScreen = PasscodeScreen(driver, wait)
        bioMetricLoginScreen = BioMetricLoginScreen(driver, wait)
        secretPhraseScreen = SecretPhraseScreen(driver, wait)
        consentScreen = ConsentScreen(driver, wait)
        recoveryPhaseScreen = RecoveryPhaseScreen(driver, wait)
        confirmRecoveryScreen = ConfirmRecoveryScreen(driver, wait)
        storiesScreen = StoriesScreen(driver, wait)
    }

    fun handleOnboardingFlow() {
        welcomeScreen.waitToBeVisible()
        welcomeScreen.getStartedButton.click()
        welcomeScreen.handleDeviceSecurityAlertIfNeeded()
        walletHomeScreen.expectBottomNavToBeVisible()
        assertEquals(walletHomeScreen.bottom_navigation.isDisplayed, true)
    }
}