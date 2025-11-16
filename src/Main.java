import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        Order orderSystem = new Order(restaurant);

        String choice;
        do {
            System.out.println("\n=== SISTEM RESTORAN NUSANTARA ===");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Pemilik");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    orderSystem.startOrdering();
                    break;
                case "2":
                    ownerMenu(restaurant);
                    break;
                case "3":
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (!choice.equals("3"));
    }

    static void ownerMenu(Restaurant restaurant) {
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.println("\n-- MENU PENGELOLAAN --");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Tambah Menu");
            System.out.println("3. Ubah Harga");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            input = sc.nextLine();

            switch (input) {
                case "1":
                    restaurant.displayMenus();
                    break;
                case "2":
                    restaurant.addMenu();
                    break;
                case "3":
                    restaurant.editMenu();
                    break;
                case "4":
                    restaurant.deleteMenu();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (!input.equals("5"));
    }
}


