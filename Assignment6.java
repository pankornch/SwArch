package assignment_6;
import java.util.Scanner;

public class Assignment6 {
    private int array[];
    Scanner scanner = new Scanner(System.in);

    public void getInput() {
        System.out.print("size of array: ");
        int size = scanner.nextInt();
        array = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.printf("%d. ", i+1);
            array[i] = scanner.nextInt();
        }
    }

    public int[] sort() {

        // Selection Sort

        int currentMinIndex;

        for (int i = 0; i < array.length - 1; i++) {
            currentMinIndex = i;

            for (int j = i; j < array.length; j++) {
                if (array[j] < array[currentMinIndex]) {
                    currentMinIndex = j;
                }
            }

            // Swap

            int temp_1 = array[i];
            int temp_2 = array[currentMinIndex];

            array[i] = temp_2;
            array[currentMinIndex] = temp_1;
        }

        return array;
    }


    public static void main(String[] args) {
        Assignment6 obj = new Assignment6();
        
        obj.getInput();

        int result[] = obj.sort();

        System.out.println("\n---- Result ----\n");

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
