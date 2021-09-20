package Clase12.PracticoClase12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testngSalesforce {
    //Ejercicio con priority
    //Agregar priority descendiente a los tests.
    //Salesforce con Testng
    //Ejercicio
    //Crear una clase llamada testngSalesforce
    //Crear una variable final estática que acceda a “https://login.salesforce.com/”
    //public static final String SALEFORCE_URL = "https://login.salesforce.com/";
    public WebDriver driver;
    public static final String SALEFORCE_URL = "https://login.salesforce.com/";


    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SALEFORCE_URL);
        driver.manage().window().maximize();
    }

    //Ejercicio
    //Crear un método llamado validateSalesforceLogoTest
    //El test debe mostrar el tagName del id logo en pantalla y su atributo “alt’
    //El orden de prioridad de este test, debe ser 1
    @Test (priority = 1 , enabled = false)
    public void validateSalesforceLogoTest(){
        WebElement tagName_Id_Name = driver.findElement(By.id("logo"));
        System.out.println(tagName_Id_Name.getTagName());
        System.out.println(tagName_Id_Name.getAttribute("alt"));

    }

    //Ejercicio
    //Crear un método llamado RememberMeIsSelected
    //Ingresar al sitio: https://login.salesforce.com/?locale=eu
    //Hacer click en el botón de Remember me
    //Validar que el checkbox está seleccionado
    //El orden de prioridad de este test, debe ser 4

    @Test (priority = 4)
    public void RememberMeIsSelected(){
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='rememberUn']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
    }

    //Ejercicio
    //Método FooterIsValid
    //Validar que el footer tenga “All rights reserved”
    //El orden de prioridad de este test, debe ser 2
    @Test (priority = 2)
    public void FooterIsValid(){
        WebElement footer = driver.findElement(By.xpath("//div[@id='footer']"));
        Assert.assertTrue(footer.getText().contains("Reservados todos los derechos"));
    }

    //Ejercicio
    //Ignorar uno de los 3 tests a elección (enabled = false)
    //@Test (enabled = false)

    //Ejercicio
    //Crear una clase llamada testng.xml
    //Esta clase debe permitir correr todos los tests de la clase testngSalesforce


    //*********************** Ejercicio GIT **********************
    //Ejercicio
    //Crear un método llamado método LoginFailureTest
    //En el sitio de salesforce: "https://login.salesforce.com/?locale=eu"
    //Validar que se encuentre el logo en el sitio (utilizar un WebElement)
    //Completar el username con “test@test.com”
    //Completar el campo Password con “123466”
    //Hacer click en Login
    //Imprimir en pantalla el mensaje de error
    //El orden de prioridad de este test, debe ser 3
    @Test (priority = 3)
    public void LoginFailureTest(){
        driver.get("https://login.salesforce.com/?locale=eu");

        WebElement validarLogo = driver.findElement(By.id("logo"));
        Assert.assertTrue(validarLogo.isDisplayed());

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("test@test.com");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123466");

        driver.findElement(By.xpath("//input[@id='Login']")).click();

        WebElement msjError = driver.findElement(By.id("error"));
        System.out.println("El msj de error es -> " + msjError.getText());
        Assert.assertEquals(msjError.getText(),"Your access to salesforce.com has been disabled by your system administrator. Please contact your Administrator for more information.");
    }

    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
