public class Book extends LibraryItem {
    private String author;
    private static final int MAX_BORROW_DAYS = 14;
    private static final double FINE_PER_DAY  = 10000;

    public Book(String title, int itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    public String getAuthor() { return author; }

    @Override
    public String getDescription() {
        return "Buku: " + title + " oleh " + author + ", ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        // Validasi: item sudah dipinjam
        if (isBorrowed) {
            throw new IllegalArgumentException(
                "Buku \"" + title + "\" sudah dipinjam dan belum dikembalikan.");
        }
        if (days > MAX_BORROW_DAYS) {
            throw new IllegalArgumentException(
                "Buku hanya bisa dipinjam maksimal " + MAX_BORROW_DAYS + " hari.");
        }
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * FINE_PER_DAY;
    }

    public void printBooks() {
        System.out.println(getDescription());
    }
}
