
package ec.espol.edu.algoritmosOrdenamiento;

/*
* 
* Algoritmo de QuickSort en Java
* Recuperado de: http://www.java2novice.com/java-sorting-algorithms/quick-sort/
* Fecha: 17/11/2018
*/

public class QuickSort {
    int arreglo[];
    int longitud;
    
    public QuickSort(int l[]){
      arreglo = l;
    } 
 
    public void sort(int[] arr){
        boolean caso = false;
        if (arr == null || arr.length == 0){
            return;
        }
        
        arreglo = arr;
        longitud = arr.length;
        quickSort(0, longitud - 1, caso);
    }

    //QuickSort retorna un true si es el mejor/promedio caso y false en peor caso
    private boolean quickSort(int lInd, int hInd, boolean caso){
        int i = lInd;
        int j = hInd;
        
        //Calcula el pivote
        int pivote = arreglo[lInd+(hInd - lInd)/2];
        
        //Se divide en dos arreglos
        while (i <= j) {
            while (arreglo[i] < pivote) {
                i++;
            }
            while (arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                intercambiarNums(i, j);
                i++;
                j--;
            }
        }
        
        /*Defino que caso es
            true = mejor o caso promedio
            false = peor caso
        */
        
        //Llama al método recursivo quicksort
        if (lInd < j)
            quickSort(lInd, j, caso);
        
        if (i < hInd)
            quickSort(i, hInd, caso);
        return caso;
    }
 
    private void intercambiarNums(int i, int j) {
        int temporal = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temporal;
    }
    
    /* Método que calcula el tiempo de ejecución */
    public long quickTimer(){
    	long inicio, fin, time;
    	
        inicio = System.nanoTime();
        sort(arreglo);
        fin = System.nanoTime();

        time = (fin - inicio)/1000;
        VariablesGlobales.timeQ = time;
        
        System.out.println("Tiempo de Quick : "+time + " microsegundos");

        return time;
    }
    
    public void calculaPorPartes(){
        int cnt = arreglo.length;
        
    }

}
