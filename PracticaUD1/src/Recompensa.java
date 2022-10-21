import java.io.Serializable;

public class Recompensa implements Serializable {

    private int id;
    private String nombre;//Max 50 chars
    private String tipo;//Max 20 chars (Objeto magico, gema, pergamino.. etc)
    private int rareza;//Pues cuanto dinero cuesta en monedas de oro. Minimo 1

    public Recompensa(int id, String nombre, String tipo, int coste) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = coste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getRareza() {
        return rareza;
    }

    public void setRareza(int coste) {
        this.rareza = coste;
    }
}
