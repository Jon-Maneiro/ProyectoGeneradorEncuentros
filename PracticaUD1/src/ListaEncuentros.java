import java.io.Serializable;
import java.util.ArrayList;

public class ListaEncuentros implements Serializable {
    private ArrayList<Encuentro> encuentros = new ArrayList<>();

    public ListaEncuentros(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    public ListaEncuentros(){}

    public ArrayList<Encuentro> getEncuentros() {
        return encuentros;
    }

    public void setEncuentros(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    public void add(Encuentro encuentro){
        encuentros.add(encuentro);
    }

}
