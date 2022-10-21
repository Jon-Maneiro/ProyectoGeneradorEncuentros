import java.io.Serializable;

public class Enemigo implements Serializable {

    private int id;//Llevar control de las listas
    private String nombre;//Max 50caracteres
    private String tipo;//Max 20 caracteres
    private long cr;//va de 0 - 0.125 hasta ... 30?¿
    private int xp;//La experiencia que da el bicho

    public Enemigo(int id, String nombre, String tipo, long cr, int xp) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cr = cr;
        this.xp = xp;
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

    public long getCr() {
        return cr;
    }

    public void setCr(long cr) {
        this.cr = cr;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}