package assignment_4;

import java.util.Scanner;

public class Assignment4_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("col: ");
        int col = scanner.nextInt();

        System.out.print("row: ");
        int row = scanner.nextInt();

        int array[][] = new int[row][col];
        int transpose[][] = new int[col][row];

        for (int i = 0; i < row; i++) {
            System.out.printf("\nrow %d\n", i + 1);
            for (int j = 0; j < col; j++) {
                System.out.printf("col %d : ", j + 1);
                array[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nA\n");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                transpose[j][i] = array[i][j];

                System.out.printf("%-3d", array[i][j]);
            }
            System.out.println((""));
        }

        System.out.println("\nA Transpose\n");
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) {
                System.out.printf("%-3d", transpose[i][j]);
            }

            System.out.println("");
        }

        scanner.close();

    }
}
