import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.crypto.spec.PSource;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    //variables globales
    static ListaRecompensas recompensas = new ListaRecompensas(true);
    static ListaEnemigos enemigos = new ListaEnemigos(true);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("--HOLA--");
        System.out.println("Bienvenido al generador de encuentros de Jon Maneiro García para D&D(Dungeons and Dragons)");
        System.out.println("Este generador generará encuentros aleatorios para tus partidas, no será muy util en casos reales,");
        System.out.println("pero ha sido divertido de programar.");
        System.out.println("A continuacion habrá una serie de pasos a seguir, dependiendo de lo que quieras hacer.");
        System.out.println("Como detalle, si introduces 0, el numero, en cualquiera de los menus, se terminará el programa.");
        System.out.println("(Los de introduccion de datos no cuentan)");
        System.out.println("..Cargando..");
        Thread.sleep(1500);//Si, he hecho esto para que parezca mas interesante
        boolean salir = false;
        int menuPrincipal = -1;
        int menuEncuentros = -1;
        int menuPersonajes = -1;

        while(!salir) {
            menuPrincipal = menuPrincipal();
            switch(menuPrincipal){
                case 1://Encuentros
                    menuEncuentros = menuEncuentros();
                    switch(menuEncuentros){
                        case 1://Generar encuentro

                            break;

                        case 2://Visualizar Encuentros

                            break;

                        case 3://Añadir Enemigo

                            break;

                        case 4://Añadir Recompensa

                            break;

                        case 0://Salir
                            salir = true;
                            break;
                    }
                    break;

                case 2://Personajes
                    menuPersonajes = menuPersonajes();
                    switch (menuPersonajes){
                        case 1://Crear Personaje

                            break;

                        case 2://Listar Personajes

                            break;

                        case 3://Subir de nivel Personaje WIP

                            break;

                        case 0:
                            salir = true;
                            break;
                    }
                    break;

                case 3://Informacion
                    /**
                     * Mirar como abrir un word programaticamente
                     */
                    break;

                case 0://Salir
                    salir = true;
                    break;
            }
        }
        System.out.println("--¡Suerte en tus aventuras!--");
        System.out.println("..Cerrando programa..");
    }

    public static int menuPrincipal(){
        boolean correcto = false;
        int selec = -1;
        while(!correcto) {
            System.out.println("--Bienvenido al Menu Principal--");
            System.out.println("¿Que deseas hacer?");
            System.out.println("1 - Encuentros");
            System.out.println("2 - Personajes");
            System.out.println("3 - WIP - Informacion");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)){
                selec = Integer.parseInt(respuesta);
                if(selec >= 0 && selec <= 3){
                    correcto = true;
                }else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    public static int menuEncuentros(){
        boolean correcto = false;
        int selec = -1;
        while(!correcto){
            System.out.println("--Bienvenido al Menu de Encuentros--");
            System.out.println("¿Que deseas hacer?");
            System.out.println("1 - Generar Encuentro");
            System.out.println("2 - Listar Encuentros generados");
            System.out.println("3 - Añadir un nuevo enemigo");
            System.out.println("4 - Añadir una nueva recompensa");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)){
                selec = Integer.parseInt(respuesta);
                if(selec >= 0 && selec <= 4){
                    correcto = true;
                }else {
                    System.out.println("Numeros del 1 al 4(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    public static int menuPersonajes(){
        boolean correcto = false;
        int selec = -1;
        while(!correcto){
            System.out.println("--Bienvenido al menu de Personajes--");
            System.out.println("1 - Crear Personaje");
            System.out.println("2 - Listado de Personajes");
            System.out.println("3 - Subir de nivel Personaje");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)){
                selec = Integer.parseInt(respuesta);
                if(selec >= 0 && selec <= 3){
                    correcto = true;
                }else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    public static boolean isInt(String check){
        try{
            Integer.parseInt(check);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static void escribirEncuentroAXML(Encuentro encuentro) {
        ListaEncuentros encuentros = new ListaEncuentros();
        encuentros.add(encuentro);
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Encuentros", ListaEncuentros.class);
            xstream.alias("encuentro", Encuentro.class);
            xstream.alias("Enemigos", ListaEnemigos.class);
            xstream.alias("enemigo", Enemigo.class);
            xstream.alias("Recompensas", ListaRecompensas.class);
            xstream.alias("recompensa", Recompensa.class);

            xstream.addImplicitCollection(ListaEncuentros.class, "lista");

            xstream.toXML(encuentros, new FileOutputStream("Encuentros.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ListaEncuentros leerEncuentrosDeXML() throws FileNotFoundException {
        ListaEncuentros encuentros;

        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Encuentros", ListaEncuentros.class);
        xstream.alias("encuentro", Encuentro.class);
        xstream.alias("Enemigos", ListaEnemigos.class);
        xstream.alias("enemigo", Enemigo.class);
        xstream.alias("Recompensas", ListaRecompensas.class);
        xstream.alias("recompensa", Recompensa.class);
        xstream.addImplicitCollection(ListaEncuentros.class, "lista");

        FileInputStream fichero = new FileInputStream("Encuentros.xml");
        encuentros = (ListaEncuentros) xstream.fromXML(fichero);

        //Leer esto con un Iterator
        return encuentros;
    }

    public void insertarRecompensa() throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres , 100 bytes
         * Tipo, cadena de 15 caracteres , 30 bytes
         * rareza, int de 8 bytes
         * Total, 142 bytes
         *
         */
        Scanner sc = new Scanner(System.in);

        String nombre = "";
        String tipo = "";
        int rareza = 0;

        System.out.println("--Se va a proceder con la inserción de una nueva recompensa a la lista interna--");

        System.out.println("Introduce el nombre del objeto(MAX 50 chars, si sobrepasa, se trunca)");
        nombre = obtenerStringCompleto(sc.nextLine(), 50);

        System.out.println("Introduce el tipo del objeto(MAX 15 char)");
        tipo = obtenerStringCompleto(sc.nextLine(), 15);

        System.out.println("Por ultimo, introduce la rareza--->");
        System.out.println("1-Comun");
        System.out.println("2-Poco Comun");
        System.out.println("3-Raro");
        System.out.println("4-Muy Raro");
        System.out.println("5-Legendario");
        System.out.println("6-Artefacto");
        boolean correcto = false;//Usado para los bucles de insercion
        while (!correcto) {
            try {
                rareza = Integer.parseInt(sc.nextLine());
                if (rareza <= 1 && 6 >= rareza) {
                    correcto = true;
                } else {
                    System.out.println("Parece que no has introducido un numero valido");
                    System.out.println("Por favor introduce de nuevo el dato");
                    correcto = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        recompensas.insertarNuevaRecompensa(nombre, tipo, rareza);


    }

    public void insertarEnemigo() throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres, 100 Bytes
         * Tipo, cadena de 11 caracteres, 22 bytes
         * CR, long de 8 bytes
         * XP, int de 8 bytes
         * Total, 142 bytes
         */

        Scanner sc = new Scanner(System.in);

        String nombre = "";
        String tipo = "";
        long cr = 0;
        int xp = 0;

        System.out.println("--Se va a proceder con la inserción de un monstruo nuevo a la lista interna--");

        System.out.println("Introduce el nombre del monstruo(MAX 50 chars, si se sobrepasa, se trunca)");
        nombre = obtenerStringCompleto(sc.nextLine(),50);

        System.out.println("Introduce el tipo del monstruo(MAX 11 chars)");
        tipo = obtenerStringCompleto(sc.nextLine(),11);


        System.out.println("Introduce la Clase de Desafio(Challenge Rating) dle monstruo, de 0 a 20");
        System.out.println("Existen, del 0 al 1 los siguientes:");
        System.out.println("1/8 : 0.125");
        System.out.println("1/4 : 0.25");
        System.out.println("1/2 : 0.50");
        boolean correcto = false;
        while (!correcto) {
            try {
                cr = Long.parseLong(sc.nextLine());
                if (cr <= 0 && 20 >= cr) {
                    correcto = true;
                } else {
                    System.out.println("Parece que no has introducido un numero valido");
                    System.out.println("Por favor introduce de nuevo el dato");
                    correcto = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        System.out.println("Introduce la experiencia que otorga el monstruo al derrotarlo");
        System.out.println("Para hacerse una idea de cuanta xp puede otorgar, consultar el Excel");
        correcto = false;
        while (!correcto) {
            try {
                xp = Integer.parseInt(sc.nextLine());
                correcto = true;
            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        enemigos.insertarNuevoEnemigo(nombre,tipo,cr,xp);

    }

    /**
     * CREAR CLASE GENERICA PARA FUNCIONES GENERICAS
     *
     * @param texto
     * @param longitud
     * @return
     */
    private String obtenerStringCompleto(String texto, int longitud) {
        String modif = texto;
        if (modif.length() < longitud) {
            while (modif.length() < longitud) {
                modif = modif + " ";
            }
        } else if (modif.length() > longitud) {
            modif = modif.substring(0, (longitud - 1));
        }

        return modif;
    }
    /**
     * TODO generarXML de Personajes
     */


}