import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibraryLogger {
    private List<String[]> logs;
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LibraryLogger() {
        this.logs = new ArrayList<>();
    }

    /**
     * @param itemType  
     * @param title     
     * @param memberName 
     */
    public void logBorrow(String itemType, String title, String memberName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        logs.add(new String[]{timestamp, itemType, title, memberName, "-"});
    }

    public void logReturn(String title, String memberName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        for (int i = logs.size() - 1; i >= 0; i--) {
            String[] log = logs.get(i);
            if (log[2].equals(title) && log[3].equals(memberName) && log[4].equals("-")) {
                log[4] = timestamp;
                return;
            }
        }
    }

    public String getLogs() {
        if (logs.isEmpty()) return "Belum ada aktivitas.";

        int w1 = "Dipinjam pada".length();
        int w2 = "Judul".length();
        int w3 = "Member".length();
        int w4 = "Dikembalikan pada".length();

        for (String[] log : logs) {
            w1 = Math.max(w1, log[0].length());
            w2 = Math.max(w2, log[2].length());
            w3 = Math.max(w3, log[3].length());
            w4 = Math.max(w4, log[4].length());
        }

        String border = "+" + "-".repeat(w1+2) + "+" + "-".repeat(w2+2)
                      + "+" + "-".repeat(w3+2) + "+" + "-".repeat(w4+2) + "+";
        String fmt = "| %-" + w1 + "s | %-" + w2 + "s | %-" + w3 + "s | %-" + w4 + "s |";

        StringBuilder sb = new StringBuilder();
        sb.append(border).append("\n");
        sb.append(String.format(fmt, "Dipinjam pada", "Judul", "Member", "Dikembalikan pada")).append("\n");
        sb.append(border).append("\n");
        for (String[] log : logs) {
            sb.append(String.format(fmt, log[0], log[2], log[3], log[4])).append("\n");
        }
        sb.append(border);
        return sb.toString();
    }
    public void clearLogs() {
        logs.clear();
    }
}
