package interfaces;

import ednolineal.Matriz2D;
import ednolineal.Matriz2DNumerica;
import entradasalida.SalidaEstandar;
import utilerias.neuronas.RedNeuronal;
import utilerias.neuronas.funciones.FuncionActivacion;
import utilerias.neuronas.funciones.FuncionLineal;
import utilerias.neuronas.funciones.FuncionLogSigmoid;
import java.lang.Math;

import edlineal.ArregloNumerico;

/**
* PruebaRedNeuronal
*/
public class PruebaRedNeuronal {

	public static void main(String[] args) {
		FuncionActivacion funcionLogSigmoid = new FuncionLogSigmoid();
		FuncionActivacion funcionLineal = new FuncionLineal();
		RedNeuronal red = new RedNeuronal(1);

		Matriz2DNumerica w1 = new Matriz2DNumerica(2, 1);
		w1.cambiarInfo(0, 0, -0.27);
		w1.cambiarInfo(1, 0, -0.41);

		Matriz2DNumerica b1 = new Matriz2DNumerica(2, 1);
		b1.cambiarInfo(0, 0, -0.48);
		b1.cambiarInfo(1, 0, -0.13);

		Matriz2DNumerica w2 = new Matriz2DNumerica(1, 2);
		w2.cambiarInfo(0, 0, 0.09);
		w2.cambiarInfo(0, 1, -0.17);

		Matriz2DNumerica b2 = new Matriz2DNumerica(1, 1);
		b2.cambiarInfo(0, 0, 0.48);

		red.agregarCapa(2, funcionLogSigmoid, w1, b1);
		red.agregarCapa(1, funcionLineal, w2, b2);

		Matriz2DNumerica entradaInicial = new Matriz2DNumerica(1, 1, 1.0);
		Matriz2DNumerica salidaEsperada = new Matriz2DNumerica(1, 1);
		double gradoAprendizaje = 0.1;

		for (int i = 0; i < 500000; i++) {
			double input = Math.random()*4.0-2.0;
			double valorEsperado = 1 + Math.sin((Math.PI / 4) * input);
			entradaInicial.cambiarInfo(0, 0, input);
			salidaEsperada.cambiarInfo(0, 0, valorEsperado);
			red.entrenar(entradaInicial, salidaEsperada, gradoAprendizaje);
		}
		double valorEsperado = 1 + Math.sin((Math.PI / 4) * 1.0);
		entradaInicial.cambiarInfo(0, 0, 1.0);
		SalidaEstandar.consola("Valor correcto: " + valorEsperado + "\n");
		SalidaEstandar.consola("Valor obtenido\n");
		red.realizarForward(entradaInicial).imprimirR();
	}
}
