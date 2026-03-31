public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sản phẩm vật lý | ID: " + id
                + " | Tên: " + name
                + " | Giá: " + price
                + " | Trọng lượng: " + weight + " kg");
    }


}