import java.util.Arrays;
import java.util.Random;

public class Gra {
    Random random = new Random();

    int a = 30;
    int b = 20;
    int r = 120;

    boolean square[][];
    boolean square1[][];

    Gra() {
        square = new boolean[a][b];
    }

    Gra(int a, int b) {
        this.a = a;
        this.b = b;
        square = new boolean[a][b];
    }

    public void init() {
        square1 = new boolean[a][b]; //figura domyslna
//        square[4][2] = true;
//        square[4][3] = true;
//        square[4][4] = true;
//        square[3][4] = true;
//        square[2][3] = true;

        //podpis autora - minimalistyczna litera V
        square[0][0] = true;
        square[1][0] = true;
        square[2][1] = true;
        square[1][2] = true;
        square[0][2] = true;

//        square[9][10] = true;
//        square[9][11] = true;
//        square[9][12] = true;
//        square[9][13] = true;
//        square[10][14] = true;
//        square[11][15] = true;
//        square[12][14] = true;
//        square[13][13] = true;
//        square[13][14] = true;
//        square[13][15] = true;
//        square[13][16] = true;
    }

    public void randomInit(int k) {
        square1 = new boolean[a][b];
        int count = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                square[i][j] = random.nextBoolean();
                if (square[i][j] == true) {
                    count++;
                }
                if (count == k) {
                    break;
                }
            }
            if (count == k) {
                break;
            }
        }
    }

    private void czyZywa(int x, int y) {
        if (square[x][y] == false) { //jesli nie zyje
            boolean[] sas = new boolean[8];
            Arrays.fill(sas, false); //sprawdzam czy jakies z 8-u sasiadow sa zywe
            sas[0] = (((x - 1) >= 0 && (y - 1) >= 0) && square1[x - 1][y - 1] == true) ? true : false;
            sas[1]=(x - 1) >= 0 && square1[x - 1][y] == true ? true : false;
            sas[2]=(x - 1) >= 0 && (y + 1) < b && square1[x - 1][y + 1] == true ? true : false;
            sas[3]=(y + 1) < b && square1[x][y + 1] == true ? true : false;
            sas[4]=(y + 1) < b && (x + 1) < b && square1[x + 1][y + 1] == true ? true : false;
            sas[5]=(x + 1) < b && square1[x + 1][y] == true ? true : false;
            sas[6]=(y - 1) >= 0 && (x + 1) < b &&  square1[x + 1][y - 1] == true ? true : false;
            sas[7]=(y - 1) >= 0 && square1[x][y - 1] == true ? true : false;

            int zywe = 0; //sprawdzam zywych sasiadow
            for (int i = 0; i < 8; i++) {
                if (sas[i] == true) {
                    zywe++;
                }
                if (zywe == 3) {
                    square[x][y] = true; //ozywiam
                }
            }
        }
        if (square[x][y] == true) { //jesli zyje
            boolean[] sas = new boolean[8];
            Arrays.fill(sas, false); //sprawdzam czy jakies z 8-u sasiadow sa zywe
            sas[0] = (((x - 1) >= 0 && (y - 1) >= 0) && square1[x - 1][y - 1] == true) ? true : false;
            sas[1]=(x - 1) >= 0 && square1[x - 1][y] == true ? true : false;
            sas[2]=(x - 1) >= 0 && (y + 1) < b && square1[x - 1][y + 1] == true ? true : false;
            sas[3]=(y + 1) < b && square1[x][y + 1] == true ? true : false;
            sas[4]=(y + 1) < b && (x + 1) < b && square1[x + 1][y + 1] == true ? true : false;
            sas[5]=(x + 1) < b && square1[x + 1][y] == true ? true : false;
            sas[6]=(y - 1) >= 0 && (x + 1) < b &&  square1[x + 1][y - 1] == true ? true : false;
            sas[7]=(y - 1) >= 0 && square1[x][y - 1] == true ? true : false;

            int zywe = 0; //sprawdzam zywych sasiadow
            for (int i = 0; i < 8; i++) {
                if (sas[i] == true) {
                    zywe++;
                }
                if (zywe == 3 || zywe == 2) {
                    square[x][y] = true; //zyje dalej
                } else {
                    square[x][y] = false; //umiera
                }
            }
        }
    }

    public void Draw() {
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                square1[i][j] = square[i][j]; //pamiec
            }
        }
        for (int i = 0; i < a; i++) { //wyswietlanie
            for (int j = 0; j < b; j++) {
                if (square[i][j] == true) {
                    System.out.print("\t#\t");
                } else {
                    System.out.print("\t.\t");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                this.czyZywa(i, j); //oblicza kolejne
            }
        }
    }
}
