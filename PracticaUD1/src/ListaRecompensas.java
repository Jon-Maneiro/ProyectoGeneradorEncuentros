import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class ListaRecompensas implements Serializable{

    static ArrayList<Recompensa> recompensas = new ArrayList<>();
    static int oroEntregable;
    public ListaRecompensas(boolean llenar) {
        if (llenar) {
            llenarListaRecompensas();
        }
    }

    public ListaRecompensas(){};

    public ArrayList<Recompensa> filtrarRareza(int rareza){
        ArrayList<Recompensa> rarezas = new ArrayList<>();
        for(int x = 0; x < recompensas.size(); x++){
            Recompensa posibilidad = recompensas.get(x);
            if(posibilidad.getRareza() == rareza){
                rarezas.add(posibilidad);
            }
        }
        return rarezas;
    }


    /**
     * Llena la lista de recompensas de un .dat que puede llenarse con más datos
     */
    public void llenarListaRecompensas() {
        try {
            //Primero hay que comprobar que el recompensas.dat exista y si no existe,
            //se crea y se rellena con losdatos del excel
            File fRecompensas = new File("recompensas.dat");
            if (!fRecompensas.exists()) {
                fRecompensas = leerExcel(fRecompensas);
            }
            //Ahora se lee el fichero y se recuperan los datos
            /**
             * Id, int de 4 bytes
             * Nombre, cadena de 50 caracteres , 100 bytes
             * Tipo, cadena de 15 caracteres , 30 bytes
             * rareza, int de 8 bytes
             * Total, 142 bytes
             */
            RandomAccessFile fichero = new RandomAccessFile(fRecompensas, "rw");
            int id;
            char[] nombre;
            char[] tipo;
            int rareza;
            long longitud = fichero.length();
            fichero.seek(0);
            //Leemos el fichero y llenamos el arraylist de recompensas para poder trabajar
            //con el
            while(fichero.getFilePointer() < longitud){

                nombre = new char[50];
                tipo = new char[15];

                id = fichero.readInt();
                for (int x = 0; x<50;x++){
                    nombre[x] = fichero.readChar();
                }
                for(int x = 0; x<15;x++){
                    tipo[x] = fichero.readChar();
                }
                rareza = fichero.readInt();

                recompensas.add(new Recompensa(id,new String(nombre), new String(tipo),rareza));
            }

            fichero.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Posibilidad de insertar una nueva recompensa en recompensas.dat
     */
    public void insertarNuevaRecompensa(String nombre, String tipo, int rareza) throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres , 100 bytes
         * Tipo, cadena de 15 caracteres , 30 bytes
         * rareza, int de 8 bytes
         * Total, 142 bytes
         */
        File file = new File("recompensas.dat");
        RandomAccessFile fichero = new RandomAccessFile(file, "rw");


        //Primero hay que sacar el ID correspondiente al monstruo
        long longitud = fichero.length();
        fichero.seek(longitud - 142);//Aqui hay una posible zona de bug, checkear
        int id = fichero.readInt() + 1;//Tenemos el id?¿


        fichero.seek(longitud);

        fichero.writeInt(id);
        fichero.writeChars(nombre);
        fichero.writeChars(tipo);
        fichero.writeInt(rareza);

        fichero.close();

        recompensas.add(new Recompensa(id,nombre,tipo,rareza));
    }

    private File leerExcel(File archivo) {
        File data = archivo;
        try {
            //Creamos una variable para llevar los ID al momento
            //Inicializada a 0 porque la primera linea no la queremos
            int contid = 0;
            //VARIABLES DEL .DAT

            RandomAccessFile fichero = new RandomAccessFile(archivo, "rw");

            //VARIABLES DEL EXCEL
            FileInputStream file = new FileInputStream(new File("Recursos.xlsx"));
            //Creamos la instancia del workbook que tiene la referencia al excel
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Obtenemos la Hoja deseada del excel
            XSSFSheet sheet = workbook.getSheetAt(1);//Leemos de la hoja de Recompensas

            //Iteramos sobre las filas una a una
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //Por cada fila iteramos sobre las columnas
                Iterator<Cell> cellIterator = row.cellIterator();
                if (contid > 0) {
                    while (cellIterator.hasNext()) {//Si esto se repite mas de una vez, algo va mal
                        // En Orden son: Nombre, tipo y Rareza
                        Cell cell = cellIterator.next();
                        //Comprobamos el tipo de la celda para leer
                        String nombre = cell.getStringCellValue();
                        cell = cellIterator.next();
                        String tipo = cell.getStringCellValue();
                        cell = cellIterator.next();
                        int rareza = (int) cell.getNumericCellValue();

                        /**
                         * Id, int de 4 bytes
                         * Nombre, cadena de 50 caracteres , 100 bytes
                         * Tipo, cadena de 15 caracteres , 30 bytes
                         * rareza, int de 8 bytes
                         * Total, 142 bytes
                         */
                        fichero.writeInt(contid);
                        fichero.writeChars(obtenerStringCompleto(nombre,50));
                        fichero.writeChars(obtenerStringCompleto(tipo,15));
                        fichero.writeInt(rareza);

                    }
                }
                System.out.println("");
                contid++;
            }
            file.close();
            fichero.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("A");
        }
        return archivo;
    }

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

    public void add(Recompensa recompensa){
        recompensas.add(recompensa);
    }

    public static int getOroEntregable() {
        return oroEntregable;
    }

    public static void setOroEntregable(int oroEntregable) {
        ListaRecompensas.oroEntregable = oroEntregable;
    }

    public static ArrayList<Recompensa> getRecompensas() {
        return recompensas;
    }

    public static void setRecompensas(ArrayList<Recompensa> recompensas) {
        ListaRecompensas.recompensas = recompensas;
    }
}
