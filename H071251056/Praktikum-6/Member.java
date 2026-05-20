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

    public String getName()    { return name; }
    public int getMemberId()   { return memberId; }
    public List<LibraryItem> getBorrowedItemsList() { return borrowedItems; }

    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException(
                "Item \"" + item.getTitle() + "\" tidak tersedia (sedang dipinjam).");
        }
        String result = item.borrowItem(days);
        item.setBorrowed(true);
        borrowedItems.add(item);
        return "Item " + item.getTitle() + " berhasil dipinjam selama " + days + " hari";
    }

    public String returnItem(LibraryItem item, int daysLate) {
        item.returnItem(); // ubah isBorrowed = false
        borrowedItems.remove(item);
        double fine = item.calculateFine(daysLate);
        String fineFormatted = formatRupiah((long) fine);
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + fineFormatted;
    }

    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item yang dipinjam");
            return;
        }
        int idWidth    = 4;
        int titleWidth = 5;
        for (LibraryItem item : borrowedItems) {
            idWidth    = Math.max(idWidth, String.valueOf(item.getItemId()).length());
            titleWidth = Math.max(titleWidth, item.getTitle().length());
        }
        String border = "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+";
        String header = String.format("| %-" + idWidth + "s | %-" + titleWidth + "s |", "ID", "Judul");

        System.out.println(border);
        System.out.println(header);
        System.out.println(border);
        for (LibraryItem item : borrowedItems) {
            System.out.printf("| %-" + idWidth + "d | %-" + titleWidth + "s |%n",
                item.getItemId(), item.getTitle());
        }
        System.out.println(border);
    }

    private String formatRupiah(long amount) {
        String raw = String.valueOf(amount);
        StringBuilder sb = new StringBuilder();
        int start = raw.length() % 3;
        if (start > 0) sb.append(raw, 0, start);
        for (int i = start; i < raw.length(); i += 3) {
            if (sb.length() > 0) sb.append('.');
            sb.append(raw, i, i + 3);
        }
        return sb.toString();
    }
}
