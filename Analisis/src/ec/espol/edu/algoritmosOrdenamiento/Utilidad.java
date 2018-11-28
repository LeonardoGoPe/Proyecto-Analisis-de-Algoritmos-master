package ec.espol.edu.algoritmosOrdenamiento;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Utilidad {
	
	int []lista;
	Random randomm = new Random();
	
	/* Metodo que recibe un array ya creado
	 */
        public Utilidad(int[] list){
            lista=list;
        }
        /* Metodo que permite generar un arreglo de n mumero de 
	 * enteros (entre 1 y n).
	 */
	public Utilidad(int num){
            lista = new int [num];
            for (int i = 0; i < lista.length; i++) {
                lista[i] = (int)(Math.random() * num + 1);
            }
            //System.out.println(lista.toString());
	}
	
	/* Metodo que permite imprimir el arreglo */
	public void imprimirArray(int []ar){
		System.out.println(Arrays.toString(ar));
	}
	
	/*Metodo que permite tener una copia del arreglo original */
	public int[] obtenerCopiaArray(){
            int[] copiaArray = lista.clone();
            return copiaArray;
	}
        
        /*Método que permite obtener una copia del arreglo original y permite obtenerlo con un tamaño definido*/
        public int[] obtenerCopiaArray(int cantidad){
            int[] copiaArray = new int[cantidad];
            for(int i=0; i<cantidad; i++){
                copiaArray[i] = lista[i];
            }
            return copiaArray;
        }
	
    public void crearTxT(String archivo){
    try{
        File f =new File(archivo);
        FileWriter escritor = new FileWriter (f,true);
        try (BufferedWriter A = new BufferedWriter(escritor); PrintWriter salida = new PrintWriter(A)) {

            for (int i = 0; i < obtenerCopiaArray().length; i++)
                salida.write(obtenerCopiaArray()[i]+"\r\n");
            }
        } catch (Exception e) {}
    }
    
    /*Método para escribir el set de resultados para los diferentes tiempos de ejecución*/
    public void escribir(String path, int nelementos, long a1, long a2, long a3)
        throws UnsupportedEncodingException, FileNotFoundException, IOException{
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("resultado.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(nelementos+"|"+a1+"|"+a2+"|"+a3);
            bw.newLine();
            System.out.println("información agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /*Método qu permite obtener el set de resultados para realizar la gráfica respectiva*/
}
