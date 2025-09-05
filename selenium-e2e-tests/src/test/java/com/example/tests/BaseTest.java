

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import com.example.utils.WebDriverUtils;

public class BaseTest {
	protected WebDriver driver;

	@Before
	public void setUp() {
		driver = WebDriverUtils.createDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}