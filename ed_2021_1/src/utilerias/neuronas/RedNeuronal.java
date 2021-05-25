package utilerias.neuronas;

import edlineal.ColaLista;
import edlineal.ListaDobleLigada;
import ednolineal.Matriz2DNumerica;
import utilerias.neuronas.funciones.FuncionActivacion;

/**
 * Conjunto de capas que continen neuronas para generar predicciones y clasificaciones
 * @author Elias Beltran y Juventino Aguilar
 */
public class RedNeuronal {
    protected ListaDobleLigada capas;
    protected int numEntradas;

		public RedNeuronal(int numEntradas) {
			this.numEntradas = numEntradas;
			capas = new ListaDobleLigada();
		}

    public void agregarCapa(int numNeuronas, FuncionActivacion funcionActivacion){
        int numEntradasNuevaCapa = entradasNuevaCapa();
        CapaNeuronas capaNueva = new CapaNeuronas(numEntradasNuevaCapa,numNeuronas, funcionActivacion);
        capas.agregar(capaNueva);
    }

    public void agregarCapa(int numNeuronas, FuncionActivacion funcionActivacion, Matriz2DNumerica pesos, Matriz2DNumerica bias){
			agregarCapa(numNeuronas, funcionActivacion);
			CapaNeuronas agregada = (CapaNeuronas) capas.getUltimo();
			agregada.setPesos(pesos);
			agregada.setBias(bias);
    }

    private int entradasNuevaCapa() {
        if(capas.vacia()){
            return numEntradas;
        }else{
            return ((CapaNeuronas)capas.getUltimo()).obtenerNumeroNeuronas();
        }
    }

    public Matriz2DNumerica realizarForward(Matriz2DNumerica entradaInicial){
        if(entradaInicial.renglones() != numEntradas || entradaInicial.columnas() != 1){
            return null;
        }
        capas.inicializarIteradorIzq();
        Matriz2DNumerica entradaIterativa = entradaInicial;
        while(capas.hayMasIzq()){
            entradaIterativa = ((CapaNeuronas)capas.obtenerSiguienteIzq()).calcularSalida(entradaIterativa);
        }
        return entradaIterativa;
    }

    public Matriz2DNumerica entrenar(Matriz2DNumerica entradaInicial, Matriz2DNumerica salidaEsperada, double gradoAprendizaje) {
        Matriz2DNumerica salidaObtenida = realizarForward(entradaInicial);
        realizarBackward(salidaObtenida, salidaEsperada, gradoAprendizaje, entradaInicial);
        return salidaObtenida;
    }

    private void realizarBackward(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada, double gradoAprendizaje, Matriz2DNumerica entradaInicial){
        capas.inicializarIteradorDer();
				ColaLista colaSensitividades = new ColaLista();
        Matriz2DNumerica sensitividadIterativa = calcularSensitivadadInicial(salidaObtenida,salidaEsperada);
				colaSensitividades.poner(sensitividadIterativa);
        CapaNeuronas capaActual = (CapaNeuronas) capas.obtenerSiguienteDer();
				CapaNeuronas capaAnterior;
        CapaNeuronas capaSiguiente;
        while(capas.hayMasDer()){
            capaSiguiente = capaActual;
            capaActual = (CapaNeuronas) capas.obtenerSiguienteDer();

            sensitividadIterativa = calcularSensitivadad(capaActual,capaSiguiente,sensitividadIterativa);
						colaSensitividades.poner(sensitividadIterativa);
        }

				capas.inicializarIteradorDer();

        while(capas.hayMasDer()){
            capaActual = (CapaNeuronas) capas.obtenerSiguienteDer();
						capaAnterior = (CapaNeuronas) capas.obtenerIteradorDer();
						sensitividadIterativa = (Matriz2DNumerica) colaSensitividades.quitar();

						if (capaAnterior == null) {
							capaActual.actualizarPesos(sensitividadIterativa,gradoAprendizaje, entradaInicial);
						} else {
							capaActual.actualizarPesos(sensitividadIterativa,gradoAprendizaje, capaAnterior.calcularSalida());
						}
            capaActual.actualizarBias(sensitividadIterativa,gradoAprendizaje);
        }
    }

    private Matriz2DNumerica calcularSensitivadad(CapaNeuronas capaActual, CapaNeuronas capaSiguiente, Matriz2DNumerica sensitividadSiguiente) {
        Matriz2DNumerica sensitividad = capaActual.funcionActivacion.funcionDerivada(capaActual.getSumatoria());
        Matriz2DNumerica transpuesta = capaSiguiente.getPesos().clonar();
        transpuesta.aplicarTranspuesta();
        sensitividad = sensitividad.multiplicarMatriz(transpuesta);
        sensitividad = sensitividad.multiplicarMatriz(sensitividadSiguiente);
        return sensitividad;
    }

    private Matriz2DNumerica calcularSensitivadadInicial(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada) {
        CapaNeuronas ultimaCapa = (CapaNeuronas) capas.getUltimo();
        Matriz2DNumerica sensitividad = ultimaCapa.funcionActivacion.funcionDerivada(ultimaCapa.getSumatoria());
        sensitividad.porEscalar(-2);
        sensitividad = sensitividad.multiplicarMatriz(calcularError(salidaObtenida,salidaEsperada));
        return sensitividad;
    }

    private Matriz2DNumerica calcularError(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada){
				Matriz2DNumerica auxiliarSalidaEsperada = salidaEsperada.clonar();
        salidaObtenida.porEscalar(-1);
        auxiliarSalidaEsperada.sumarMatriz(salidaObtenida);
        return auxiliarSalidaEsperada;
    }
}
