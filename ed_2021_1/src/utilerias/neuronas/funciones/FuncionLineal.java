package utilerias.neuronas.funciones;

import ednolineal.Matriz2DNumerica;

/**
 * FuncionLineal
 */
public class FuncionLineal implements FuncionActivacion {

	public Matriz2DNumerica funcionActivadora(Matriz2DNumerica variableIndependiente) {
		return variableIndependiente.clonar();
	}

	public Matriz2DNumerica funcionDerivada(Matriz2DNumerica variableIndependiente) {
		Matriz2DNumerica resultadoDerivada = new Matriz2DNumerica(variableIndependiente.renglones(), variableIndependiente.columnas(), 0.0);
		for (int renglon = 0; renglon < resultadoDerivada.renglones(); renglon++) {
			resultadoDerivada.cambiarInfo(renglon, renglon, 1.0);
		}
		return resultadoDerivada;
	}
}
