import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    private static Library library = new Library();
    private static Scanner scanner  = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistem Manajemen Perpustakaan ===");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Pilih menu: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> tambahItem();
                case "2" -> tambahAnggota();
                case "3" -> pinjamItem();
                case "4" -> kembalikanItem();
                case "5" -> System.out.println(library.getLibraryStatus());
                case "6" -> System.out.println(library.getAllLogs());
                case "7" -> lihatItemDipinjam();
                case "8" -> {
                    System.out.println("Terima kasih. Sampai jumpa!");
                    running = false;
                }
                default  -> System.out.println("Pilihan tidak valid, coba lagi.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("----------------------------------");
        System.out.println("1. Tambah Item");
        System.out.println("2. Tambah Anggota");
        System.out.println("3. Pinjam Item");
        System.out.println("4. Kembalikan Item");
        System.out.println("5. Lihat Status Perpustakaan");
        System.out.println("6. Lihat Log Aktivitas");
        System.out.println("7. Lihat Item yang Dipinjam Anggota");
        System.out.println("8. Keluar");
    }

    private static void tambahItem() {
        System.out.println("Pilih jenis item:");
        System.out.println("  1. Buku");
        System.out.println("  2. DVD");
        System.out.print("Pilihan: ");
        String jenis = scanner.nextLine().trim();

        try {
            System.out.print("ID Item: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Judul: ");
            String judul = scanner.nextLine().trim();

            if (jenis.equals("1")) {
                System.out.print("Penulis: ");
                String author = scanner.nextLine().trim();
                Book book = new Book(judul, id, author);
                System.out.println(library.addItem(book));
            } else if (jenis.equals("2")) {
                System.out.print("Durasi (menit): ");
                int durasi = Integer.parseInt(scanner.nextLine().trim());
                DVD dvd = new DVD(judul, id, durasi);
                System.out.println(library.addItem(dvd));
            } else {
                System.out.println("Jenis tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid: masukkan angka yang benar.");
        }
    }

    private static void tambahAnggota() {
        try {
            System.out.print("ID Anggota: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Nama: ");
            String nama = scanner.nextLine().trim();
            Member member = new Member(nama, id);
            System.out.println(library.addMember(member));
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid.");
        }
    }

    private static void pinjamItem() {
        try {
            System.out.print("ID Anggota: ");
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID Item: ");
            int itemId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Jumlah hari peminjaman: ");
            int days = Integer.parseInt(scanner.nextLine().trim());

            System.out.println(library.borrowItem(memberId, itemId, days));
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid.");
        } catch (NoSuchElementException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void kembalikanItem() {
        try {
            System.out.print("ID Anggota: ");
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID Item: ");
            int itemId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Keterlambatan (hari, 0 jika tepat waktu): ");
            int daysLate = Integer.parseInt(scanner.nextLine().trim());

            System.out.println(library.returnItem(memberId, itemId, daysLate));
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void lihatItemDipinjam() {
        try {
            System.out.print("ID Anggota: ");
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            Member member = library.findMemberById(memberId);
            System.out.println("Item yang dipinjam oleh " + member.getName() + ":");
            member.getBorrowedItems();
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
