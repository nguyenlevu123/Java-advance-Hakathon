import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductDatabase database = ProductDatabase.getInstance();

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = inputInt("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

            System.out.println();
        } while (choice != 5);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("--------------- QUẢN LÝ SẢN PHẨM ---------------");
        System.out.println("1. Thêm mới sản phẩm");
        System.out.println("2. Xem danh sách sản phẩm");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Xoá sản phẩm");
        System.out.println("5. Thoát");
        System.out.println("------------------------------------------------");
    }

    private static void addProduct() {
        System.out.println("Chọn loại sản phẩm:");
        System.out.println("1. Sản phẩm vật lý");
        System.out.println("2. Sản phẩm kỹ thuật số");
        String type = inputString("Nhập loại: ");

        String id = inputString("Nhập ID: ");

        if (database.findById(id) != null) {
            System.out.println("ID đã tồn tại. Không thể thêm sản phẩm.");
            return;
        }

        String name = inputString("Nhập tên sản phẩm: ");
        double price = inputDouble("Nhập giá sản phẩm: ");

        double extraValue;
        if ("1".equals(type)) {
            extraValue = inputDouble("Nhập trọng lượng (kg): ");
        } else if ("2".equals(type)) {
            extraValue = inputDouble("Nhập dung lượng (MB): ");
        } else {
            System.out.println("Loại sản phẩm không hợp lệ.");
            return;
        }

        Product product = ProductFactory.createProduct(type, id, name, price, extraValue);

        if (product != null) {
            database.addProduct(product);
            System.out.println("Thêm sản phẩm thành công.");
        } else {
            System.out.println("Tạo sản phẩm thất bại.");
        }
    }

    private static void displayProducts() {
        List<Product> products = database.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm đang trống.");
            return;
        }

        System.out.println("Danh sách sản phẩm:");
        for (Product product : products) {
            product.displayInfo();
        }
    }

    private static void updateProduct() {
        String id = inputString("Nhập ID sản phẩm cần cập nhật: ");
        Product existingProduct = database.findById(id);

        if (existingProduct == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
            return;
        }

        String newName = inputString("Nhập tên mới: ");
        double newPrice = inputDouble("Nhập giá mới: ");

        double newExtraValue;
        if (existingProduct instanceof PhysicalProduct) {
            newExtraValue = inputDouble("Nhập trọng lượng mới (kg): ");
        } else {
            newExtraValue = inputDouble("Nhập dung lượng mới (MB): ");
        }

        boolean updated = database.updateProduct(id, newName, newPrice, newExtraValue);

        if (updated) {
            System.out.println("Cập nhật sản phẩm thành công.");
        } else {
            System.out.println("Cập nhật thất bại.");
        }
    }

    private static void deleteProduct() {
        String id = inputString("Nhập ID sản phẩm cần xoá: ");
        boolean deleted = database.deleteProduct(id);

        if (deleted) {
            System.out.println("Xoá sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    private static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
            }
        }
    }
}