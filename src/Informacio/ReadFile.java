package Informacio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadFile {
    public Maze readFile(String path) {

        File fichero = new File(path);
        String line;
        int i, columns, rows, ecol, efil, scol, sfil, j;
        Maze laberinto = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            if ((line = br.readLine()) != null) {
                StringTokenizer strtok = new StringTokenizer(line, ",");
                rows = Integer.parseInt(strtok.nextToken().strip());
                columns = Integer.parseInt(strtok.nextToken().strip());
                efil = Integer.parseInt(strtok.nextToken().strip());
                ecol = Integer.parseInt(strtok.nextToken().strip());
                sfil = Integer.parseInt(strtok.nextToken().strip());
                scol = Integer.parseInt(strtok.nextToken().strip());
                laberinto = new Maze(rows, columns, efil, ecol, sfil, scol);
                i = 0;
                while (((line = br.readLine()) != null) && (i < laberinto.getFiles())) {
                    strtok = new StringTokenizer(line, ",");
                    j = 0;
                    while (strtok.hasMoreTokens() && (j < laberinto.getCols())) {
                        laberinto.guardarInfo(i, j, strtok.nextToken().strip());
                        j++;
                    }

                    i++;
                }
            }
        } catch
        (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return laberinto;
    }
}
