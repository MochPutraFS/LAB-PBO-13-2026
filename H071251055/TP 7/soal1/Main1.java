import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Sistem Logistik - The Central Warehouse ===");
        System.out.println("Kapasitas gudang: 50 unit");
        System.out.println("Sistem berjalan selama 15 detik...\n");

        // Inisialisasi gudang dengan kapasitas 50
        Gudang gudang = new Gudang(50);

        // Buat objek Pemasok dan Kurir
        Pemasok pemasok = new Pemasok(gudang);
        Kurir kurir = new Kurir(gudang);
        Monitoring monitoring = new Monitoring(gudang);

        // Thread pool untuk Pemasok (2 thread) dan Kurir (3 thread)
        ExecutorService pemasokPool = Executors.newFixedThreadPool(2);
        ExecutorService kurirPool   = Executors.newFixedThreadPool(3);

        // Thread terpisah untuk Monitoring (1 thread)
        Thread monitorThread = new Thread(monitoring);
        monitorThread.setDaemon(true); // Daemon agar ikut mati saat main selesai
        monitorThread.start();

        // Jalankan 2 Pemasok dan 3 Kurir
        for (int i = 0; i < 2; i++) pemasokPool.execute(pemasok);
        for (int i = 0; i < 3; i++) kurirPool.execute(kurir);

        // Biarkan sistem berjalan selama 15 detik
        Thread.sleep(15000);

        System.out.println("\n=== Waktu habis! Menghentikan semua thread... ===");

        // Hentikan semua thread secara aman
        pemasok.stop();
        kurir.stop();
        monitoring.stop();

        // Shutdown executor dan tunggu hingga selesai (maks 5 detik)
        pemasokPool.shutdown();
        kurirPool.shutdown();

        pemasokPool.awaitTermination(5, TimeUnit.SECONDS);
        kurirPool.awaitTermination(5, TimeUnit.SECONDS);

        // Paksa shutdown jika masih ada yang berjalan
        if (!pemasokPool.isTerminated()) pemasokPool.shutdownNow();
        if (!kurirPool.isTerminated())   kurirPool.shutdownNow();

        monitorThread.interrupt();

        System.out.println("=== Sistem berhasil dihentikan. Stok akhir: " + gudang.getStok() + " ===");
    }
}
