import java.util.Scanner;

public class Sizzer {
    private String[] menus = {"Southwest Grilled Chicken",
            "Hibachi Chicken",
            "Chinese Noodle Salad",
            "Teriyaki Chopped Steak",
            "Coca Cola Light"};

    private int[] prices = {479, 385, 369, 189, 219, 29};
    private double sum = 0.0;
    private double discount = 0.0;
    private double vat;
    private double total;
    private boolean isMember;

    public void showMenu() {
        String line  = "=";
        line = line.repeat(48 / 2 );


        System.out.println(line + " Menu " + line);

        for(int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %-40s %-5d Baht\n", i+1, menus[i], prices[i]);
        }

        System.out.println("0  0. End");
    }

    public void enterMenu() {
        System.out.println("");

        while (true) {
            System.out.print("Enter menu (1 - 6) and amount : ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            String[] split = str.split(" ");
            int i = Integer.parseInt(split[0]);
            int n = Integer.parseInt(split[1]);

            if (i > prices.length) {
                System.out.println("---- END ----");
                break;
            } else if (i == 0 && n == 0) {
                break;
            }

            this.sum += prices[i - 1] * n;
        }

    }

    public void getMember() {
        System.out.print("Member ID (0 is not member): ");
        Scanner scanner = new Scanner(System.in);
        String member = scanner.next();

        if (!member.equals("0")) this.isMember = true;

    }

    public void payment() {

        this.vat = this.sum * 0.07;
        this.total = this.sum + this.vat;


        if (this.isMember) {
            this.discount = this.total * 0.03;
            this.total -= this.discount;

        }

        System.out.println("");
        System.out.printf("%-43s %.2f\n", "Before VAT", this.sum);
        System.out.printf("%-43s %.2f\n", "VAT 7%", this.vat);
        System.out.printf("%-43s %.2f\n", "Discount", this.discount);
        System.out.printf("%-43s %.2f\n", "Total", this.total);
    }

}
