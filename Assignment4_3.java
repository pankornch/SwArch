package assignment_4;

public class Assignment4_3 {
    int a[] = { 1, 2, 3, 4, 5 };
    int b[] = { 6, 7, 8, 9, 10 };
    int merge[] = new int[a.length + b.length];

    public void run() {
        for (int i = 0; i < a.length + b.length; i++) {
            if (i < a.length) {
                merge[i] = a[i];
            } else {
                merge[i] = b[i - b.length];
            }
        }

        for (int i = 0; i < merge.length; i++) {
            System.out.printf("%d%s", merge[i], i == merge.length-1 ? "" : ", ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Assignment4_3 obj = new Assignment4_3();

        obj.run();
    }
}
