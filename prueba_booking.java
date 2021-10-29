package Simulacro;

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

public class prueba_booking {
    //1. Crear una clase llamada prueba_booking
    //2. Ingresar a https://www.booking.com/
    //3. Maximizar la pagina al iniciar cada test
    //4. Agregar una espera implícita al iniciar cada test
    public WebDriver driver;
    public String SITE_URL = "https://www.booking.com/";
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SITE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
    }


    //5. Crear un test llamado validarTituloTest, que valide el título de la página con un assert.
    @Test (priority = 0)
    public void validarTituloTest(){
        String tituloPagina = driver.getTitle();
        System.out.println("********** Validando el título de la página con un Assert **********");
        System.out.println("El título de la página es ---> " + tituloPagina);
        Assert.assertEquals(tituloPagina,"Booking.com | Página oficial | Los mejores hoteles y alojamientos","Validando el título de la página con un Assert");
    }


    //6. Crear un test llamado mostarLinksTest. Crear un test que muestre los links presentes en el sitio
    @Test (priority = 1)
    public void mostarLinksTest(){
        List<WebElement> listaLinks = driver.findElements(By.tagName("a"));
        System.out.println("********** Links presentes en el sitio **********");
        for (WebElement link : listaLinks) {
            if (link.getText().isEmpty() == false) {
                System.out.println("Link ---> " + link.getText());
            }
        }
    }


    //7. Crear un test llamado mostarH1sTest. Mostrar los h1s que estén presentes en el sitio
    @Test (priority = 2)
    public void mostarH1sTest(){
        List<WebElement> listaH1s = driver.findElements(By.tagName("h1"));
        System.out.println("********** H1s del sitio **********");
        for (WebElement h1 : listaH1s) {
            if (h1.getText().isEmpty() == false) {
                System.out.println("Texto de H1 ---> " +h1.getText());
            }
        }
    }


    //8. Crear un test llamado buscarGenteViajeraTest que busque en todos los h2s,
    // si el texto “Conecta con gente viajera” está presente. (Utilizar un assert para validarlo)
    @Test (priority = 3)
    public void buscarGenteViajeraTest(){
        List<WebElement> listaH2s = driver.findElements(By.tagName("h2"));
        System.out.println("********** H2s del sitio **********");
        String textoABuscar= "Conecta con gente viajera";
        for (WebElement h2 : listaH2s) {
            if (h2.getText().isEmpty() == false) {
                System.out.println("Texto de H2 ---> " +h2.getText());
            }
        }
    } // No supe como validar con el ASSERT :(


    //9. Crear un test llamado registroUsuarioTests
    //1. Hacer click en “Inicia Sesión”. Ingrese un email generado desde la clase Faker,
    // haga click en Siguiente. Luego, valide con un assert, que el mensaje de error sea
    // “Parece que no existe ninguna cuenta vinculada a este e-mail. Puedes crear una cuenta para utilizar
    // nuestros servicios.”.
    //2. A continuación debe hacer click en el link en el link “Registrate”.
    //3. Luego, ingresar un email randomico (generado de la librería Fakers) y hacer click en “Empezar”
    //4. Ingresar  dos contraseñas que sean diferentes.
    //5. Validar que se despliegue un mensaje de error: “Las contraseñas no coinciden. Inténtalo de nuevo”.
    @Test (priority = 4)
    public void registroUsuarioTests() throws InterruptedException {
        driver.findElement(By.linkText("Iniciar sesión")).click();
        Faker faker = new Faker();
        driver.findElement(By.name("username")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.xpath("(//*[@class='_1jp30RWusTBQoML9GSCZ_C'])[2]")).click();

        driver.findElement(By.name("new_password")).sendKeys("Mai1234567890");
        driver.findElement(By.name("confirmed_password")).sendKeys("M0001234567890");
        driver.findElement(By.xpath("(//*[@class='_1jp30RWusTBQoML9GSCZ_C'])[2]")).click();

        WebElement errorMsg = driver.findElement(By.xpath("//*[@id='confirmed_password-note']"));
        System.out.println("El mensaje de error es ---> " +errorMsg.getText());

        Assert.assertEquals(errorMsg.getText(),"Las contraseñas que ingresaste no coinciden. Volvé a intentarlo.","COnstraseñas con coinciden");
    } //NOTA: se ve que la página cambio xq no estaba para hacer los pasos según el enunciado, hice lo que pude.

    
    //10. Crear un test llamado crearCuentaTest. test@test.com.
    // Hacer click en empezar y validar que el mensaje de error sea
    // “Ya tienes una cuenta de Booking.com registrada con la dirección de e-mail test@test.com.
    // Puedes iniciar sesión directamente.”
    @Test (priority = 5)
    public void crearCuentaTest() throws InterruptedException {
        //NOTA: la página no esta para hacer este test.
        driver.findElement(By.xpath("(//*[@class='bui-button__text'])[4]")).click();
        driver.findElement(By.name("username")).sendKeys("test@test.com");
        driver.findElement(By.xpath("(//*[@class='_1jp30RWusTBQoML9GSCZ_C'])[2]")).click();
        WebElement desactivacionMsg = driver.findElement(By.xpath("//*[@class='nw-step-description']"));
        System.out.println("Mensaje de cuenta desactivada ---> " + desactivacionMsg.getText());
    } //NOTA: se ve que la página cambio xq no estaba para hacer los pasos según el enunciado, hice lo que pude.


    //11. Crear un archivo llamado testng.xml que ejecute todos los tests. ---> OK


    //12. Crear un data provider que provee 3 emails diferentes a ser utilizados en un test que trate de iniciar sesión.
    // Los emails deben ser inválidos y debe validarse el mensaje de error que se despliega en pantalla con un assert.
    // Se debe cerrar cada uno de los tests al finalizar
    @Test (priority =6, dataProvider = "e-mail" , dataProviderClass = DataProvider.class)
    public void iniciarSesion(String unEmail, String unCaso){
        driver.findElement(By.linkText("Iniciar sesión")).click();
        driver.findElement(By.name("username")).sendKeys(unEmail);
        driver.findElement(By.xpath("(//*[@class='_1jp30RWusTBQoML9GSCZ_C'])[2]")).click();

        WebElement errorMsg = driver.findElement(By.xpath("//*[@id='username-note']"));
        if(unCaso.equals("CASO-1_e-MAIL_VACIO")){
            Assert.assertEquals(errorMsg.getText(),"Ingresá tu dirección de e-mail");
            System.out.println("CASO-1_e-MAIL_VACIO pased!!!");
        } else if(unCaso.equals("CASO-2_e-MAIL_INVALIDO")){
            Assert.assertEquals(errorMsg.getText(),"Verificá que la dirección de e-mail que ingresaste sea correcta.");
            System.out.println("CASO-2_e-MAIL_INVALIDO pased!!!");
        } else if(unCaso.equals("CASO-3_e-MAIL_CON_LONGITUD_MAX")){
            Assert.assertEquals(errorMsg.getText(),"Verificá que la dirección de e-mail que ingresaste sea correcta.", "Error: mails no coinciden");
            System.out.println("CASO-3_e-MAIL_CON_LONGITUD_MAX pased!!!");
        } else {
            System.out.println("Caso no encontrado!!!");
        }
    }


    //13. Una vez finalizada la prueba, realizar un commit con los archivos: testng.xml,
    // el data provider y la clase llamada prueba_booking.


    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
