import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main2{

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== High-Performance Data Indexer - The Search Engine Core ===\n");

        // Daftar 10 dokumen simulasi yang akan diproses
        List<String> dokumen = Arrays.asList(
            "Dokumen_A.txt", "Dokumen_B.txt", "Dokumen_C.txt",
            "Dokumen_D.txt", "Dokumen_E.txt", "Dokumen_F.txt",
            "Dokumen_G.txt", "Dokumen_H.txt", "Dokumen_I.txt",
            "Dokumen_J.txt"
        );

        int jumlahThread = 4;
        int jumlahDokumen = dokumen.size();

        // ConcurrentHashMap: banyak thread yang menulis data secara bersamaan
        ConcurrentHashMap<String, Integer> hasilIndexing = new ConcurrentHashMap<>();

        // Menyimpan info tambahan: thread name dan durasi untuk output akhir
        ConcurrentHashMap<String, String[]> infoProses = new ConcurrentHashMap<>();

        // CountDownLatch: digunakan sebagai "penghalang" agar tidak terjadi race condition
        CountDownLatch latch = new CountDownLatch(jumlahDokumen);

        // Counter untuk progress (thread-safe dengan AtomicInteger)
        AtomicInteger selesai = new AtomicInteger(0);

        // ExecutorService dengan 4 thread tetap
        ExecutorService executor = Executors.newFixedThreadPool(jumlahThread);
        DataProcessor processor = new DataProcessor();

        System.out.println("Memproses " + jumlahDokumen + " dokumen dengan " + jumlahThread + " thread paralel...\n");

        long startAll = System.currentTimeMillis();

        // Submit tugas untuk setiap dokumen
        for (String nama : dokumen) {
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                long startWaktu = System.currentTimeMillis();

                try {
                    // Proses dokumen (simulasi)
                    int jumlahKata = processor.process(nama);
                    long durasiMs = System.currentTimeMillis() - startWaktu;

                    // Simpan hasil ke ConcurrentHashMap (thread-safe)
                    hasilIndexing.put(nama, jumlahKata);
                    infoProses.put(nama, new String[]{threadName, durasiMs + "ms"});

                    // Tampilkan progres real-time
                    int sudahSelesai = selesai.incrementAndGet();
                    System.out.printf("[%s] Selesai memproses %s (%d kata) | Progres: %d/%d%n",
                            threadName, nama, jumlahKata, sudahSelesai, jumlahDokumen);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown(); // Kurangi counter latch
                }
            });
        }

        // Main thread MENUNGGU di sini sampai semua dokumen selesai (latch = 0)
        latch.await();

        long totalWaktu = System.currentTimeMillis() - startAll;

        // Shutdown executor setelah semua tugas selesai
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                   HASIL AKHIR INDEXING DOKUMEN");
        System.out.println("=".repeat(70));
        System.out.printf("%-18s %-25s %-12s %-10s%n",
                "Nama Dokumen", "Thread", "Durasi", "Jumlah Kata");
        System.out.println("-".repeat(70));

        long totalKata = 0;
        long totalDurasiMs = 0;
        List<String> sortedKeys = new ArrayList<>(hasilIndexing.keySet());
        sortedKeys.sort(String::compareTo);

        for (String nama : sortedKeys) {
            int kata = hasilIndexing.get(nama);
            String[] info = infoProses.get(nama);
            String thread = info[0];
            String durasi = info[1];

            totalKata += kata;
            totalDurasiMs += Long.parseLong(durasi.replace("ms", ""));

            System.out.printf("%-18s %-25s %-12s %-10d%n",
                    nama, thread, durasi, kata);
        }

        System.out.println("-".repeat(70));
        System.out.printf("%-18s %-25s %-12s %-10d%n",
                "TOTAL", "", "", totalKata);
        System.out.println("=".repeat(70));
        System.out.printf("Total Kata Keseluruhan  : %d kata%n", totalKata);
        System.out.printf("Rata-rata Waktu Proses  : %.0f ms%n", (double) totalDurasiMs / jumlahDokumen);
        System.out.printf("Total Waktu Keseluruhan : %d ms (paralel)%n", totalWaktu);
        System.out.println("=".repeat(70));
    }
}
