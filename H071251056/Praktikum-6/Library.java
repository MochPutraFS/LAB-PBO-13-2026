import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<LibraryItem> items;
    private List<Member> members;
    private LibraryLogger logger;

    public Library() {
        this.items   = new ArrayList<>();
        this.members = new ArrayList<>();
        this.logger  = new LibraryLogger();
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
        return items.stream()
            .filter(i -> i.getItemId() == itemId)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "Item dengan ID " + itemId + " tidak ditemukan."));
    }

    public Member findMemberById(int memberId) {
        return members.stream()
            .filter(m -> m.getMemberId() == memberId)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "Member dengan ID " + memberId + " tidak ditemukan."));
    }

    public String borrowItem(int memberId, int itemId, int days) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);
        String result = member.borrow(item, days);

        String itemType = (item instanceof Book) ? "Buku" : "DVD";
        logger.logBorrow(itemType, item.getTitle(), member.getName());
        return result;
    }


    public String returnItem(int memberId, int itemId, int daysLate) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);
        String result = member.returnItem(item, daysLate);
        logger.logReturn(item.getTitle(), member.getName());
        return result;
    }

    public String getLibraryStatus() {
        if (items.isEmpty()) return "Tidak ada item di perpustakaan.";

        int idWidth     = 2;
        int titleWidth  = 5;
        int statusWidth = 8;

        for (LibraryItem item : items) {
            idWidth    = Math.max(idWidth, String.valueOf(item.getItemId()).length());
            titleWidth = Math.max(titleWidth, item.getTitle().length());
        }

        String border = "+" + "-".repeat(idWidth+2) + "+" + "-".repeat(titleWidth+2)
                      + "+" + "-".repeat(statusWidth+2) + "+";
        String fmt    = "| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + statusWidth + "s |";

        StringBuilder sb = new StringBuilder();
        sb.append(border).append("\n");
        sb.append(String.format(fmt, "ID", "Judul", "Status")).append("\n");
        sb.append(border).append("\n");
        for (LibraryItem item : items) {
            String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            sb.append(String.format(fmt,
                item.getItemId(), item.getTitle(), status)).append("\n");
        }
        sb.append(border);
        return sb.toString();
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public List<LibraryItem> getItems()   { return items; }
    public List<Member>      getMembers() { return members; }
}
