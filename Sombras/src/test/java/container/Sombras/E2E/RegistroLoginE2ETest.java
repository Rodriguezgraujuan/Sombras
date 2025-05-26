package container.Sombras.E2E;

import container.Sombras.Repositorio.UsuarioRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // Para que levante el servidor en puerto fijo (8080)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistroLoginE2ETest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080"; // Asegúrate que el servidor esté corriendo
    private final String email = "usuario" + System.currentTimeMillis() + "@test.com";
    private final String password = "Password123!";


    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        driver.get(baseUrl + "/");

        // Ir a formulario de registro
        WebElement switchToRegister = wait.until(ExpectedConditions.elementToBeClickable(By.id("switchToRegister")));
        switchToRegister.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("registerForm")));

        // Completar formulario de registro
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys("TestUser");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("newEmail"))).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("newPassword"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmedPassword"))).sendKeys(password);


        // Hacer click en botón de registrar
        WebElement registerButton = driver.findElement(By.cssSelector("#registerForm button[type='submit']"));
        registerButton.click();

        // Esperar mensaje de éxito
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("registerSuccessMessage")));
        Assertions.assertTrue(successMsg.getText().contains("Registro exitoso"));

        // Volver a login (click en el enlace)
        WebElement switchToLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("switchToLogin")));
        switchToLogin.click();

        // Completar formulario de login
        wait.until(ExpectedConditions.elementToBeClickable(By.name("email"))).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);

        // Hacer click en botón de login
        WebElement loginButton = driver.findElement(By.cssSelector("#loginForm button[type='submit']"));
        loginButton.click();

        // Verificar que se redirige a /home
        wait.until(ExpectedConditions.urlContains("/home"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/home"));
    }
}
