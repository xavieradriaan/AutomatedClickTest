package desafioClick;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

public class Desafio {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
   
        WebDriverManager.chromedriver().setup();

        // Deshabilitar montón de notificaciones y configuraciones emergentes inservibles...
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-backgrounding-occluded-windows");
        options.addArguments("--disable-client-side-phishing-detection");
        options.addArguments("--disable-component-update");
        options.addArguments("--disable-domain-reliability");
        options.addArguments("--disable-features=AudioServiceOutOfProcess");
        options.addArguments("--disable-hang-monitor");
        options.addArguments("--disable-ipc-flooding-protection");
        options.addArguments("--disable-prompt-on-repost");
        options.addArguments("--disable-renderer-backgrounding");
        options.addArguments("--disable-sync");
        options.addArguments("--metrics-recording-only");
        options.addArguments("--safebrowsing-disable-auto-update");
        options.addArguments("--enable-automation");
        options.addArguments("--password-store=basic");
        options.addArguments("--use-mock-keychain");
        options.addArguments("--user-data-dir=/tmp/chrome-user-data"); // Usar un perfil de usuario temporal

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Establecer el tamaño de la ventana
        driver.manage().window().setSize(new Dimension(1024, 768)); // Tamaño de ventana personalizado
    }

    @Test
    public void testClickDifferentColor() {
        // Navegar a la página del desafío
        driver.get("https://zzzscore.com/color/en/");

        long endTime = System.currentTimeMillis() + 60000; // 60 segundos desde ahora

        while (System.currentTimeMillis() < endTime) {
            // Encuentra todos los elementos con el selector CSS .main
            List<WebElement> elements = driver.findElements(By.cssSelector(".main"));
            System.out.println("Número de elementos encontrados: " + elements.size());

            // Hacer clic en todos los elementos encontrados
            for (WebElement element : elements) {
                element.click();
                try {
                    Thread.sleep(50); // Esperar 50 ms para realizar el siguiente clic
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}