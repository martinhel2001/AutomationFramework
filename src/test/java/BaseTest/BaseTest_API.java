package BaseTest;

import org.testng.annotations.Listeners;
import utils.Listeners.TestListenerAPI;
import utils.Listeners.TestListenerUI;

@Listeners(TestListenerAPI.class)
public class BaseTest_API extends BaseTest{
}
