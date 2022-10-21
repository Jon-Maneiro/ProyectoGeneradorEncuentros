import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaEnemigos implements Serializable {

    static ArrayList<Enemigo> enemigos = new ArrayList<>();

    public ListaEnemigos(boolean llenar) {
        if (llenar) {
            llenarListaEnemigos();
        }
    }

    public ListaEnemigos() {
    }


    /**
     * Leer los datos de la lista de enemigos.dat.
     * Si el archivo de Enemigos.dat no existe, se creará en base al fichero excel proporcionado en la carpeta
     */
    public void llenarListaEnemigos() {
        try {
            //Primero comprobamos que el enemigos.dat existe, y si no existe
            //se crea y se rellena con los datos del excel
            File fEnemigos = new File("enemigos.dat");
            if (!fEnemigos.exists()) {
                fEnemigos = leerExcel(fEnemigos);
            }
            //Ahora se lee el fichero y se recuperan los datos
            /**
             * Id, int de 4 bytes
             * Nombre, cadena de 50 caracteres, 100 Bytes
             * Tipo, cadena de 11 caracteres, 22 bytes
             * CR, long de 8 bytes
             * XP, int de 8 bytes
             * Total, 142 bytes
             */
            RandomAccessFile fichero = new RandomAccessFile(fEnemigos, "rw");
            int id;
            char[] nombre;
            char[] tipo;
            long cr;
            int xp;
            long longitud = fichero.length();
            fichero.seek(0);
            //Leemos el fichero y llenamos el arrayList de enemigos para
            //poder trabajar con el
            while (fichero.getFilePointer() < longitud) {

                nombre = new char[50];
                tipo = new char[11];

                id = fichero.readInt();
                for (int x = 0; x < 50; x++) {
                    nombre[x] = fichero.readChar();
                }
                for (int x = 0; x < 11; x++) {
                    tipo[x] = fichero.readChar();
                }
                cr = fichero.readLong();
                xp = fichero.readInt();
                enemigos.add(new Enemigo(id, new String(nombre), new String(tipo), cr, xp));
            }


            fichero.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Posibilidad e insertar un enemigo nuevo en enemigos.dat
     */
    public void insertarNuevoEnemigo(String nombre, String tipo, long cr, int xp) throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres, 100 Bytes
         * Tipo, cadena de 11 caracteres, 22 bytes
         * CR, long de 8 bytes
         * XP, int de 8 bytes
         * Total, 142 bytes
         */
        File file = new File("enemigos.dat");
        RandomAccessFile fichero = new RandomAccessFile(file, "rw");


        //Primero hay que sacar el ID correspondiente al monstruo
        long longitud = fichero.length();
        fichero.seek(longitud - 142);//Aqui hay una posible zona de bug, checkear
        int id = fichero.readInt() + 1;//Tenemos el id?¿


        fichero.seek(longitud);

        fichero.writeInt(id);
        fichero.writeChars(nombre);
        fichero.writeChars(tipo);
        fichero.writeLong(cr);
        fichero.writeInt(xp);

        fichero.close();

        enemigos.add(new Enemigo(id, nombre, tipo, cr,xp));
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
            XSSFSheet sheet = workbook.getSheetAt(0);//Leemos de la hoja de Monstruos

            //Iteramos sobre las filas una a una
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //Por cada fila iteramos sobre las columnas
                Iterator<Cell> cellIterator = row.cellIterator();
                if (contid > 0) {
                    while (cellIterator.hasNext()) {//Si esto se repite mas de una vez, algo va mal
                        // En Orden son: Nombre, tipo y CR(Challenge Rating)
                        Cell cell = cellIterator.next();
                        //Comprobamos el tipo de la celda para leer
                        String nombre = cell.getStringCellValue();
                        cell = cellIterator.next();
                        String tipo = cell.getStringCellValue();
                        cell = cellIterator.next();
                        long cr = (long) cell.getNumericCellValue();
                        cell = cellIterator.next();
                        int xp = (int) cell.getNumericCellValue();
                        //DATOS A METER, EN ORDEN
                        /**
                         * Id, int de 4 bytes
                         * Nombre, cadena de 50 caracteres, 100 Bytes
                         * Tipo, cadena de 11 caracteres, 22 bytes
                         * CR, long de 8 bytes
                         * XP, int de 8 bytes
                         * Total, 142 bytes
                         */

                        fichero.writeInt(contid);
                        fichero.writeChars(obtenerStringCompleto(nombre, 50));
                        fichero.writeChars(obtenerStringCompleto(tipo, 11));
                        fichero.writeLong(cr);
                        fichero.writeInt(xp);


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
        } catch (Exception e) {
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

    public static void add(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    public static ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public static void setEnemigos(ArrayList<Enemigo> enemigos) {
        ListaEnemigos.enemigos = enemigos;
    }
}
