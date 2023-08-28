package core

import org.openqa.selenium.remote.DesiredCapabilities

class ProjectCapabilities {

    companion object{
        fun androidCapabilities() : DesiredCapabilities {
            val caps  = DesiredCapabilities()
            caps.setCapability("platformName", "Android")
            caps.setCapability("udid", "emulator-5554")
            caps.setCapability("app", System.getProperty("user.dir")+"/src/test/resources/v7.32.1_release.apk")
            caps.setCapability("appPackage", "com.wallet.crypto.trustapp")
            caps.setCapability("appActivity", "com.wallet.crypto.trustapp.ui.start.activity.RootHostActivity")
            caps.setCapability("unlockType", "pin")
            caps.setCapability("unlockKey", "111111")
            return caps
        }
    }
}