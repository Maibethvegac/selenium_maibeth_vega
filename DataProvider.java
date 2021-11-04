package Examen_Octubre;

public class DataProvider {

    @org.testng.annotations.DataProvider (name = "email")
    public Object[][] datosParaBooking(){
        return new Object[][]{
                {"maibethvega.a@gmail.com","holamundo","CASO-1_e-MAIL_VALIDO"},
                {"maibethvega.b@gmail.com","holamundo","CASO-2_e-MAIL_VALIDO"},
                {"maibethvega.c@gmail.com","holamundo","CASO-3_e-MAIL_VALIDO"},
        };
    }
}
