package assignment_4;

import java.util.Scanner;

public class Assignment4_1 {
    int A[] = { 10, 13, 14, 5, 7, 20, 3, 6, 1, 15 };
    int B[] = new int[A.length];
    Scanner scanner = new Scanner(System.in);

    public void run() {
        for (int i = 0; i < B.length; i++) {
            System.out.printf("%d. ", i+1);
            B[i] = scanner.nextInt();
        }
    }

    public void loop() {
        for (int i = 0; i < B.length; i++) {
            if (A[i] != B[i]) {
                checkArray();
                return;

            }
        }

        System.out.println("A equal B");
    }

    public void checkArray() {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] == B[j]) {
                    System.out.println("A intersect B");
                    return;
                }
            }
        }

        System.out.println("A disjoin B");
    }


    // 
    public static void main(String[] args) {
        Assignment4_1 obj = new Assignment4_1();

        obj.run();
        obj.loop();

    }
}
