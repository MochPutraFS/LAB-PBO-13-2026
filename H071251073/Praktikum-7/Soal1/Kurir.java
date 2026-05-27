import java.util.Random;

public class Kurir implements Runnable {

    private final Gudang gudang;
    private final String nama;
    private final Random random = new Random();

    public Kurir(Gudang gudang, String nama) {
        this.gudang = gudang;
        this.nama = nama;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                int jumlah = random.nextInt(4) + 1;

                gudang.ambilStok(jumlah, nama);

                Thread.sleep((random.nextInt(2) + 2) * 1000);

            } catch (InterruptedException e) {
                System.out.println(nama + " dihentikan.");
                Thread.currentThread().interrupt();
            }
        }
    }
}