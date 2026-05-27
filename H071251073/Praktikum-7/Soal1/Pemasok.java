import java.util.Random;

public class Pemasok implements Runnable {

    private final Gudang gudang;
    private final String nama;
    private final Random random = new Random();

    public Pemasok(Gudang gudang, String nama) {
        this.gudang = gudang;
        this.nama = nama;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                int jumlah = random.nextInt(5) + 1;

                gudang.tambahStok(jumlah, nama);

                Thread.sleep((random.nextInt(2) + 1) * 1000);

            } catch (InterruptedException e) {
                System.out.println(nama + " dihentikan.");
                Thread.currentThread().interrupt();
            }
        }
    }
}