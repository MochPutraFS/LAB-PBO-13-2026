import java.util.Random;

public class Pemasok implements Runnable {
    private final Gudang gudang;
    private final Random random = new Random();
    private volatile boolean berjalan = true;

    public Pemasok(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (berjalan && !Thread.currentThread().isInterrupted()) {
            try {
                // Tambah barang secara acak antara 1 - 10
                int jumlah = random.nextInt(10) + 1;
                gudang.tambahStok(jumlah);

                // Tunggu 1-2 detik sebelum menambah lagi
                int delay = 1000 + random.nextInt(1000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Kembalikan status interrupted
                break;
            }
        }
    }

    public void stop() {
        berjalan = false;
    }
}
