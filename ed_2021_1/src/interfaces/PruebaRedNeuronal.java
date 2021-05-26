package interfaces;

import registros.neuronas.AdministracionRedesNeuronales;

/**
* PruebaRedNeuronal
*/
public class PruebaRedNeuronal {

	public static void main(String[] args) {
		AdministracionRedesNeuronales adminRed = new AdministracionRedesNeuronales();
		adminRed.crearRedNeuronal();
		adminRed.entrenarRed();
		adminRed.realizarPrueba();
		adminRed.graficarResultados();
	}
}
