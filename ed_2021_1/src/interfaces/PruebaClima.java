package interfaces;

import registros.clima.InterfazPrediccionClima;

/**
 * Realiza las pruebas de prediccion del clima
 * @author Elias Beltran y Juventino Aguilar
 */
public class PruebaClima {
    public static void main(String[] args) {
        InterfazPrediccionClima interfazClima = new InterfazPrediccionClima();
        interfazClima.generarPrediccion();
        interfazClima.init();
    }
}
