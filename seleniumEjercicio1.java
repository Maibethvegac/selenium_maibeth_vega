package clase9.practico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class seleniumEjercicio1 {

    @Test
    public WebDriver abrirUrlEnBrowser (String URL){
        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    //Ejercicio 14
    //Subir este ejercicio a github en una rama nueva llamada selenium/ejercicio1.
    // Crear una pull request y agregar a seleniumcurso como revisor.
    //Crear un método con un nombre a seleccionar
    //Acceder a Netflix: https://www.netflix.com/uy/
    //Mostrar los elementos h1 y h2 que hay en el sitio
    //Refrescar la página
    //Mostrar el texto de los botones que se encuentran en la página
    //Mostrar la cantidad de elementos div que contiene el sitio
    //Obtener y mostrar el título de la página
    //Mostrar la cantidad de elementos de tipos link

    @Test
    public void irANetflix(){
        //Acceder a Netflix: https://www.netflix.com/uy/
        WebDriver driver = abrirUrlEnBrowser("https://www.netflix.com/uy/");
        //Maximizar la página
        driver.manage().window().maximize();

        //Mostrar los elementos h1 y h2 que hay en el sitio
        System.out.println("------------------ H1 ---------------------");
        List<WebElement> listasH1 = driver.findElements(By.tagName("h1"));
        System.out.println("La cantidad de H1 es: " + listasH1.size());
        for (WebElement totalH1 : listasH1){
            System.out.println("H1 ---> " + totalH1.getText());
        }

        System.out.println("------------------ H2 ---------------------");
        List<WebElement> listasH2 = driver.findElements(By.tagName("h2"));
        System.out.println("La cantidad de total de H2 es: " + listasH2.size());
        int contH2 = 0;
        for (WebElement totalH2 : listasH2){
            if (totalH2.getText().isEmpty() == false){
                System.out.println("H2 ---> " + totalH2.getText());
                contH2++;
            }
        }
        System.out.println("La cantidad de total de H2 con texto es: " + contH2);

        //Refrescar la página
        driver.navigate().refresh();

        //Mostrar el texto de los botones que se encuentran en la página
        System.out.println("------------------ Botones ---------------------");
        List<WebElement> listaBtn = driver.findElements(By.tagName("button"));
        for (WebElement textoBtn : listaBtn){
            if (textoBtn.getText().isEmpty() == false){
                System.out.println("Boton ->> " + textoBtn.getText());
            }
        }

        //Mostrar la cantidad de elementos div que contiene el sitio
        System.out.println("------------------ Elementos DIV ---------------------");
        List<WebElement> listaDiv = driver.findElements(By.tagName("div"));
        System.out.println("La cantidad de elementos Div que contiene el sitio es: " + listaDiv.size());

        //Obtener y mostrar el título de la página
        System.out.println("------------------ Título de la Página ---------------------");
        System.out.println("El título de la página es: " + driver.getTitle());

        //Mostrar la cantidad de elementos de tipos link
        System.out.println("------------------ Elementos tipo Link ---------------------");
        List<WebElement> listaLink = driver.findElements(By.tagName("a"));
        System.out.println("La cantidad de elementos tipo link es:" +listaLink.size());

    }
}
