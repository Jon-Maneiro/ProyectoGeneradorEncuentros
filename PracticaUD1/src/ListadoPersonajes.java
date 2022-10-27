import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ListadoPersonajes implements Serializable {
    private ArrayList<Personaje> personajes;
    /**
     * 3 Strings de 50 chars - 100 cada uno, 300 bytes
     * 10 ints - 4 cada uno - 40 bytes
     * TOTAL 340 Bytes
     */
    public ListadoPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void llenarListaPersonajes(){
        /**
         * 3 Strings de 50 chars - 100 cada uno, 300 bytes
         * 10 ints - 4 cada uno - 40 bytes
         * TOTAL 340 Bytes
         */
        try{
            File fPj = new File("personajes.dat");
            if(!fPj.exists()){
                System.out.println("Parece que aun no existe ningun personaje...");
                System.out.println("¿Porqué no vas a crear uno?");
            }else{
                RandomAccessFile fichero = new RandomAccessFile(fPj,"rw");
                int id;
                char[] nombre;//Max 50
                char[] clase;//Max 50
                char[] raza;//Max 50
                int nivel;
                int hitDie;
                int vida;
                int Str;
                int Dex;
                int Con;
                int Int;
                int Wis;
                int Cha;


                long longitud = fichero.length();
                fichero.seek(0);

                while(fichero.getFilePointer() < longitud){

                    nombre = new char[50];
                    clase = new char[50];
                    raza = new char[50];

                    id = fichero.readInt();
                    for (int x = 0; x < 50; x++) {
                        nombre[x] = fichero.readChar();
                    }
                    for (int x = 0; x < 50; x++) {
                        clase[x] = fichero.readChar();
                    }
                    for (int x = 0; x < 50; x++) {
                        raza[x] = fichero.readChar();
                    }
                    nivel = fichero.readInt();
                    hitDie = fichero.readInt();
                    vida = fichero.readInt();
                    Str = fichero.readInt();
                    Dex = fichero.readInt();
                    Con = fichero.readInt();
                    Int = fichero.readInt();
                    Wis = fichero.readInt();
                    Cha = fichero.readInt();

                    Personaje person = new Personaje(new String(nombre),nivel,new String(clase),hitDie,new String(raza),Str,Dex,Con,Int,Wis,Cha,vida);
                    person.setId(id);
                    add(person);
                }
                fichero.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertarNuevoPersonaje(Personaje pj) throws IOException {
        /**
         * 3 Strings de 50 chars - 100 cada uno, 300 bytes
         * 10 ints - 4 cada uno - 40 bytes
         * TOTAL 340 Bytes
         */
        File file = new File("personajes.dat");
        RandomAccessFile fichero = new RandomAccessFile(file,"rw");
        int id = 0;
        //Primero hay que sacar el ultimo id para tenerlo
        if(fichero.length() == 0){
            id = 1;
        }else{
            long longitud = fichero.length();
            fichero.seek(longitud - 340);
            id = fichero.readInt() + 1;

            fichero.seek(longitud);

            fichero.writeInt(id);
            fichero.writeChars(pj.getNombre());
            fichero.writeChars(pj.getClase());
            fichero.writeChars(pj.getRaza());
            fichero.writeInt(pj.getNivel());
            fichero.writeInt(pj.getHitDie());
            fichero.writeInt(pj.getVida());
            fichero.writeInt(pj.getStr());
            fichero.writeInt(pj.getDex());
            fichero.writeInt(pj.getCon());
            fichero.writeInt(pj.getInt());
            fichero.writeInt(pj.getWis());
            fichero.writeInt(pj.getCha());

            fichero.close();

            pj.setId(id);
            add(pj);

        }

    }

    public void listarPersonajes(){
        for(Personaje pj: personajes){
            System.out.println("------------------------");
            System.out.println("Id:" + pj.getId());
            System.out.println("Nombre: " + pj.getNombre() + "Raza: " + pj.getRaza() + " Clase: " + pj.getClase());
            System.out.println("HitDie:" + pj.getHitDie() + "Vida: " + pj.getVida());
            System.out.println("FUERZA:"+pj.getStr());
            System.out.println("DESTREZA:" + pj.getDex());
            System.out.println("CONSTITUCION:" + pj.getCon());
            System.out.println("INTELIGENCIA:" + pj.getInt());
            System.out.println("SABIDURIA:" + pj.getWis());
            System.out.println("CARISMA:" + pj.getCha());
        }
    }

    public void subirNivel(int id){
        try {
            //Lo hago con el fichero por practicar realmente
            File file = new File("personajes.dat");
            if(!file.exists()){
                System.out.println("Parece que aun no existe ningun personaje...");
                System.out.println("¿Porqué no vas a crear uno?");
            }else{
                /**
                 * 3 Strings de 50 chars - 100 cada uno, 300 bytes
                 * 10 ints - 4 cada uno - 40 bytes
                 * TOTAL 340 Bytes
                 */
                RandomAccessFile fichero = new RandomAccessFile(file,"rw");
                Personaje pj = new Personaje();
                char[] nombre;
                char[] raza;
                char[] clase;

                long longitud = fichero.length();
                long posicionlectura = 340 *(id -1);

                if(posicionlectura < longitud){
                    System.out.println("Ese personaje no existe");
                }else{
                    nombre = new char[50];
                    clase = new char[50];
                    raza = new char[50];
                    fichero.seek(posicionlectura);
                    pj.setId(id);
                    for (int x = 0; x < 50; x++) {
                        nombre[x] = fichero.readChar();
                    }
                    for (int x = 0; x < 50; x++) {
                        clase[x] = fichero.readChar();
                    }
                    for (int x = 0; x < 50; x++) {
                        raza[x] = fichero.readChar();
                    }
                    pj.setNombre(new String(nombre));
                    pj.setClase(new String(clase));
                    pj.setRaza(new String(raza));
                    pj.setNivel(fichero.readInt());
                    pj.setHitDie(fichero.readInt());
                    pj.setVida(fichero.readInt());
                    pj.setStr(fichero.readInt());
                    pj.setDex(fichero.readInt());
                    pj.setCon(fichero.readInt());
                    pj.setInt(fichero.readInt());
                    pj.setWis(fichero.readInt());
                    pj.setCha(fichero.readInt());

                    pj.subirNivel();

                    fichero.seek(posicionlectura);

                    fichero.writeInt(id);
                    fichero.writeChars(pj.getNombre());
                    fichero.writeChars(pj.getClase());
                    fichero.writeChars(pj.getRaza());
                    fichero.writeInt(pj.getNivel());
                    fichero.writeInt(pj.getHitDie());
                    fichero.writeInt(pj.getVida());
                    fichero.writeInt(pj.getStr());
                    fichero.writeInt(pj.getDex());
                    fichero.writeInt(pj.getCon());
                    fichero.writeInt(pj.getInt());
                    fichero.writeInt(pj.getWis());
                    fichero.writeInt(pj.getCha());

                    fichero.close();

                    for(Personaje pc: personajes){
                        if(pc.getId() == id){
                            personajes.remove(pc);
                            personajes.add(pj);
                        }
                    }

                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
