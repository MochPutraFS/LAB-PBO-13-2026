import java.util.Random;

public class Kurir implements Runnable {
    private final Gudang gudang;
    private final Random random = new Random();
    private volatile boolean berjalan = true;

    public Kurir(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (berjalan && !Thread.currentThread().isInterrupted()) {
            try {
                int jumlah = random.nextInt(5) + 1;
                gudang.ambilStok(jumlah);

                int delay = 2000 + random.nextInt(1000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        berjalan = false;
    }
}
