package desafioClick;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Desafio {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurar WebDriverManager para manejar el driver de Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testClickDifferentColor() {
        // Navegar a la página del desafío
        driver.get("https://zzzscore.com/color/en/");

        long endTime = System.currentTimeMillis() + 60000; // 60 segundos desde ahora

        while (System.currentTimeMillis() < endTime) {
            // Encuentra todos los elementos de color usando XPath
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='main']"));
            System.out.println("Número de elementos encontrados: " + elements.size());

            // Lógica para encontrar el elemento de color diferente
            WebElement differentColorElement = findDifferentColorElement(elements);

            if (differentColorElement != null) {
                System.out.println("Elemento encontrado: " + differentColorElement.getCssValue("background-color"));
                differentColorElement.click();
                try {
                    Thread.sleep(1000); // Esperar 1 segundo para ver si el clic se realiza
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se encontró un elemento de color diferente.");
            }
        }
    }

    private WebElement findDifferentColorElement(List<WebElement> elements) {
        // Implementar la lógica para encontrar el elemento de color diferente
        // Esto puede variar dependiendo de cómo se representen los colores en la página
        // Ejemplo básico:
        String baseColor = elements.get(0).getCssValue("background-color");
        for (WebElement element : elements) {
            System.out.println("Color del elemento: " + element.getCssValue("background-color"));
            if (!element.getCssValue("background-color").equals(baseColor)) {
                return element;
            }
        }
        return null;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}