package Clase3;

public class ejercicio3 {

    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Ejercicio 1 - Calcular el doble de un número");
        int doble = calcularDoble(1000);
        System.out.println("El doble del número es: " + doble);

    }

    //Práctico 3: Métodos
    //Ejercicio 1
    //Escribir un método que reciba un número y retorne su doble
    public static int calcularDoble(int num){
        return num*2;
    }
}
