import java.util.Scanner;

abstract class Bangun {
    abstract void hitung(Scanner sc);
}

class Kubus extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan sisi kubus: ");
        double s = sc.nextDouble();
        System.out.println("Luas kubus: " + (6 * s * s));
        System.out.println("Volume kubus: " + (s * s * s));
    }
}

class Balok extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan panjang balok: ");
        double p = sc.nextDouble();
        System.out.print("Masukkan lebar balok: ");
        double l = sc.nextDouble();
        System.out.print("Masukkan tinggi balok: ");
        double t = sc.nextDouble();
        System.out.println("Luas balok: " + (2 * (p*l + p*t + l*t)));
        System.out.println("Volume balok: " + (p * l * t));
    }
}

class Bola extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan jari-jari bola: ");
        double r = sc.nextDouble();
        System.out.println("Luas bola: " + (4 * Math.PI * r * r));
        System.out.println("Volume bola: " + ((4.0/3) * Math.PI * r * r * r));
    }
}

class Tabung extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan jari-jari tabung: ");
        double r = sc.nextDouble();
        System.out.print("Masukkan tinggi tabung: ");
        double t = sc.nextDouble();
        System.out.println("Luas tabung: " + (2 * Math.PI * r * (r + t)));
        System.out.println("Volume tabung: " + (Math.PI * r * r * t));
    }
}

class Persegi extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan sisi persegi: ");
        double s = sc.nextDouble();
        System.out.println("Luas persegi: " + (s * s));
        System.out.println("Keliling persegi: " + (4 * s));
    }
}

class PersegiPanjang extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan panjang persegi panjang: ");
        double p = sc.nextDouble();
        System.out.print("Masukkan lebar persegi panjang: ");
        double l = sc.nextDouble();
        System.out.println("Luas persegi panjang: " + (p * l));
        System.out.println("Keliling persegi panjang: " + (2 * (p + l)));
    }
}

class Lingkaran extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan jari-jari lingkaran: ");
        double r = sc.nextDouble();
        System.out.println("Luas lingkaran: " + (Math.PI * r * r));
        System.out.println("Keliling lingkaran: " + (2 * Math.PI * r));
    }
}

class Trapesium extends Bangun {
    void hitung(Scanner sc) {
        System.out.print("Masukkan sisi 1 trapesium: ");
        double s1 = sc.nextDouble();
        System.out.print("Masukkan sisi 2 trapesium: ");
        double s2 = sc.nextDouble();
        System.out.print("Masukkan sisi 3 trapesium: ");
        double s3 = sc.nextDouble();
        System.out.print("Masukkan sisi 4 trapesium: ");
        double s4 = sc.nextDouble();
        System.out.print("Masukkan tinggi trapesium: ");
        double t = sc.nextDouble();
        System.out.println("Luas trapesium: " + (0.5 * (s1 + s2) * t));
        System.out.println("Keliling trapesium: " + (s1 + s2 + s3 + s4));
    }
}

public class Soal1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("----------------------------------------");
            System.out.println("  ===== BANGUN RUANG =====");
            System.out.println("1. KUBUS");
            System.out.println("2. BALOK");
            System.out.println("3. BOLA");
            System.out.println("4. TABUNG");
            System.out.println();
            System.out.println("  ===== BANGUN DATAR =====");
            System.out.println("5. PERSEGI");
            System.out.println("6. PERSEGI PANJANG");
            System.out.println("7. LINGKARAN");
            System.out.println("8. TRAPESIUM");
            System.out.println("----------------------------------------");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();
            System.out.println("----------------------------------------");

            Bangun bangun = null;
            switch (pilihan) {
                case 1: bangun = new Kubus(); break;
                case 2: bangun = new Balok(); break;
                case 3: bangun = new Bola(); break;
                case 4: bangun = new Tabung(); break;
                case 5: bangun = new Persegi(); break;
                case 6: bangun = new PersegiPanjang(); break;
                case 7: bangun = new Lingkaran(); break;
                case 8: bangun = new Trapesium(); break;
                default: System.out.println("Pilihan tidak valid!"); break;
            }

            if (bangun != null) {
                bangun.hitung(sc);
            }
            System.out.println("----------------------------------------");
        } while (true);
    }
}
