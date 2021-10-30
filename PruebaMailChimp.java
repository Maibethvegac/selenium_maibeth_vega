package Examen_Octubre;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

// Prueba de Maibeth Vega - Selenium Básico - 30 Octubre 2021
public class PruebaMailChimp {
    //1. Crear una clase llamada PruebaMailChimp
    //2. Ingresar a https://login.mailchimp.com/
    //3. Maximizar la pagina al iniciar cada test
    //4. Agregar una espera implícita al iniciar cada test
    //5. Antes de comenzar cada test, se debe esperar 2 segundos a que carguen las cookies,
    // se deben aceptarlas y esperar 2 segundos antes de interactuar con el sitio
    //6. Usar testng para la creación de todos los tests.
    public WebDriver driver;
    public String SITE_URL = "https://login.mailchimp.com/";
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SITE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='onetrust-pc-btn-handler']")).click();
        driver.findElement(By.xpath("//*[@class='save-preference-btn-handler onetrust-close-btn-handler']")).click();
        Thread.sleep(2000);
    }


    //Caso de prueba 1:
    //Crear un test llamado validarTituloTest, que valide el título de la página con un assert.
    @Test (priority = 4)
    public void validarTituloTest(){
        String tituloPagina = driver.getTitle();
        System.out.println("********** Validando el título de la página con un Assert **********");
        System.out.println("El título de la página es ---> " + tituloPagina);
        Assert.assertEquals(tituloPagina,"Login | Mailchimp","Validando el título de la página con un Assert");
    }


    // Caso de prueba 2:
    //Crear un método de test que se llame iniciarSesionPageTest.
    //1. Validar que se encuentre el texto “Iniciar sesión” en el sitio. Utilizar asserts
    //2. Validar que esté el texto: Need a Mailchimp account?
    @Test (priority = 3)
    public void iniciarSesionPageTest(){
        String texto1 = "Iniciar sesión";
        String texto2 = "Need a Mailchimp account?";

       List<WebElement> textosPagina = driver.findElements(By.tagName("p"));

        boolean encontrar1 = false;
        for (WebElement p : textosPagina) {
            if (p.getText().isEmpty() == false) {
                System.out.println("Texto de P ---> " + p.getText());
                if (p.getText().contains(texto1)) {
                    encontrar1 = true;
                }
            }
        }
        Assert.assertTrue(encontrar1,"NO SE ENCONTRO EL TEXTO --> Iniciar sesión");

        boolean encontrar2 = false;
        for (WebElement p : textosPagina) {
            if (p.getText().isEmpty() == false) {
                if (p.getText().contains(texto2)) {
                    encontrar2 = true;
                }
            }
        }
        Assert.assertTrue(encontrar2,"NO SE ENCONTRO EL TEXTO --> Need a Mailchimp account?");
    }


    //Caso de prueba 3:
    //Crear un método de test llamado loginErrorTest que complete el campo del email con XXXXX@gmail.com
    // dentro de la sección de login. Haz click en Iniciar Sesión.
    //Validar con un assert que esté presente el mensaje de error:
    // “Looks like you forgot your password there”
    // y validar que el checkbox “Keep me logged” esté deseleccionado.
    @Test (priority = 2)
    public void loginErrorTest(){
        driver.findElement(By.id("username")).sendKeys("XXXXX@gmail.com");
        driver.findElement(By.xpath("//*[@class='button-large button-wide full-width p1 !margin-bottom--lv5']")).click();

        WebElement errorMSG = driver.findElement(By.xpath("//*[@id='empty-error']"));
        boolean encontrarError = false;
        String textoABucar = "Looks like you forgot your password there";
            if (errorMSG.getText().contains(textoABucar)) {
                encontrarError = true;
             }
            Assert.assertTrue(encontrarError,"NO SE ENCONTRO EL TEXTO --> Looks like you forgot your password there");

        WebElement checkboxLogin = driver.findElement(By.name("stay-signed-in"));
        //checkboxLogin.click();
        Assert.assertEquals(false,checkboxLogin.isSelected(),"checkbox “Keep me logged” NO esta deseleccionado");
    }


    //Caso de prueba 4:
    //Crear un método llamado fakeEmailTest.
    //Navegar hacia la sección de registro:
    // driver.navigate().to(“https://login.mailchimp.com/signup/”);
    //Aceptar las cookies (si aparecen) y esperar 2 segundos.
    //Se debe completar el campo de email que se encuentra dentro de la sección de Registro.
    // Asegurarse que la url contiene la palabra signup.
    // Se debe enviar un email que se genere de forma randómica
    // cada vez que se ejecute el test con la librería Faker.
    @Test (priority = 1)
    public void fakeEmailTest(){
        driver.navigate().to("https://login.mailchimp.com/signup/");

        String urlPagina = driver.getCurrentUrl();
        System.out.println("La Url de la pagina es ---> " + urlPagina);

        boolean urlContieneSignup = false;
        String palabraABuscar = "signup";
        if (urlPagina.contains(palabraABuscar)) {
            urlContieneSignup = true;
        }
        Assert.assertTrue(urlContieneSignup,"NO SE ENCONTRO LA PALABRA SINGUP EN LA URL");

        Faker faker = new Faker();
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
    }


    //Caso de prueba 5:
    //Crear un método llamado dataProviderEmailTest que complete el campo de email
    // que se encuentra dentro de la sección de login..
    // El dataProvider debe enviar 3 emails válidos al test.
    // La password debe ser definida como “holamundo”.
    // Hacer click en el botón para loguearse y  validar que se encuentre el mensaje
    // “Can we help you recover your username?” dentro del sitio.
    @Test (priority = 0, dataProvider = "email", dataProviderClass = DataProvider.class)
    public void dataProviderEmailTest(String unEmail, String unaPassword, String unCaso) throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys(unEmail);
        driver.findElement(By.id("password")).sendKeys(unaPassword);
        driver.findElement(By.xpath("//*[@class='button-large button-wide full-width p1 !margin-bottom--lv5']")).click();

        //NOTA: A VECES APARECE UN RECAPCHA Y AL NO SELECCIONARSE Y ELEGIR LAS IMAGENES QUE PIDE EL TEST FALLA.
        WebElement errorMsgLogin = driver.findElement(By.xpath("//*[@class='c-mediaBody--centered']"));

        boolean encontrarMsg = false;
        String msgABuscar = "Can we help you recover your username?";
        if (errorMsgLogin.getText().contains(msgABuscar)) {
            encontrarMsg = true;
        }
        Assert.assertTrue(encontrarMsg,"NO SE ENCONTRO EL MENSAJE ->> “Can we help you recover your username?” EN EL SITIO, NOTA: A VECES APARECE UN RECAPCHA Y AL NO SELECCIONARSE Y ELEGIR LAS IMAGENES QUE PIDE EL TEST FALLA.");
    }
    //1. Crear una fábrica que ejecute 2 veces la clase de test. ---> OK, se creo la clase FactoryTest.java


    //2. Todos los test deben cerrarse al finalizar su ejecución.
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }

    //3. Crear un archivo testng que permita ejecutar la clase de test. ->> OK.

    //4. Agregar un orden de ejecución descendente a los tests. -> OK, se le asigno priority a los test.
}
