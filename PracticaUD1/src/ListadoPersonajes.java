import java.io.Serializable;
import java.util.ArrayList;

public class ListadoPersonajes implements Serializable {
    private ArrayList<Personaje> personajes;

    public ListadoPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }
    public void add(Personaje personaje){
        personajes.add(personaje);
    }
}
