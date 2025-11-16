import java.util.Scanner;

public class Restaurant {
    private Menu[] menus;
    private int menuCount;
    private Scanner sc = new Scanner(System.in);

    public Restaurant() {
        menus = new Menu[8];
        menuCount = 0;
        initDefaultMenus();
    }

    private void initDefaultMenus() {
        menus[0] = new Menu("Nasi Goreng Spesial", 25000, "makanan");
        menus[1] = new Menu("Ayam Geprek Sambal Bawang", 20000, "makanan");
        menus[2] = new Menu("Mie Ayam Bakso", 22000, "makanan");
        menus[3] = new Menu("Sate Ayam Madura", 28000, "makanan");
        menus[4] = new Menu("Es Teh Manis", 6000, "minuman");
        menus[5] = new Menu("Jus Jeruk Segar", 10000, "minuman");
        menus[6] = new Menu("Kopi Susu Gula Aren", 15000, "minuman");
        menus[7] = new Menu("Jus Alpukat Coklat", 18000, "minuman");
        menuCount = 8;
    }

    public void displayMenus() {
        System.out.println("\n--- Makanan ---");
        for (int i = 0; i < menuCount; i++) {
            if (menus[i].category.equalsIgnoreCase("makanan"))
                System.out.printf("- %s (Rp %d)%n", menus[i].name, menus[i].price);
        }

        System.out.println("--- Minuman ---");
        for (int i = 0; i < menuCount; i++) {
            if (menus[i].category.equalsIgnoreCase("minuman"))
                System.out.printf("- %s (Rp %d)%n", menus[i].name, menus[i].price);
        }
    }

    public void displayMenusNumbered() {
        System.out.println("\nDaftar Menu:");
        for (int i = 0; i < menuCount; i++) {
            System.out.printf("%d. %s (%s) - Rp %d%n", i + 1, menus[i].name, menus[i].category, menus[i].price);
        }
    }

    public Menu findMenuByName(String name) {
        for (int i = 0; i < menuCount; i++) {
            if (menus[i].name.equalsIgnoreCase(name.trim())) return menus[i];
        }
        return null;
    }

    // ===== CRUD (Create, Update, Delete) =====
    public void addMenu() {
        System.out.println("\n-- Tambah Menu Baru --");
        System.out.print("Nama menu: ");
        String name = sc.nextLine().trim();
        System.out.print("Kategori (makanan/minuman): ");
        String cat = sc.nextLine().trim();
        System.out.print("Harga: ");
        int price = Integer.parseInt(sc.nextLine().trim());

        if (menuCount >= menus.length) menus = expandMenuArray(menus);
        menus[menuCount++] = new Menu(name, price, cat);
        System.out.println("Menu berhasil ditambahkan!");
    }

    public void editMenu() {
        displayMenusNumbered();
        System.out.print("Nomor menu yang ingin diubah: ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx < 0 || idx >= menuCount) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        System.out.print("Harga baru: ");
        int newPrice = Integer.parseInt(sc.nextLine());
        menus[idx].price = newPrice;
        System.out.println("Harga berhasil diubah.");
    }

    public void deleteMenu() {
        displayMenusNumbered();
        System.out.print("Nomor menu yang ingin dihapus: ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx < 0 || idx >= menuCount) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        System.out.print("Yakin hapus " + menus[idx].name + "? (Ya/Tidak): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("ya")) {
            for (int i = idx; i < menuCount - 1; i++) {
                menus[i] = menus[i + 1];
            }
            menuCount--;
            System.out.println("Menu berhasil dihapus.");
        } else {
            System.out.println("Dibatalkan.");
        }
    }

    public Menu[] getMenus() { return menus; }
    public int getMenuCount() { return menuCount; }

    private Menu[] expandMenuArray(Menu[] arr) {
        Menu[] newArr = new Menu[arr.length + 4];
        for (int i = 0; i < arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }
}

