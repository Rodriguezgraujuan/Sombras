package container.Sombras.E2E;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RegistroLoginE2ETest {

    private WebDriver driver;
    private final String baseUrl = "http://localhost:8080"; // Ajustá el puerto si es necesario
    private final String email = "usuario" + System.currentTimeMillis() + "@test.com";
    private final String password = "Password123!";

    @BeforeAll
    void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testRegistroYLogin() {
        // --- Registro ---
        driver.get(baseUrl + "/register");

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(password);
        driver.findElement(By.tagName("form")).submit();

        // Debería redirigir al login
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

        // --- Login ---
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.tagName("form")).submit();

        // Debería redirigir al home
        Assertions.assertTrue(driver.getCurrentUrl().contains("/home"));
    }
}
