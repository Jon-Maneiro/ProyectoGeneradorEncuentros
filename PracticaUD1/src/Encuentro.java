import java.io.Serializable;

public class Encuentro implements Serializable {
    /**
     * Esta clase solo debería usarse para pasar los entre el programa y XML
     * todas las demás operaciones deberán hacerse instanciando un objeto
     * de la clase necesaria.
     */

    private int numeroPJ;
    private int nivelPJ;

    private ListaEnemigos enemigos;
    private ListaRecompensas recompensas;

    public Encuentro(int numeroPJ, int nivelPJ, ListaEnemigos enemigos, ListaRecompensas recompensas) {
        this.numeroPJ = numeroPJ;
        this.nivelPJ = nivelPJ;
        this.enemigos = enemigos;
        this.recompensas = recompensas;
    }

    public int getNumeroPJ() {
        return numeroPJ;
    }

    public void setNumeroPJ(int numeroPJ) {
        this.numeroPJ = numeroPJ;
    }

    public int getNivelPJ() {
        return nivelPJ;
    }

    public void setNivelPJ(int nivelPJ) {
        this.nivelPJ = nivelPJ;
    }

    public ListaEnemigos getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ListaEnemigos enemigos) {
        this.enemigos = enemigos;
    }

    public ListaRecompensas getRecompensas() {
        return recompensas;
    }

    public void setRecompensas(ListaRecompensas recompensas) {
        this.recompensas = recompensas;
    }
}
