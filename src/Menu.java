public class Menu {
    String name;
    int price;
    String category; //"makanan" atau "minuman"

    public  Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category.toLowerCase();
    }
}
