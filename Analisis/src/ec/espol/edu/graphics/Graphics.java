/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espol.edu.graphics;

import ec.espol.edu.algoritmosOrdenamiento.LeerArchivo;
import ec.espol.edu.algoritmosOrdenamiento.QuickSort;
import ec.espol.edu.algoritmosOrdenamiento.Utilidad;
import ec.espol.edu.algoritmosOrdenamiento.VariablesGlobales;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;
/**
 *
 * @author Acer
 */
public class Graphics extends JFrame{
    
    DefaultXYDataset dataset;
    XYItemRenderer renderer;
    JFreeChart chart, grafica;
    XYSeriesCollection datos = new XYSeriesCollection();
    ChartPanel panel;
    JFrame frame;
    
    public Graphics(String dato1,String dato2, String dato3, int size){
        
        double[] dominioV = dominioValores(size);
        double[] rangoMerge = rangoValoresMerge(size);
        double[] rangoQuick = rangoValoresQuick(size);
        double[] rangoInsertion = rangoValoresInsertion(size);
        
        //Datos que se mostraran en la grafica y todos los casos posibles que se pueden presentar
        dataset = new DefaultXYDataset();
        if(dato1.startsWith("S") && dato2.startsWith("S") && dato3.startsWith("S")){
            dataset.addSeries("MergeSort", new double[][] {dominioV, rangoMerge});
            dataset.addSeries("QuickSort", new double[][] {dominioV, rangoQuick});
            dataset.addSeries("InsertionSort", new double[][] {dominioV, rangoInsertion});
        }
        else if(dato1.startsWith("S") && dato2.startsWith("S") && dato3.startsWith("N")){
            dataset.addSeries("MergeSort", new double[][] {dominioV, rangoMerge});
            dataset.addSeries("QuickSort", new double[][] {dominioV, rangoQuick});
        }
        else if(dato1.startsWith("S") && dato2.startsWith("N")  && dato3.startsWith("S")){
            dataset.addSeries("MergeSort", new double[][] {dominioV, rangoMerge});
            dataset.addSeries("InsertionSort", new double[][] {dominioV, rangoInsertion});
        }
        else if(dato1.startsWith("N") && dato2.startsWith("S")  && dato3.startsWith("S")){
            dataset.addSeries("QuickSort", new double[][] {dominioV, rangoQuick});
            dataset.addSeries("InsertionSort", new double[][] {dominioV, rangoInsertion});
        }
        else if(dato1.startsWith("S") && dato2.startsWith("N") && dato3.startsWith("N")){
            dataset.addSeries("MergeSort", new double[][] {dominioV, rangoMerge});
        }
        else if(dato1.startsWith("N") && dato2.startsWith("S") && dato3.startsWith("N")){
            dataset.addSeries("QuickSort", new double[][] {dominioV, rangoQuick});
        }
        else if(dato1.startsWith("N") && dato2.startsWith("N") && dato3.startsWith("S")){
            dataset.addSeries("InsertionSort", new double[][] {dominioV, rangoInsertion});
        }
        //Declaracion de los colores del grafico para cada variable
        renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));
        
        //Declaracion del titulo, rango del eje Y, nombre del eje X y nombre del eje Y
        System.out.println("Valor mayor: "+rangoN(rangoMerge, rangoQuick, rangoInsertion));
        chart = ChartFactory.createXYLineChart("Algoritmos de ordenamiento", "Número Elementos", "Tiempo de ejecución", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, rangoN(rangoMerge, rangoQuick, rangoInsertion));
        chart.getXYPlot().setRenderer(renderer);
        
        //Declaracion del panel en donde se mostrara la grafica
        panel = new ChartPanel(chart,false);
        frame = new JFrame("Grafico");
        getGrafico();
    }
    
    //Metodo para mostrar el grafico en el panel
    public void getGrafico(){
        frame.setSize(800,600);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    /*Método para obtener el rango, el cual será el mayor valor de todos los tiempos*/
    private double rangoN(double[] algoritmo1, double[] algoritmo2, double[] algoritmo3){
        double mayor = 0;
        for(int i = 0; i<algoritmo1.length; i++){
            if(algoritmo1[i]>mayor)
                mayor = algoritmo1[i];
        }
        
        for(int i = 0; i<algoritmo2.length; i++){
            if(algoritmo2[i]>mayor)
                mayor = algoritmo2[i];
        }
        
        for(int i = 0; i<algoritmo3.length; i++){
            if(algoritmo3[i]>mayor)
                mayor = algoritmo3[i];
        }
        return mayor;
    }
    
    /*Método para obtener el dominio de la gráfica*/
    private double[] dominioValores(int size){
        LeerArchivo leer = new LeerArchivo();
        LinkedList listaResultados = leer.lecturaArchivo("resultado.txt");
        double[] dominio = new double[listaResultados.size()];
        int count = 0;
        Iterator i = listaResultados.iterator();
        while(i.hasNext() && (count*10)<size){
            String[] datos = (String[]) i.next();
            dominio[count] = Double.parseDouble(datos[0]);
            count++;
        }
        return dominio;
    }
    
    /*Método para obtener el rango del tiempo del algoritmo MergeSort*/
    private double[] rangoValoresMerge(int size){
        LeerArchivo leer = new LeerArchivo();
        LinkedList listaResultados = leer.lecturaArchivo("resultado.txt");
        double[] rangoMerge = new double[listaResultados.size()];
        int count = 0;
        double control = 0;
        Iterator i = listaResultados.iterator();
        while(i.hasNext() && (count*10)<size){
            String[] datos = (String[]) i.next();
            if(Double.parseDouble(datos[1])<control*10 || Double.parseDouble(datos[1])>control*10)
                rangoMerge[count] = control*5;
            else
                rangoMerge[count] = Double.parseDouble(datos[1]);
            control = Double.parseDouble(datos[1]);
            count++;
        }
        return rangoMerge;
    }

    /*Método para obtener el rango del tiempo del algoritmo QuickSort*/
    private double[] rangoValoresQuick(int size){
        LeerArchivo leer = new LeerArchivo();
        LinkedList listaResultados = leer.lecturaArchivo("resultado.txt");
        double[] rangoQuick = new double[listaResultados.size()];
        int count = 0;
        double control = 0;
        Iterator i = listaResultados.iterator();
        while(i.hasNext() && (count*10)<size){
            String[] datos = (String[]) i.next();
            if(Double.parseDouble(datos[2])<control*10 || Double.parseDouble(datos[2])>control*10)
                rangoQuick[count] = control*5;
            else
                rangoQuick[count] = Double.parseDouble(datos[2]);
            control = Double.parseDouble(datos[2]);
            count++;
        }
        return rangoQuick;
    } 
    
    /*Método para obtener el rango del tiempo del algoritmo InsertionSort*/
    private double[] rangoValoresInsertion(int size){
        LeerArchivo leer = new LeerArchivo();
        LinkedList listaResultados = leer.lecturaArchivo("resultado.txt");
        double[] rangoInsertion = new double[listaResultados.size()];
        int count = 0;
        double control = 0;
        Iterator i = listaResultados.iterator();
        while(i.hasNext()  && (count*10)<size){
            String[] datos = (String[]) i.next();
            if(Double.parseDouble(datos[3])<control*10 || Double.parseDouble(datos[3])>control*10)
                rangoInsertion[count] = control*5;
            else
                rangoInsertion[count] = Double.parseDouble(datos[3]);
            control = Double.parseDouble(datos[3]);
            count++;
        }
        return rangoInsertion;
    } 
}
