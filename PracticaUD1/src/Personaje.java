import java.io.Serializable;
import java.util.HashMap;

public class Personaje implements Serializable {
    /**
     * 3 Strings de 50 chars - 100 cada uno, 300 bytes
     * 10 ints - 4 cada uno - 40 bytes
     * TOTAL 340 Bytes
     */
    private int id;
    private String nombre;//Max 50
    private String clase;//Max 50
    private String raza;//Max 50
    private int hitDie;
    private int nivel;
    private int Str;
    private int Dex;
    private int Con;
    private int Int;
    private int Wis;
    private int Cha;
    private int vida;


    public Personaje(String nombre, int nivel, String clase, int hitDie, String raza, int str, int dex, int con, int anInt, int wis, int cha, int vida) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.clase = clase;
        this.hitDie = hitDie;
        this.raza = raza;
        Str = str;
        Dex = dex;
        Con = con;
        Int = anInt;
        Wis = wis;
        Cha = cha;
        this.vida = vida;
    }
    public Personaje(){}

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

    public int getStr() {
        return Str;
    }

    public void setStr(int str) {
        Str = str;
    }

    public int getDex() {
        return Dex;
    }

    public void setDex(int dex) {
        Dex = dex;
    }

    public int getCon() {
        return Con;
    }

    public void setCon(int con) {
        Con = con;
    }

    public int getInt() {
        return Int;
    }

    public void setInt(int anInt) {
        Int = anInt;
    }

    public int getWis() {
        return Wis;
    }

    public void setWis(int wis) {
        Wis = wis;
    }

    public int getCha() {
        return Cha;
    }

    public void setCha(int cha) {
        Cha = cha;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void subirNivel(){
        //X + ((Y/2+1) + CPorNivel(Se aplica Retroactivamente))
        this.nivel++;
        this.vida = vida + (((hitDie/2) + 1) + Con);
    }
}
