package assignment_4;

import java.util.Scanner;

public class Assignment4_2 {
    int arr[] = new int[10];
    Scanner scanner = new Scanner(System.in);

    public void run() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d. : ", i + 1);
            int num = scanner.nextInt();
            if (num == 0) {
                System.out.println("--- End ---");
                return;
            }

            arr[i] = num;
        }

        while (true) {
            int idx = findVal(getVal());

            System.out.printf("index: %d\n", idx);
            if (idx != -1) {
                break;
            }

            System.out.println("try again!\n");
        }

    }

    public int getVal() {
        System.out.print("val: ");
        int val = scanner.nextInt();
        return val;
    }

    public int findVal(int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Assignment4_2 obj = new Assignment4_2();
        obj.run();
    }
}
