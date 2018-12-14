import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainClass {
    public static int n, indexes[];
    public static double a[][], x[];
    public static boolean detNull=false;

    public static void gaus(){
        x = new double[n];
        for(int c = 0, r = 0;c<n && r<n;c++) {
            int rM = r;
            for (int i = r; i < n; i++)
                if (Math.abs(a[i][c]) > Math.abs(a[rM][c]))
                    rM = i;
            for (int i = c; i <= n; i++) {
                double temp = a[rM][i];
                a[rM][i] = a[r][i];
                a[r][i] = temp;
            }
            if(a[r][r] == 0)
                detNull = true;
            for (int i = 0; i < n; i++)
                if (i != r) {
                    double k = a[i][c] / a[r][c];
                    for (int j = 0; j <= n; j++)
                        a[i][j] -= a[r][j] * k;
                }
            r++;
        }
        for (int c=0, r=0; c<n; c++) {
            for (int i=0; i<n; i++)
                if (i != r) {
                    double k = a[i][c] / a[r][c];
                    a[i][c] -= a[r][c] * k;
                }
            r++;
        }
        for (int i=0; i<n; i++)
            x[i] = a[i][n] / a[i][i];
    }

    public static void main(String[] args){
        try {
            File fName = new File("src\\input.txt");
            Scanner sc = new Scanner(fName);


            n = sc.nextInt();
            a = new double[n][n + 1];
            for (int i = 0; i < n; i++)
                for (int j = 0; j <= n; j++)
                    a[i][j] = sc.nextDouble();
            gaus();
            if(detNull)
                System.out.println("Null determinant");
            else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j <= n; j++)
                        System.out.print(a[i][j] + "\t");
                    System.out.println();
                }
                System.out.println("-----------------");
                for (int i = 0; i < n; i++)
                    System.out.print(x[i] + " ");
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("wrong file name");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("wrong input");
        }

    }
}