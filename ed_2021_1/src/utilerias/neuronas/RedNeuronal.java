package utilerias.neuronas;

import edlineal.ListaDobleLigada;
import ednolineal.Matriz2DNumerica;

/**
 * Conjunto de capas que continen neuronas para generar predicciones y clasificaciones
 * @author Elias Beltran y Juventino Aguilar
 */
public class RedNeuronal {
    protected ListaDobleLigada capas;
    protected int numEntradas;

    public void agregarCapa(int numNeuronas, FuncionActivacion funcionActivacion){
        int numEntradasNuevaCapa = entradasNuevaCapa();
        CapaNeuronas capaNueva = new CapaNeuronas(numEntradasNuevaCapa,numNeuronas, funcionActivacion);
        capas.agregar(capaNueva);
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

    private void realizarBackward(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada, double gradoAprendizaje){
        capas.inicializarIteradorDer();
        Matriz2DNumerica sensitividadIterativa = calcularSensitivadadInicial(salidaObtenida,salidaEsperada);
        CapaNeuronas capaActual = (CapaNeuronas) capas.obtenerSiguienteDer();
        capaActual.actualizarPesos(sensitividadIterativa,gradoAprendizaje);
        capaActual.actualizarBias(sensitividadIterativa,gradoAprendizaje);
        CapaNeuronas capaAnterior;
        while(capas.hayMasDer()){
            capaAnterior = capaActual;
            capaActual = (CapaNeuronas) capas.obtenerSiguienteDer();

            sensitividadIterativa = calcularSensitivadad(capaActual,capaAnterior,sensitividadIterativa);

            capaActual.actualizarPesos(sensitividadIterativa,gradoAprendizaje);
            capaActual.actualizarBias(sensitividadIterativa,gradoAprendizaje);
        }
    }

    private Matriz2DNumerica calcularSensitivadad(CapaNeuronas capaActual, CapaNeuronas capaSiguiente, Matriz2DNumerica sensitividadSiguiente) {
        Matriz2DNumerica sensitividad = capaActual.funcionActivacion.funcionDerivada(capaActual.sumatoria);
        sensitividad.multiplicarMatriz(capaSiguiente.pesos);
        sensitividad.multiplicarMatriz(sensitividadSiguiente);
        return sensitividad;
    }

    private Matriz2DNumerica calcularSensitivadadInicial(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada) {
        CapaNeuronas ultimaCapa = (CapaNeuronas) capas.getUltimo();
        Matriz2DNumerica sensitividad = ultimaCapa.funcionActivacion.funcionDerivada(ultimaCapa.sumatoria);
        sensitividad.porEscalar(-2);
        sensitividad.multiplicarMatriz(calcularError(salidaObtenida,salidaEsperada));
        return sensitividad;
    }

    private Matriz2DNumerica calcularError(Matriz2DNumerica salidaObtenida, Matriz2DNumerica salidaEsperada){
        salidaObtenida.porEscalar(-1);
        salidaEsperada.sumarMatriz(salidaObtenida);
        return salidaEsperada;
    }
}