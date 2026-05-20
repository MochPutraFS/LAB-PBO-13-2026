import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

abstract class LibraryItem {
    protected String title;
    protected int itemId;
    protected boolean isBorrowed;

    public LibraryItem(String title, int itemId) {
        this.title = title;
        this.itemId = itemId;
        this.isBorrowed = false;
    }

    public abstract String getDescription();
    public abstract String borrowItem(int days);
    public abstract double calculateFine(int daysLate);

    public String returnItem() {
        this.isBorrowed = false;
        return title + " dikembalikan";
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public int getItemId() {
        return itemId;
    }
}

class Book extends LibraryItem {
    private String author;

    public Book(String title, int itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    @Override
    public String borrowItem(int days) {
        if (days > 14 || isBorrowed) {
            throw new IllegalArgumentException("Buku tidak bisa dipinjam.");
        }
        isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 10000;
    }

    @Override
    public String getDescription() {
        return "Buku: " + title + " oleh " + author + ", ID: " + itemId;
    }
}

class DVD extends LibraryItem {
    private int duration;

    public DVD(String title, int itemId, int duration) {
        super(title, itemId);
        this.duration = duration;
    }

    @Override
    public String borrowItem(int days) {
        if (days > 7 || isBorrowed) {
            throw new IllegalArgumentException("DVD tidak bisa dipinjam.");
        }
        isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 25000;
    }

    @Override
    public String getDescription() {
        return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
    }
}

class Member {
    private String name;
    private String memberId;
    private List<LibraryItem> borrowedItems;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedItems = new ArrayList<>();
    }

    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException("Item sedang dipinjam.");
        }

        String result = item.borrowItem(days);
        borrowedItems.add(item);
        return result;
    }

    public String returnItem(LibraryItem item, int daysLate) {
        if (!borrowedItems.contains(item)) {
            throw new IllegalArgumentException("Item tidak dipinjam oleh member ini.");
        }

        borrowedItems.remove(item);
        double fine = item.calculateFine(daysLate);
        item.returnItem();
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + fine;
    }

    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item yang dipinjam");
        } else {
            for (LibraryItem item : borrowedItems) {
                System.out.println(item.getDescription());
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }
}

class LibraryLogger {
    private List<String> logs = new ArrayList<>();

    public String logActivity(String activity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String log = LocalDateTime.now().format(formatter) + " " + activity;
        logs.add(log);
        return log;
    }

    public String getLogs() {
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append(log).append("\n");
        }
        return sb.toString();
    }

    public void clearLogs() {
        logs.clear();
    }
}

class Library {
    private List<LibraryItem> items = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private LibraryLogger logger = new LibraryLogger();

    public String addItem(LibraryItem item) {
        items.add(item);
        return item.getTitle() + " berhasil ditambahkan";
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member findMemberById(String id) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Member tidak ditemukan"));
    }

    public LibraryItem findItemById(int itemId) {
        return items.stream()
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item tidak ditemukan"));
    }

    public String getLibraryStatus() {
        StringBuilder sb = new StringBuilder();
        for (LibraryItem item : items) {
            sb.append(item.getDescription())
              .append(" - ")
              .append(item.isBorrowed() ? "Dipinjam" : "Tersedia")
              .append("\n");
        }
        return sb.toString();
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public LibraryLogger getLogger() {
        return logger;
    }
}

public class Tuprak6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Item");
            System.out.println("4. Kembalikan Item");
            System.out.println("5. Lihat Status Perpustakaan");
            System.out.println("6. Lihat Log Aktivitas");
            System.out.println("7. Lihat Item yang Dipinjam Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilih: ");

            int pilih = sc.nextInt();
            sc.nextLine();

            try {
                switch (pilih) {

                    case 1:
                        System.out.println("1. Buku");
                        System.out.println("2. DVD");
                        System.out.print("Pilih: ");
                        int jenis = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Judul: ");
                        String title = sc.nextLine();
                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        if (jenis == 1) {
                            sc.nextLine();
                            System.out.print("Author: ");
                            String author = sc.nextLine();
                            System.out.println(lib.addItem(new Book(title, id, author)));
                        } else {
                            System.out.print("Durasi: ");
                            int durasi = sc.nextInt();
                            System.out.println(lib.addItem(new DVD(title, id, durasi)));
                        }
                        break;

                    case 2:
                        System.out.print("Nama: ");
                        String nama = sc.nextLine();
                        System.out.print("ID Member: ");
                        String memberId = sc.nextLine();

                        lib.addMember(new Member(nama, memberId));
                        System.out.println("Anggota berhasil ditambahkan");
                        break;

                    case 3:
                        System.out.print("ID Member: ");
                        String mId = sc.nextLine();
                        Member member = lib.findMemberById(mId);

                        System.out.print("ID Item: ");
                        int itemId = sc.nextInt();
                        System.out.print("Hari: ");
                        int days = sc.nextInt();

                        LibraryItem item = lib.findItemById(itemId);
                        String res = member.borrow(item, days);

                        lib.getLogger().logActivity(item.getTitle() + " dipinjam oleh " + member.getName());
                        System.out.println(res);
                        break;

                    case 4:
                        System.out.print("ID Member: ");
                        String mId2 = sc.nextLine();
                        Member member2 = lib.findMemberById(mId2);

                        System.out.print("ID Item: ");
                        int itemId2 = sc.nextInt();
                        System.out.print("Terlambat: ");
                        int late = sc.nextInt();

                        LibraryItem item2 = lib.findItemById(itemId2);
                        String res2 = member2.returnItem(item2, late);

                        lib.getLogger().logActivity(item2.getTitle() + " dikembalikan oleh " + member2.getName());
                        System.out.println(res2);
                        break;

                    case 5:
                        System.out.println(lib.getLibraryStatus());
                        break;

                    case 6:
                        System.out.println(lib.getAllLogs());
                        break;

                    case 7:
                        System.out.print("ID Member: ");
                        String mId3 = sc.nextLine();
                        Member member3 = lib.findMemberById(mId3);
                        member3.getBorrowedItems();
                        break;

                    case 8:
                        System.out.println("Keluar...");
                        return;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}