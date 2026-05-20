import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<LibraryItem> items;
    private List<Member> members;

    private LibraryLogger logger;

    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.logger = new LibraryLogger();
    }

    public String addItem(LibraryItem item) {
        items.add(item);
        return item.getTitle() + " berhasil ditambahkan";
    }

    public String addMember(Member member) {
        members.add(member);
        return "Anggota " + member.getName() + " berhasil ditambahkan";
    }

    public LibraryItem findItemById(int itemId) {
        for (LibraryItem item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        throw new NoSuchElementException("Item dengan ID " + itemId + " tidak ditemukan.");
    }

    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new NoSuchElementException("Anggota dengan ID " + memberId + " tidak ditemukan.");
    }

    public String borrowItem(int memberId, int itemId, int days) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);
        String result = member.borrow(item, days);
        logger.logBorrow(item, member);
        return result;
    }

    public String returnItem(int memberId, int itemId, int daysLate) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);
        String result = member.returnItem(item, daysLate);
        logger.logReturn(item, member);
        return result;
    }

    public String getLibraryStatus() {
        if (items.isEmpty()) {
            return "Tidak ada item di perpustakaan.";
        }

        int col1 = 4; // ID
        int col2 = 5; // Judul
        int col3 = 8; // Status

        for (LibraryItem item : items) {
            if (String.valueOf(item.getItemId()).length() > col1) col1 = String.valueOf(item.getItemId()).length();
            if (item.getTitle().length() > col2) col2 = item.getTitle().length();
        }

        String sep = "+" + "-".repeat(col1 + 2) + "+" + "-".repeat(col2 + 2) + "+" + "-".repeat(col3 + 2) + "+";
        StringBuilder sb = new StringBuilder();
        sb.append(sep).append("\n");
        sb.append(String.format("| %-" + col1 + "s | %-" + col2 + "s | %-" + col3 + "s |", "ID", "Judul", "Status")).append("\n");
        sb.append(sep).append("\n");

        for (LibraryItem item : items) {
            String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            sb.append(String.format("| %-" + col1 + "d | %-" + col2 + "s | %-" + col3 + "s |",
                    item.getItemId(), item.getTitle(), status)).append("\n");
        }
        sb.append(sep);
        return sb.toString();
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<Member> getMembers() {
        return members;
    }

    public LibraryLogger getLogger() {
        return logger;
    }
}
