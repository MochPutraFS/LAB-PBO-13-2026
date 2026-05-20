import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibraryLogger {

    // Inner class untuk menyimpan data log lengkap
    private static class LogEntry {
        String borrowedAt;
        String title;
        String memberName;
        String returnedAt;

        LogEntry(String borrowedAt, String title, String memberName) {
            this.borrowedAt = borrowedAt;
            this.title = title;
            this.memberName = memberName;
            this.returnedAt = "-";
        }
    }

    private List<LogEntry> logs;
    private DateTimeFormatter formatter;

    public LibraryLogger() {
        this.logs = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public String logActivity(String activity) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logLine = timestamp + " " + activity;
        // Tambah sebagai raw log juga (untuk kompatibilitas)
        return logLine;
    }

    public void logBorrow(LibraryItem item, Member member) {
        String timestamp = LocalDateTime.now().format(formatter);
        String type = (item instanceof Book) ? "Buku" : "DVD";
        LogEntry entry = new LogEntry(timestamp, item.getTitle(), member.getName());
        logs.add(entry);
        // Juga print ke console sebagai info
        System.out.println(timestamp + " [" + type + "] dipinjam oleh " + member.getName());
    }

    public void logReturn(LibraryItem item, Member member) {
        String timestamp = LocalDateTime.now().format(formatter);
        // Update entry yang ada
        for (LogEntry entry : logs) {
            if (entry.title.equals(item.getTitle()) && entry.memberName.equals(member.getName())
                    && entry.returnedAt.equals("-")) {
                entry.returnedAt = timestamp;
                return;
            }
        }
    }

    public String getLogs() {
        if (logs.isEmpty()) {
            return "Tidak ada log aktivitas.";
        }

        // Hitung lebar kolom
        int col1 = "Dipinjam pada".length();
        int col2 = "Judul".length();
        int col3 = "Member".length();
        int col4 = "Dikembalikan pada".length();

        for (LogEntry e : logs) {
            if (e.borrowedAt.length() > col1) col1 = e.borrowedAt.length();
            if (e.title.length() > col2) col2 = e.title.length();
            if (e.memberName.length() > col3) col3 = e.memberName.length();
            if (e.returnedAt.length() > col4) col4 = e.returnedAt.length();
        }

        String sep = "+" + "-".repeat(col1 + 2) + "+" + "-".repeat(col2 + 2) + "+" + "-".repeat(col3 + 2) + "+" + "-".repeat(col4 + 2) + "+";

        StringBuilder sb = new StringBuilder();
        sb.append(sep).append("\n");
        sb.append(String.format("| %-" + col1 + "s | %-" + col2 + "s | %-" + col3 + "s | %-" + col4 + "s |",
                "Dipinjam pada", "Judul", "Member", "Dikembalikan pada")).append("\n");
        sb.append(sep).append("\n");

        for (LogEntry e : logs) {
            sb.append(String.format("| %-" + col1 + "s | %-" + col2 + "s | %-" + col3 + "s | %-" + col4 + "s |",
                    e.borrowedAt, e.title, e.memberName, e.returnedAt)).append("\n");
        }
        sb.append(sep);

        return sb.toString();
    }

    public void clearLogs() {
        logs.clear();
    }
}
