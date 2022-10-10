package template_method;

public class Main {
    public static void main(String[] args) {
        HouseTemplate houseType = new WoodenHouse();
        houseType.buildHouse();

        houseType = new GlassHouse();
        houseType.buildHouse();
    }
}
