package Informacio;

public class Maze {
    private final int files;
    private final int cols;
    public String[][] laberint;
    public int[][] visites;
    public int[] entrada = new int[2];
    public int[] sortida = new int[2];

    public Maze(int files, int cols, int efil, int ecol, int sfil, int scol) {
        this.files = files;
        this.cols = cols;
        laberint = new String[files][cols];
        visites = new int[files][cols];
        entrada[0] = efil;
        entrada[1] = ecol;
        sortida[0] = sfil;
        sortida[1] = scol;
    }

    public void guardarInfo(int fil, int col, String dada) {
        laberint[fil][col] = dada;
        visites[fil][col] = dada.equals("NA") ? 3 : 0;
    }

    public String getInfo(int fil, int col) {
        return laberint[fil][col];
    }

    public int getFiles() {
        return files;
    }

    public int getCols() {
        return cols;
    }


    public void setVisites(int[][] visites) {
        this.visites = visites;
    }

    public void setLaberint(String[][] laberint) {
        this.laberint = laberint;
    }

    @Override
    public String toString() {
        StringBuilder tostring = new StringBuilder();
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                tostring.append(getInfo(i, j)).append("\t");
            }
            tostring.append("\n");
        }
        return tostring.toString();
    }

    public Maze clone() {
        Maze maze = new Maze(this.files, this.cols, this.entrada[0], this.entrada[1], this.sortida[0], this.sortida[1]);
        maze.setLaberint(this.laberint.clone());
        maze.setVisites(this.visites.clone());
        return maze;
    }

    public String visitesToString() {
        StringBuilder message = new StringBuilder("\n");
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                if (entrada[0] == i && entrada[1] == j) {
                    message.append("E");
                } else if (sortida[0] == i && sortida[1] == j) {
                    message.append("S");
                } else {
                    switch (visites[i][j]) {
                        case 1 -> message.append("+");
                        case 3 -> message.append("#");
                        default -> message.append("·");
                    }
                }
            }
            message.append("\n");
        }
        return message.toString();
    }


}
