package tests

import core.BaseTest
import org.testng.annotations.Test

class NewWalletTests : BaseTest() {

    @Test
    fun verifyCreateNewWalletFlow(){
        guestUserScreen.waitToBeVisible()
        guestUserScreen.selectCreateNewWalletOption()
        guestUserScreen.enterPasscode("111111")
        //passcodeScreen.enterPasscode("111111")
    }
}