package utilerias.matematicas;

/**
 * Representa un conjunto de numeros entre dos limites
 * @author Elias Beltran y Juventino Aguilar
 */
public class Rango {
    private String nombre;
    private double inicioRango;
    private double finRango;

    public Rango(String nombre, double inicioRango, double finRango) {
        this.nombre = nombre;
        this.inicioRango = inicioRango;
        this.finRango = finRango;
    }

    public boolean estaDentro(double dato){
        return inicioRango <= dato && dato < finRango;
    }

    public String getNombre() {
        return nombre;
    }
}
