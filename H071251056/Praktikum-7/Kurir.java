import java.util.Random;
public class Kurir implements Runnable {

    private final Gudang gudang;
    private final Random random = new Random();

    public Kurir(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int jumlah = random.nextInt(10) + 1;
                gudang.ambilStok(jumlah);
                Thread.sleep(2000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Kurir dihentikan.%n",
                        Thread.currentThread().getName());
            }
        }
    }
}
