package registros.neuronas;

import edlineal.ListaLigada;
import ednolineal.Matriz2DNumerica;
import graficacion.GraficaLineaXY;
import utilerias.neuronas.RedNeuronal;
import utilerias.neuronas.funciones.FuncionActivacion;
import utilerias.neuronas.funciones.FuncionLineal;
import utilerias.neuronas.funciones.FuncionLogSigmoid;

public class AdministracionRedesNeuronales {
    private ListaLigada ejeXFuncion;
    private ListaLigada ejeYFuncion;
    private ListaLigada ejeXRed;
    private ListaLigada ejeYRed;
    private RedNeuronal redNeuronal;

    public void crearRedNeuronal(){
        //Generando Red Neuronal
        FuncionActivacion funcionLogSigmoid = new FuncionLogSigmoid();
        FuncionActivacion funcionLineal = new FuncionLineal();
        redNeuronal = new RedNeuronal(1);

        redNeuronal.agregarCapa(2, funcionLogSigmoid);
        redNeuronal.agregarCapa(1, funcionLineal);
    }

    public void graficarResultados() {
        ListaLigada paresXY = new ListaLigada();
        paresXY.agregar(ejeXFuncion);
        paresXY.agregar(ejeYFuncion);
        paresXY.agregar(ejeXRed);
        paresXY.agregar(ejeYRed);

        ListaLigada keys = new ListaLigada();
        keys.agregar("Función Original");
        keys.agregar("Función Red Neuronal");

        GraficaLineaXY graficaLinea=new GraficaLineaXY();
        graficaLinea.init(paresXY, keys,"Ejemplo Redes Neuronales","Dato insertado","Resultado");
    }

    public void realizarPrueba() {
        Matriz2DNumerica entradaInicial = new Matriz2DNumerica(1, 1);

        for (int i = 0; i < 5000; i++) {
            double input = Math.random()*4.0-2.0;
            double valorEsperado = 1 + Math.sin((Math.PI / 4) * input);

            entradaInicial.cambiarInfo(0, 0, input);
            ejeXFuncion.agregar(input);
            ejeYFuncion.agregar(valorEsperado);
            ejeXRed.agregar(input);
            Matriz2DNumerica resultadoRed = redNeuronal.realizarForward(entradaInicial);
            ejeYRed.agregar(resultadoRed.obtenerInfo(0,0));
        }
    }

    public void entrenarRed() {
        double gradoAprendizaje = .1;

        ejeXFuncion = new ListaLigada();
        ejeYFuncion = new ListaLigada();
        ejeXRed = new ListaLigada();
        ejeYRed = new ListaLigada();

        Matriz2DNumerica entradaInicial = new Matriz2DNumerica(1,1);
        Matriz2DNumerica salidaEsperada = new Matriz2DNumerica(1,1);

        for (int i = 0; i < 5000; i++) {
            double input = Math.random()*4.0-2.0;
            double valorEsperado = 1 + Math.sin((Math.PI / 4) * input);
            entradaInicial.cambiarInfo(0, 0, input);
            salidaEsperada.cambiarInfo(0, 0, valorEsperado);
            redNeuronal.entrenar(entradaInicial, salidaEsperada, gradoAprendizaje);
        }
    }
}
