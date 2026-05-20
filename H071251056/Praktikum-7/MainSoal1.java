import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainSoal1 {

    public static void main(String[] args) throws InterruptedException {

        final int KAPASITAS_GUDANG = 50;
        Gudang gudang = new Gudang(KAPASITAS_GUDANG);
        ExecutorService pemasokPool = Executors.newFixedThreadPool(2,
                r -> {
                    Thread t = new Thread(r);
                    t.setName("Pemasok-" + t.threadId());
                    return t;
                });
        ExecutorService kurirPool = Executors.newFixedThreadPool(3,
                r -> {
                    Thread t = new Thread(r);
                    t.setName("Kurir-" + t.threadId());
                    return t;
                });
        Thread monitoringThread = new Thread(new Monitoring(gudang), "Monitoring");
        monitoringThread.setDaemon(true);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     THE CENTRAL WAREHOUSE - SISTEM MULAI     ║");
        System.out.printf( "║  Kapasitas Gudang: %-5d | Durasi: 15 detik  ║%n", KAPASITAS_GUDANG);
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        for (int i = 0; i < 2; i++) pemasokPool.submit(new Pemasok(gudang));
        for (int i = 0; i < 3; i++) kurirPool.submit(new Kurir(gudang));
        monitoringThread.start();

        Thread.sleep(15_000);
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  Waktu habis. Menghentikan semua thread...");
        System.out.println("══════════════════════════════════════════════");

        pemasokPool.shutdownNow();
        kurirPool.shutdownNow();
        monitoringThread.interrupt();

        boolean pemasokBerhenti = pemasokPool.awaitTermination(5, TimeUnit.SECONDS);
        boolean kurirBerhenti   = kurirPool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║          SISTEM GUDANG DIHENTIKAN            ║");
        System.out.printf( "║  Pemasok pool berhenti : %-20s║%n", pemasokBerhenti ? "Ya" : "Timeout");
        System.out.printf( "║  Kurir pool berhenti   : %-20s║%n", kurirBerhenti   ? "Ya" : "Timeout");
        System.out.printf( "║  Stok akhir gudang     : %-20s║%n", gudang.getStok() + " barang");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
