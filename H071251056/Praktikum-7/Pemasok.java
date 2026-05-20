import java.util.Random;

public class Pemasok implements Runnable {

    private final Gudang gudang;
    private final Random random = new Random();

    public Pemasok(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int jumlah = random.nextInt(15) + 1;
                gudang.tambahStok(jumlah);
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                // Thread diinterupsi, keluar dari loop dengan aman
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Pemasok dihentikan.%n",
                        Thread.currentThread().getName());
            }
        }
    }
}
