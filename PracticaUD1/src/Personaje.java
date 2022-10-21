import java.io.Serializable;
import java.util.HashMap;

public class Personaje implements Serializable {
    private String nombre;
    private int nivel;
    private String clase;
    private int hitDie;
    private String raza;
    private HashMap<String,Integer> estadisticas;
    /**
     * Las estadisticas tienen que ir en orden
     * Fuerza
     * Destreza
     * Constitucion
     * Inteligencia
     * Sabiduria
     * Carisma
     */
    private int vida;

    public Personaje(String nombre, int nivel, String clase, int hitDie, String raza, HashMap<String, Integer> estadisticas, int vida) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.clase = clase;
        this.hitDie = hitDie;
        this.raza = raza;
        this.estadisticas = estadisticas;
        this.vida = vida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public HashMap<String, Integer> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(HashMap<String, Integer> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void subirNivel(){
        //X + ((Y/2+1) + CPorNivel(Se aplica Retroactivamente))
        this.nivel++;
        this.vida = vida + (((hitDie/2) + 1) + estadisticas.get("Constitucion"));
    }
}
