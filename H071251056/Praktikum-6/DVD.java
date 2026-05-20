public class DVD extends LibraryItem {
    private int duration; // dalam menit
    private static final int    MAX_BORROW_DAYS = 7;
    private static final double FINE_PER_DAY    = 25000;

    public DVD(String title, int itemId, int duration) {
        super(title, itemId);
        this.duration = duration;
    }

    public int getDuration() { return duration; }

    @Override
    public String getDescription() {
        return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        if (days > MAX_BORROW_DAYS) {
            throw new IllegalArgumentException(
                "DVD hanya bisa dipinjam maksimal " + MAX_BORROW_DAYS + " hari.");
        }
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * FINE_PER_DAY;
    }
}
