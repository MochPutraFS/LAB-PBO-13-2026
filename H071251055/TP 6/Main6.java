import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main6 {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Pilihan: ");

            switch (choice) {
                case 1 -> tambahItem();
                case 2 -> tambahAnggota();
                case 3 -> pinjamItem();
                case 4 -> kembalikanItem();
                case 5 -> lihatStatus();
                case 6 -> lihatLog();
                case 7 -> lihatItemDipinjamAnggota();
                case 8 -> {
                    System.out.println("Terima kasih. Program selesai.");
                    running = false;
                }
                default -> System.out.println("[!] Pilihan tidak valid. Silakan pilih 1-8.");
            }

            if (running) {
                System.out.println();
            }
        }
        scanner.close();
    }

    // ── MENU ────────────────────────────────────────────────────────────────
    private static void printMenu() {
        System.out.println("=== Sistem Manajemen Perpustakaan ===");
        System.out.println("1. Tambah Item");
        System.out.println("2. Tambah Anggota");
        System.out.println("3. Pinjam Item");
        System.out.println("4. Kembalikan Item");
        System.out.println("5. Lihat Status Perpustakaan");
        System.out.println("6. Lihat Log Aktivitas");
        System.out.println("7. Lihat Item yang Dipinjam Anggota");
        System.out.println("8. Keluar");
        // System.out.print("Pilihan: ");
    }

    // ── FITUR 1: Tambah Item ────────────────────────────────────────────────
    private static void tambahItem() {
        System.out.println("\n-- Tambah Item --");
        System.out.println("Jenis item:");
        System.out.println("1. Buku");
        System.out.println("2. DVD");
        int jenis = readInt("Pilih jenis: ");

        String title = readString("Judul: ");
        int itemId  = readInt("ID Item: ");

        if (jenis == 1) {
            String author = readString("Penulis: ");
            Book book = new Book(title, itemId, author);
            System.out.println(library.addItem(book));
        } else if (jenis == 2) {
            int duration = readInt("Durasi (menit): ");
            DVD dvd = new DVD(title, itemId, duration);
            System.out.println(library.addItem(dvd));
        } else {
            System.out.println("[!] Jenis tidak valid.");
        }
    }

    // ── FITUR 2: Tambah Anggota ─────────────────────────────────────────────
    private static void tambahAnggota() {
        System.out.println("\n-- Tambah Anggota --");
        String name     = readString("Nama Anggota: ");
        int    memberId = readInt("ID Anggota  : ");
        Member member   = new Member(name, memberId);
        System.out.println(library.addMember(member));
    }

    // ── FITUR 3: Pinjam Item ─────────────────────────────────────────────────
    private static void pinjamItem() {
        System.out.println("\n-- Pinjam Item --");
        int memberId = readInt("ID Anggota: ");
        int itemId   = readInt("ID Item   : ");
        int days     = readInt("Lama pinjam (hari): ");

        try {
            String result = library.borrowItem(memberId, itemId, days);
            System.out.println(result);
        } catch (NoSuchElementException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ── FITUR 4: Kembalikan Item ─────────────────────────────────────────────
    private static void kembalikanItem() {
        System.out.println("\n-- Kembalikan Item --");
        int memberId = readInt("ID Anggota: ");
        int itemId   = readInt("ID Item   : ");
        int daysLate = readInt("Keterlambatan (hari, 0 jika tidak terlambat): ");

        try {
            String result = library.returnItem(memberId, itemId, daysLate);
            System.out.println(result);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ── FITUR 5: Lihat Status ────────────────────────────────────────────────
    private static void lihatStatus() {
        System.out.println("\n-- Status Perpustakaan --");
        System.out.println(library.getLibraryStatus());
    }

    // ── FITUR 6: Lihat Log ───────────────────────────────────────────────────
    private static void lihatLog() {
        System.out.println("\n-- Log Aktivitas --");
        System.out.println(library.getAllLogs());
    }

    // ── FITUR 7: Item Dipinjam Anggota ───────────────────────────────────────
    private static void lihatItemDipinjamAnggota() {
        System.out.println("\n-- Item yang Dipinjam Anggota --");
        int memberId = readInt("ID Anggota: ");

        try {
            Member member = library.findMemberById(memberId);
            System.out.println("Item yang dipinjam oleh " + member.getName() + ":");
            member.getBorrowedItems();
        } catch (NoSuchElementException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ── HELPER ───────────────────────────────────────────────────────────────
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("[!] Input harus berupa angka. Coba lagi.");
                scanner.nextLine();
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
