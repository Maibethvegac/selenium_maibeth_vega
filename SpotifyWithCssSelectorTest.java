package Clase11.PracticoClase11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SpotifyWithCssSelectorTest {

    public WebDriver getChromeDriver (String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }
    //**************************** Ejercicio GIT ****************************
    //Ejercicio 9
    //Crear una clase llamada SpotifyWithCssSelectorTest
    //Crear un método llamado spotifyByPlaceHolderTest
    //Ingresar al sitio: https://www.spotify.com/uy/signup/
    //Completar todos los campos con Css Selector placeholder
    //Crear una rama partiendo de dev llamada practico11/ejercicio7 y subir los cambios del archivo

    @Test
    public void spotifyByPlaceHolderTest() throws InterruptedException {
        WebDriver driver = getChromeDriver("https://www.spotify.com/uy/signup/");
        driver.manage().window().maximize();

        Thread.sleep(1500);
        driver.findElement(By.cssSelector("[placeholder='Introduce tu correo electrónico.']")).sendKeys("prueba@test.com");
        driver.findElement(By.cssSelector("[placeholder='Vuelve a introducir tu correo electrónico.']")).sendKeys("prueba@test.com");
        driver.findElement(By.cssSelector("[placeholder='Crea una contraseña.']")).sendKeys("prueba123");
        driver.findElement(By.cssSelector("[placeholder='Introduce un nombre de perfil.']")).sendKeys("Ejercicio Git - Práctico 11");
        driver.findElement(By.cssSelector("[placeholder='DD']")).sendKeys("07");
        driver.findElement(By.cssSelector("[name='month']")).sendKeys("Septiembre");
        driver.findElement(By.cssSelector("[placeholder='AAAA']")).sendKeys("1989");
        driver.findElement(By.xpath("(//span[@class='Indicator-sc-16vj7o8-0 iBjMfh'])[2]")).click();
    }
}
