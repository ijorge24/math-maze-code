import Soluciones.LabAvido;
import Soluciones.LabExhaustivo;

public class Main {
    public static void main(String[] args) {
        long startNanoTime;
        double elapsedTime;
        System.out.println("JUEGO DE PRUEBAS PRACTICA 3 MP!!!");
        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1 -> System.out.println("\n\nTest1: laberinto con varios recorridos disponibles (ejemplo del enunciado)");
                case 2 -> System.out.println("\n\nTest2: laberinto sin solucion");
                case 3 -> System.out.println("\n\nTest3: laberinto con una unica solucion");
                case 4 -> System.out.println("\n\nTest4: laberinto cuadrado 3x3 con todas las posiciones +1");
            }
            System.out.println("Resolución forma avida->");
            startNanoTime = System.nanoTime();
            new LabAvido("labDemo" + i + ".txt");
            elapsedTime = (System.nanoTime() - startNanoTime) * 1.0e-6;
            System.out.println("\nEl tiempo para la forma avida ha sido de: " + elapsedTime + "ns");
            System.out.println("\n");
            System.out.println("Resolucion de forma exhaustiva (todas las soluciones)->");
            startNanoTime = System.nanoTime();
            LabExhaustivo prueba = new LabExhaustivo("labDemo" + i + ".txt");
            prueba.solRecursiva(prueba.getMaze().entrada[0], prueba.getMaze().entrada[1], 0, prueba.getMaze().clone());
            if (!prueba.getSolucion())
                System.out.println("No tiene solucion");
            else
                prueba.setSolucion();
            elapsedTime = (System.nanoTime() - startNanoTime) * 1.0e-6;
            System.out.println("\nEl tiempo para la forma exhaustiva ha sido de: " + elapsedTime + "ns");
        }

    }
}
