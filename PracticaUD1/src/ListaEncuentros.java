import java.io.Serializable;
import java.util.ArrayList;

public class ListaEncuentros implements Serializable {
    private ArrayList<Encuentro> encuentros;

    public ListaEncuentros(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    public ListaEncuentros(){}

    public ArrayList<Encuentro> getEncuentro() {
        return encuentros;
    }

    public void setEncuentro(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    public void add(Encuentro encuentro){
        encuentros.add(encuentro);
    }

}
