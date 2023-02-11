import java.io.IOError;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Zycie {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {
        int a;
        int b;

        System.out.print("Wpisz rozmiar tablicy: (jezeli chcesz domyslny - wpisz 0)\na: ");
        a = sc.nextInt();
        if (a <= 0) {
            if (a < 0) {
                System.out.println("Ujemna? Serio? Wiec bedzie domyslny");
            }
            a = 30;
            b = 20;
        } else {
            System.out.print("b: ");
            b = sc.nextInt();
        }
        Gra n = new Gra(a, b);

        System.out.println("Wpisz ilosc zywych komorek: (0 - domyslna, -1 - figura poczatkowa)");
        int k = sc.nextInt();
        do {
            if (k < -1) {
                System.out.println("Niepoprawna liczba. Wpisz ponownie");
                k = sc.nextInt();
                continue;
            } else if (k == -1) {
                n.init();
            } else if (k == 0) {
                k = (a * b) / 5;
                n.randomInit(k);
            } else {
                n.randomInit(k);
            }
        } while (k < -1);

        System.out.println("Wpisz szybkosc wypisywania ukladow w milisekundach: (0 - domyslna)");
        int czas = sc.nextInt();
        if (czas <= 0) {
            czas = 1000;
        }

        while (true) {
            n.Draw();
            Scanner scan = new Scanner(System.in);
            System.out.println();
            try {
                Thread.sleep(czas);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            if (Arrays.deepEquals(n.square1, n.square) == true) {
                System.out.println("Autostop");
                break;
            } else {
                clear();
            }
        }
    }

    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
