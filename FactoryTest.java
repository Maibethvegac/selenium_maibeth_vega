package Examen_Octubre;

import org.testng.annotations.Factory;

//1. Crear una fábrica que ejecute 2 veces la clase de test.
    public class FactoryTest {
        @Factory
        public Object[] fabricaTest(){
            return new Object[]{
                new PruebaMailChimp(),
                new PruebaMailChimp(),
            };
        }
}
