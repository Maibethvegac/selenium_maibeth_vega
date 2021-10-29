package Simulacro;

public class DataProvider {

   @org.testng.annotations.DataProvider (name="e-mail")
    public Object[][] datosParaBooking(){
        return new Object[][]{
                {"","CASO-1_e-MAIL_VACIO"},
                {"testtest.com","CASO-2_e-MAIL_INVALIDO"},
                {"qewretryu6hjfghdgdgfgfhghyjfghgjhkgghjuyuiuiiiiiiiiiiiiiiiiiiijgfhuyjyjhkjkj","CASO-3_e-MAIL_CON_LONGITUD_MAX"},
        };
    }
}
