import java.util.Scanner;

public class Order {
    private Restaurant restaurant;
    private static final int SERVICE_FEE = 20000;

    public Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void startOrdering() {
        Scanner sc = new Scanner(System.in);
        Menu[] orderMenus = new Menu[4];
        int[] orderQty = new int[4];
        int orderCount = 0;

        restaurant.displayMenus();
        System.out.println("\nMasukkan pesanan (Nama Menu = jumlah)");
        System.out.println("Ketik 'selesai' jika sudah.");

        while (true) {
            System.out.print("Pesanan: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("selesai")) break;

            String[] parts = input.split("=");
            if (parts.length != 2) {
                System.out.println("Format salah! Gunakan format: Nama Menu = jumlah");
                continue;
            }

            String name = parts[0].trim();
            int qty = Integer.parseInt(parts[1].trim());
            Menu found = restaurant.findMenuByName(name);

            if (found == null) {
                System.out.println("Menu tidak ditemukan!");
                continue;
            }

            if (orderCount >= orderMenus.length) {
                orderMenus = expandMenuArray(orderMenus);
                orderQty = expandIntArray(orderQty);
            }

            orderMenus[orderCount] = found;
            orderQty[orderCount] = qty;
            orderCount++;
        }

        printReceipt(orderMenus, orderQty, orderCount);
    }

    private void printReceipt(Menu[] orderMenus, int[] orderQty, int orderCount) {
        System.out.println("\n========== STRUK PEMESANAN ==========");
        int subtotal = 0;
        for (int i = 0; i < orderCount; i++) {
            int total = orderMenus[i].price * orderQty[i];
            subtotal += total;
            System.out.printf("%-25s x%-2d Rp %d%n", orderMenus[i].name, orderQty[i], total);
        }

        int bogo = 0;
        if (subtotal > 50000) {
            for (int i = 0; i < orderCount; i++) {
                if (orderMenus[i].category.equals("minuman") && orderQty[i] > 1)
                    bogo += (orderQty[i] / 2) * orderMenus[i].price;
            }
        }

        int afterBogo = subtotal - bogo;
        double discount = afterBogo > 100000 ? afterBogo * 0.1 : 0;
        double tax = 0.1 * (afterBogo - discount);
        double total = afterBogo - discount + tax + SERVICE_FEE;

        System.out.println("--------------------------------------");
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Potongan BOGO: Rp " + bogo);
        System.out.println("Diskon 10%: Rp " + discount);
        System.out.println("Pajak 10%: Rp " + tax);
        System.out.println("Biaya Pelayanan: Rp " + SERVICE_FEE);
        System.out.println("--------------------------------------");
        System.out.println("TOTAL BAYAR: Rp " + total);
    }

    private Menu[] expandMenuArray(Menu[] arr) {
        Menu[] newArr = new Menu[arr.length + 4];
        for (int i = 0; i < arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    private int[] expandIntArray(int[] arr) {
        int[] newArr = new int[arr.length + 4];
        for (int i = 0; i < arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }
}
