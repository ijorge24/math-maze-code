package Soluciones;

import Informacio.Maze;
import Informacio.ReadFile;

import java.util.Arrays;
import java.util.LinkedList;

public class LabExhaustivo {
    Maze laberinto;
    LinkedList<int[]> pila = new LinkedList<>();
    int[] auxFila = new int[2];
    boolean solucion = false;


    public LabExhaustivo(String file) {
        laberinto = new ReadFile().readFile(file);
    }

    public Maze getMaze() {
        return laberinto;
    }

    public boolean getSolucion() {
        return solucion;
    }

    public void setSolucion() {
        solucion = false;
    }

    public Maze solRecursiva(int fil, int col, int valor, Maze propio) {
        if (fil == laberinto.entrada[0] && col == laberinto.entrada[1]) {           // solucionar
            propio = laberinto.clone();
            valor = hazOperacion(laberinto.getInfo(fil, col), valor);

        }
        if (fil == laberinto.sortida[0] && col == laberinto.sortida[1]) {
            System.out.println("Llega a la posicion [" + fil + ", " + col + "] con puntuación " + valor + " y con el siguiente mapa:");
            System.out.println(propio.visitesToString());
            pila.add(laberinto.sortida);
            System.out.println("El recorrido ha sido el siguiente:");
            for (int[] elem : pila) {
                System.out.print(Arrays.toString(elem) + "\t");
            }
            System.out.println();
            System.out.println();
            pila.removeLast();
            solucion = true;
            return propio;
        }
        int auxvalor;
        if (fil != 0) {

            auxvalor = propio.visites[fil - 1][col] == 0 ? hazOperacion(laberinto.getInfo(fil - 1, col), valor) : -1;

            if (auxvalor > 0) {

                propio.visites[fil][col] = 1;
                auxFila[0] = fil;
                auxFila[1] = col;
                pila.add(auxFila.clone());
                solRecursiva(fil - 1, col, auxvalor, propio);
                pila.removeLast();
            }

        }
        if (col + 1 < laberinto.getCols()) {
            auxvalor = propio.visites[fil][col + 1] == 0 ? hazOperacion(laberinto.getInfo(fil, col + 1), valor) : -1;

            if (auxvalor > 0) {

                propio.visites[fil][col] = 1;
                auxFila[0] = fil;
                auxFila[1] = col;
                pila.add(auxFila.clone());
                solRecursiva(fil, col + 1, auxvalor, propio);
                pila.removeLast();
            }

        }


        if (fil + 1 < laberinto.getFiles()) {
            auxvalor = propio.visites[fil + 1][col] == 0 ? hazOperacion(laberinto.getInfo(fil + 1, col), valor) : -1;

            if (auxvalor > 0) {

                propio.visites[fil][col] = 1;
                auxFila[0] = fil;
                auxFila[1] = col;
                pila.add(auxFila.clone());
                solRecursiva(fil + 1, col, auxvalor, propio);
                pila.removeLast();
            }
        }
        if (col != 0) {
            auxvalor = propio.visites[fil][col - 1] == 0 ? hazOperacion(laberinto.getInfo(fil, col - 1), valor) : -1;
            if (auxvalor > 0) {

                propio.visites[fil][col] = 1;
                auxFila[0] = fil;
                auxFila[1] = col;
                pila.add(auxFila.clone());
                solRecursiva(fil, col - 1, auxvalor, propio);
                pila.removeLast();
            }
        }
        propio.visites[fil][col] = 0;

        return null;
    }


    public int hazOperacion(String op, int valor) {
        int num = Integer.parseInt(op.substring(1));
        switch (op.substring(0, 1)) {
            case "+" -> num += valor;
            case "-" -> num = valor - num;
            case "*" -> num *= valor;
            case "/" -> num = valor / num;
        }
        return num;
    }

}
