package utilerias.neuronas;

import ednolineal.Matriz2DNumerica;

/**
 * Interfaz de la cual se pueden implementar diferentes funciones
 * @author Elias Beltran y Juventino Aguilar
 */
public interface FuncionActivacion {

    Matriz2DNumerica funcionActivadora(Matriz2DNumerica variableIndependiente);

    Matriz2DNumerica funcionDerivada(Matriz2DNumerica sumatoria);
}
