import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int memberId;
    private List<LibraryItem> borrowedItems;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedItems = new ArrayList<>();
    }

    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException(
                "Item " + item.getTitle() + " tidak tersedia, sudah dipinjam."
            );
        }
        String result = item.borrowItem(days);
        item.setBorrowed(true);
        borrowedItems.add(item);
        return "Item " + item.getTitle() + " berhasil dipinjam selama " + days + " hari";
    }

    public String returnItem(LibraryItem item, int daysLate) {
        item.returnItem();
        borrowedItems.remove(item);
        double fine = item.calculateFine(daysLate);
        String fineFormatted = formatRupiah(fine);
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: " + fineFormatted;
    }

    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item yang dipinjam");
            return;
        }

        // Hitung lebar kolom
        int idWidth = 6;
        int titleWidth = 10;
        for (LibraryItem item : borrowedItems) {
            if (String.valueOf(item.getItemId()).length() > idWidth) {
                idWidth = String.valueOf(item.getItemId()).length();
            }
            if (item.getTitle().length() > titleWidth) {
                titleWidth = item.getTitle().length();
            }
        }

        String separator = "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+";
        String header = String.format("| %-" + idWidth + "s | %-" + titleWidth + "s |", "ID", "Judul");

        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        for (LibraryItem item : borrowedItems) {
            System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s |%n",
                item.getItemId(), item.getTitle());
        }
        System.out.println(separator);
    }

    private String formatRupiah(double amount) {
        // Format: Rp xxx.xxx
        long val = (long) amount;
        String raw = String.valueOf(val);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = raw.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) sb.insert(0, '.');
            sb.insert(0, raw.charAt(i));
            count++;
        }
        return "Rp " + sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<LibraryItem> getBorrowedItemsList() {
        return borrowedItems;
    }
}
