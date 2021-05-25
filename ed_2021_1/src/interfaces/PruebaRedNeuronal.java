package interfaces;

import edlineal.ListaLigada;
import ednolineal.Matriz2DNumerica;
import entradasalida.SalidaEstandar;
import graficas.GraficaLineaXY;
import utilerias.neuronas.RedNeuronal;
import utilerias.neuronas.funciones.FuncionActivacion;
import utilerias.neuronas.funciones.FuncionLineal;
import utilerias.neuronas.funciones.FuncionLogSigmoid;

/**
* PruebaRedNeuronal
*/
public class PruebaRedNeuronal {

	public static void main(String[] args) {
		//Generando Red Neuronal
		FuncionActivacion funcionLogSigmoid = new FuncionLogSigmoid();
		FuncionActivacion funcionLineal = new FuncionLineal();
		RedNeuronal red = new RedNeuronal(1);

		red.agregarCapa(2, funcionLogSigmoid);
		red.agregarCapa(1, funcionLineal);

		//Entradas de la funcion a simular
		Matriz2DNumerica entradaInicial = new Matriz2DNumerica(1, 1, 1.0);
		Matriz2DNumerica salidaEsperada = new Matriz2DNumerica(1, 1);

		double gradoAprendizaje = 0.1;

		ListaLigada ejeXFuncion = new ListaLigada();
		ListaLigada ejeYFuncion = new ListaLigada();
		ListaLigada ejeXRed = new ListaLigada();
		ListaLigada ejeYRed = new ListaLigada();

		for (int i = 0; i < 5000; i++) {
			double input = Math.random()*4.0-2.0;
			double valorEsperado = 1 + Math.sin((Math.PI / 4) * input);
			entradaInicial.cambiarInfo(0, 0, input);
			salidaEsperada.cambiarInfo(0, 0, valorEsperado);
			red.entrenar(entradaInicial, salidaEsperada, gradoAprendizaje);
		}

		for (int i = 0; i < 5000; i++) {
			double input = Math.random()*4.0-2.0;
			double valorEsperado = 1 + Math.sin((Math.PI / 4) * input);
			entradaInicial.cambiarInfo(0, 0, input);
			ejeXFuncion.agregar(input);
			ejeYFuncion.agregar(valorEsperado);
			ejeXRed.agregar(input);
			Matriz2DNumerica resultadoRed = red.realizarForward(entradaInicial);
			ejeYRed.agregar(((double)resultadoRed.obtenerInfo(0,0)));
		}

		ListaLigada paresXY = new ListaLigada();
		paresXY.agregar(ejeXFuncion);
		paresXY.agregar(ejeYFuncion);
		paresXY.agregar(ejeXRed);
		paresXY.agregar(ejeYRed);

		ListaLigada keys = new ListaLigada();
		keys.agregar("Funcion original");
		keys.agregar("Funcion red neuronal");

		GraficaLineaXY graficaLinea=new GraficaLineaXY();
		graficaLinea.init(paresXY, keys,"Ejemplo Redes Neuronales","Dato insertado","Resultado");
	}
}
