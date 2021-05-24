package utilerias.neuronas.funciones;

import ednolineal.Matriz2DNumerica;
import java.lang.Math;

/**
 * FuncionLogSigmoid
 */
public class FuncionLogSigmoid {

	public Matriz2DNumerica funcionActivadora(Matriz2DNumerica variableIndependiente) {
		Matriz2DNumerica resultado = variableIndependiente.clonar();

		for (int renglon = 0; renglon < resultado.renglones(); renglon++) {
			for (int columna = 0; columna < resultado.columnas(); columna++) {
				double valorActual = (double) resultado.obtenerInfo(renglon, columna);
				resultado.cambiarInfo(renglon, columna, logSigmoid(valorActual));
			}
		}

		return resultado;
	}

	private double logSigmoid(double n) {
		double resultado = 1.0;
		resultado /= (1.0 + (Math.pow(Math.E, n*-1)));
		return resultado;
	}

	public Matriz2DNumerica funcionDerivada(Matriz2DNumerica variableIndependiente) {
		Matriz2DNumerica resultado = variableIndependiente.clonar();

		for (int renglon = 0; renglon < resultado.renglones(); renglon++) {
			for (int columna = 0; columna < resultado.columnas(); columna++) {
				double valorActual = (double) resultado.obtenerInfo(renglon, columna);
				double nuevoValor = (1 - logSigmoid(valorActual))*(logSigmoid(valorActual));
				resultado.cambiarInfo(renglon, columna, nuevoValor);
			}
		}

		return resultado;
	}
}
