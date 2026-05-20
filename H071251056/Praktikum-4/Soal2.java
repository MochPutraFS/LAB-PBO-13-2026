import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String brand;
    int seriesNumber;
    double price;

    Product(String brand, int seriesNumber, double price) {
        this.brand = brand;
        this.seriesNumber = seriesNumber;
        this.price = price;
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Serial Number: " + seriesNumber);
        System.out.println("Price: $" + price);
    }
}

class Smartphone extends Product {
    double screenSize;
    int storageCapacity;

    Smartphone(String brand, int seriesNumber, double price, double screenSize, int storageCapacity) {
        super(brand, seriesNumber, price);
        this.screenSize = screenSize;
        this.storageCapacity = storageCapacity;
    }

    void displayInfo() {
        super.displayInfo();
        System.out.println("Screen Size: " + screenSize + " inches");
        System.out.println("Storage Capacity: " + storageCapacity + "GB");
    }
}

class Laptop extends Product {
    int ramSize;
    String processorType;

    Laptop(String brand, int seriesNumber, double price, int ramSize, String processorType) {
        super(brand, seriesNumber, price);
        this.ramSize = ramSize;
        this.processorType = processorType;
    }

    void displayInfo() {
        super.displayInfo();
        System.out.println("Processor Type: " + processorType);
        System.out.println("RAM Size: " + ramSize + "GB");
    }
}

class Camera extends Product {
    int resolution;
    String lensType;

    Camera(String brand, int seriesNumber, double price, int resolution, String lensType) {
        super(brand, seriesNumber, price);
        this.resolution = resolution;
        this.lensType = lensType;
    }

    void displayInfo() {
        super.displayInfo();
        System.out.println("Resolution: " + resolution + "MP");
        System.out.println("Lens Type: " + lensType);
    }
}

public class Soal2 {
    static ArrayList<Product> stok = new ArrayList<>();

    static void tampilkanMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Beli Produk");
        System.out.println("4. Keluar");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Data awal
        stok.add(new Smartphone("Samsung", 123456, 899.99, 6.5, 256));
        stok.add(new Laptop("Dell", 987654, 1299.99, 16, "Intel Core i7"));
        stok.add(new Camera("Canon", 456789, 699.99, 24, "Telephoto"));

        int pilihan;
        do {
            tampilkanMenu();
            System.out.print(">>> Pilih menu (1-4):  ");
            pilihan = sc.nextInt();
            sc.nextLine();

            if (pilihan == 1) {
                System.out.print("Masukkan nama produk: ");
                String brand = sc.nextLine();
                System.out.print("Masukkan nomor seri: ");
                int seri = sc.nextInt();
                System.out.print("Masukkan harga: ");
                double harga = sc.nextDouble();
                sc.nextLine();

                System.out.println("Pilih tipe produk:");
                System.out.println("1. Smartphone");
                System.out.println("2. Laptop");
                System.out.println("3. Camera");
                System.out.print("Pilih tipe produk (1-3): ");
                int tipe = sc.nextInt();
                sc.nextLine();

                if (tipe == 1) {
                    System.out.print("Masukkan ukuran layar (inci): ");
                    double layar = sc.nextDouble();
                    System.out.print("Masukkan kapasitas penyimpanan (GB): ");
                    int storage = sc.nextInt();
                    sc.nextLine();
                    stok.add(new Smartphone(brand, seri, harga, layar, storage));
                } else if (tipe == 2) {
                    System.out.print("Masukkan ukuran RAM (GB): ");
                    int ram = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Masukkan tipe prosesor: ");
                    String prosesor = sc.nextLine();
                    stok.add(new Laptop(brand, seri, harga, ram, prosesor));
                } else if (tipe == 3) {
                    System.out.print("Masukkan resolusi (MP): ");
                    int resolusi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Masukkan tipe lensa: ");
                    String lensa = sc.nextLine();
                    stok.add(new Camera(brand, seri, harga, resolusi, lensa));
                } else {
                    System.out.println("Tipe tidak valid!");
                }

            } else if (pilihan == 2) {
                System.out.println("Daftar Produk:");
                for (Product p : stok) {
                    p.displayInfo();
                    System.out.println();
                }

            } else if (pilihan == 3) {
                System.out.print("Masukkan nomor seri produk yang ingin dibeli: ");
                int seriCari = sc.nextInt();
                sc.nextLine();

                Product ditemukan = null;
                for (Product p : stok) {
                    if (p.seriesNumber == seriCari) {
                        ditemukan = p;
                        break;
                    }
                }

                if (ditemukan != null) {
                    System.out.println("Anda telah membeli produk:");
                    ditemukan.displayInfo();
                    stok.remove(ditemukan);
                } else {
                    System.out.println("Produk dengan nomor seri tersebut tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                System.out.println("Terima kasih telah menggunakan layanan kami. Sampai jumpa!");
            } else {
                System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 4);

        sc.close();
    }
}
