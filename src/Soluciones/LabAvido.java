package Soluciones;

import Informacio.Maze;
import Informacio.ReadFile;

import java.util.Arrays;
import java.util.LinkedList;

public class LabAvido {
    Maze laberinto;
    LinkedList<Integer> valor = new LinkedList<>();
    LinkedList<int[]> pila = new LinkedList<>();

    public LabAvido(String file) {
        int opcio;
        int[] newFila = new int[2];
        laberinto = new ReadFile().readFile(file);
        pila.add(laberinto.entrada);
        valor.add(hazOperacion(laberinto.getInfo(laberinto.entrada[0], laberinto.entrada[1]), 0));
        laberinto.visites[pila.getLast()[0]][pila.getLast()[1]] = 1;
        while (pila.size() != 0 && !(pila.getLast()[0] == laberinto.sortida[0] && pila.getLast()[1] == laberinto.sortida[1])) {
            //System.out.println("Calculant amb valor: " + valor.getLast() + " i coords: " + Arrays.toString(pila.getLast()));
            opcio = busquedaCamino(pila.getLast()[0], pila.getLast()[1], valor.getLast());
            switch (opcio) {
                case -1 -> {
                    laberinto.visites[pila.getLast()[0]][pila.getLast()[1]] = 2;
                    valor.removeLast();
                    pila.removeLast();
                }
                case 0 -> {
                    newFila[0] = pila.getLast()[0] - 1;
                    newFila[1] = pila.getLast()[1];
                }
                case 1 -> {
                    newFila[0] = pila.getLast()[0];
                    newFila[1] = pila.getLast()[1] + 1;
                }
                case 2 -> {
                    newFila[0] = pila.getLast()[0] + 1;
                    newFila[1] = pila.getLast()[1];
                }
                case 3 -> {
                    newFila[0] = pila.getLast()[0];
                    newFila[1] = pila.getLast()[1] - 1;
                }
            }
            if (opcio != -1) {
                pila.add(newFila.clone());
                valor.add(hazOperacion(laberinto.getInfo(newFila[0], newFila[1]), valor.getLast()));
                laberinto.visites[newFila[0]][newFila[1]] = 1;
            }
        }
        if (pila.size() == 0) {
            System.out.println("No se ha encontrado solucion");
        } else {

            System.out.println("Llega a la posicion " + Arrays.toString(laberinto.sortida) + " con puntuacion " + valor.getLast() + " y con el siguiente mapa:");
            System.out.println(laberinto.visitesToString());
            System.out.println("El recorrido ha sido el siguiente:");
            for (int[] elem : pila) {
                System.out.print(Arrays.toString(elem) + "\t");
            }
        }
    }

    public int busquedaCamino(int fil, int col, int valor) {
        int auxN, auxS, auxE, auxW;
        auxN = (fil - 1 >= 0) && laberinto.visites[fil - 1][col] == 0 ? hazOperacion(laberinto.getInfo(fil - 1, col), valor) : -1;
        auxE = (col + 1 < laberinto.getCols()) && laberinto.visites[fil][col + 1] == 0 ? hazOperacion(laberinto.getInfo(fil, col + 1), valor) : -1;
        auxS = (fil + 1 < laberinto.getFiles()) && laberinto.visites[fil + 1][col] == 0 ? hazOperacion(laberinto.getInfo(fil + 1, col), valor) : -1;
        auxW = (col - 1 >= 0) && laberinto.visites[fil][col - 1] == 0 ? hazOperacion(laberinto.getInfo(fil, col - 1), valor) : -1;
        return (auxN <= 0 && auxE <= 0 && auxS <= 0 && auxW <= 0) ? -1 : ((auxN >= auxS && auxN >= auxE && auxN >= auxW) ? 0 : ((auxE >= auxS && auxE >= auxW) ? 1 : (auxS >= auxW ? 2 : 3)));
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
