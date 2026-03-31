public class DigitalProduct extends Product {
    private double sizeInMB;

    public DigitalProduct(String id, String name, double price, double sizeInMB) {
        super(id, name, price);
        this.sizeInMB = sizeInMB;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public void setSizeInMB(double sizeInMB) {
        this.sizeInMB = sizeInMB;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sản phẩm kỹ thuật số | ID: " + id
                + " | Tên: " + name
                + " | Giá: " + price
                + " | Dung lượng: " + sizeInMB + " MB");
    }
}