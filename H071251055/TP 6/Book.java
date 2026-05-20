public class Book extends LibraryItem {
    private String author;

    public Book(String title, int itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    @Override
    public String getDescription() {
        return "Buku: " + title + " oleh " + author + ", ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        if (isBorrowed) {
            throw new IllegalArgumentException(
                "Item " + title + " sudah dipinjam dan belum dikembalikan."
            );
        }
        if (days > 14) {
            throw new IllegalArgumentException(
                "Buku hanya dapat dipinjam maksimal 14 hari. Anda meminta " + days + " hari."
            );
        }
        this.isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 10000;
    }

    public void printBooks() {
        System.out.println(getDescription());
    }

    public String getAuthor() {
        return author;
    }
}
