package clase10.Practico10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class registrationFacebookTest {

    public WebDriver getChromeDriver (String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    //******** Ejercicio GIT *******
    //Ejercicio 9
    //Crear una nueva branch llamada prácticoSelenium y subir una clase llamada registrationFacebookTest,
    // con el siguiente ejercicio:
    //Crear un método de test llamado fullRegistrationTest
    //Ir a Facebook https://www.facebook.com/
    //Completar los campos de registro con los siguientes valores:
    //First Name: “John”
    //Last Name: “Smith”
    //Mobile: “5555555”
    //New Password: “123456789”
    //Birthday: Jun 26 1980
    //Gender: Male

    @Test
    public void fullRegistrationTest() throws InterruptedException {
        WebDriver driver = getChromeDriver("https://www.facebook.com/");
        driver.manage().window().maximize();

        Thread.sleep(1500);
        driver.findElement(By.linkText("Crear cuenta nueva")).click();

        Thread.sleep(1500);
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("reg_email__")).sendKeys("5555555");
        driver.findElement(By.id("password_step_input")).sendKeys("123456789");

        //Fecha de nacimiento
        WebElement elementoDias = driver.findElement(By.name("birthday_day"));
        Select dropdownDias = new Select(elementoDias);
        dropdownDias.selectByValue("26");

        WebElement elementoMeses = driver.findElement(By.name("birthday_month"));
        Select dropdownMeses = new Select(elementoMeses);
        dropdownMeses.selectByVisibleText("jun");

        WebElement elementosAnios = driver.findElement(By.name("birthday_year"));
        Select dropdownAnios = new Select(elementosAnios);
        dropdownAnios.selectByValue("1980");

        //Sexo
        List<WebElement> listaGenero = driver.findElements(By.name("sex"));
        WebElement male = listaGenero.get(1);
        male.click();
    }
}
