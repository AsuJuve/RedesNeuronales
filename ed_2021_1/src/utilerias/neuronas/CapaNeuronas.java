package utilerias.neuronas;

import ednolineal.Matriz2DNumerica;
import java.lang.Math;

/**
 * Conjunto de neuronas que mediante una entrada generan una salida
 * @author Elias Beltran y Juventino Aguilar
 */
public class CapaNeuronas {
    protected Matriz2DNumerica pesos;
    protected Matriz2DNumerica bias;
    protected Matriz2DNumerica sumatoria;
    protected FuncionActivacion funcionActivacion;

    public CapaNeuronas(int numEntrada, int numNeuronas, FuncionActivacion funcionActivacion) {
        pesos = new Matriz2DNumerica(numNeuronas,numEntrada);
        bias = new Matriz2DNumerica(numNeuronas,1);
        this.funcionActivacion = funcionActivacion;
        rellenarValoresAleatorios();
    }

    public int obtenerNumeroNeuronas(){
        return pesos.renglones();
    }

    public void calcularSumatoria(Matriz2DNumerica entrada){
        sumatoria = pesos.multiplicarMatriz(entrada);
        sumatoria.sumarMatriz(bias);
    }

    private Matriz2DNumerica calcularSalida() {
        return funcionActivacion.funcionActivadora(sumatoria);
    }

    public Matriz2DNumerica calcularSalida(Matriz2DNumerica entrada){
        calcularSumatoria(entrada);
        return calcularSalida();
    }

    public void rellenarValoresAleatorios(){
        for (int renglon = 0; renglon < pesos.renglones(); renglon++){
            for (int columna = 0; columna < pesos.columnas(); columna++){
                pesos.cambiarInfo(renglon,columna,(Math.random()*2)-1);
            }
        }

        for (int renglon = 0; renglon < bias.renglones(); renglon++){
            for (int columna = 0; columna < bias.columnas(); columna++){
                bias.cambiarInfo(renglon,columna,(Math.random()*2)-1);
            }
        }
    }

    public void actualizarPesos(Matriz2DNumerica sensitividad, double gradoAprendizaje){
        Matriz2DNumerica auxiliarSensitividad = sensitividad.clonar();
        Matriz2DNumerica salidaTranspuesta = calcularSalida();
        salidaTranspuesta.aplicarTranspuesta();
        auxiliarSensitividad.multiplicarMatriz(salidaTranspuesta);
        auxiliarSensitividad.porEscalar(gradoAprendizaje);
        auxiliarSensitividad.porEscalar(-1);
        pesos.sumarMatriz(auxiliarSensitividad);
    }

    public void actualizarBias(Matriz2DNumerica sensitividad, double gradoAprendizaje){
        Matriz2DNumerica auxiliarSensitividad = sensitividad.clonar();
        auxiliarSensitividad.porEscalar(gradoAprendizaje*(-1));
        bias.sumarMatriz(auxiliarSensitividad);
    }
}
