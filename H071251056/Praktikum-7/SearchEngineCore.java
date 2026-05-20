import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchEngineCore {

    private static final ConcurrentHashMap<String, Integer> hasilIndex   = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String>  infoThread   = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long>    durasiProses = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        List<String> dokumen = Arrays.asList(
                "Dokumen_A.txt", "Dokumen_B.txt", "Dokumen_C.txt",
                "Dokumen_D.txt", "Dokumen_E.txt", "Dokumen_F.txt",
                "Dokumen_G.txt", "Dokumen_H.txt", "Dokumen_I.txt",
                "Dokumen_J.txt", "Dokumen_K.txt", "Dokumen_L.txt"
        );

        int jumlahDokumen = dokumen.size();

        CountDownLatch latch = new CountDownLatch(jumlahDokumen);

        AtomicInteger threadCounter = new AtomicInteger(1);
        ExecutorService executor = Executors.newFixedThreadPool(4,
                r -> {
                    Thread t = new Thread(r);
                    t.setName("Thread-" + threadCounter.getAndIncrement());
                    return t;
                });

        DataProcessor processor = new DataProcessor();

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║        SEARCH ENGINE CORE - INDEXING DIMULAI         ║");
        System.out.printf( "║  Total Dokumen : %-5d | Thread Pool : 4 thread      ║%n", jumlahDokumen);
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        long waktuMulaiTotal = System.currentTimeMillis();
        for (String namaFile : dokumen) {
            executor.submit(() -> {
                String namaThread = Thread.currentThread().getName();
                long waktuMulai   = System.currentTimeMillis();
                try {
                    int jumlahKata = processor.process(namaFile);
                    long durasi    = System.currentTimeMillis() - waktuMulai;

                    hasilIndex.put(namaFile,   jumlahKata);
                    infoThread.put(namaFile,   namaThread);
                    durasiProses.put(namaFile, durasi);

                    System.out.printf("[%s] Selesai memproses %-18s (%3d kata) dalam %4dms%n",
                            namaThread, namaFile, jumlahKata, durasi);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.printf("[%s] Gagal memproses %s: diinterupsi.%n",
                            namaThread, namaFile);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
        long totalWaktu = System.currentTimeMillis() - waktuMulaiTotal;

        tampilkanLaporan(dokumen, totalWaktu);
    }

    private static void tampilkanLaporan(List<String> dokumen, long totalWaktu) {
        final String SEPARATOR = "═".repeat(72);
        final String DIVIDER   = "─".repeat(72);

        System.out.println("\n" + SEPARATOR);
        System.out.println("               LAPORAN AKHIR - SEARCH ENGINE INDEX");
        System.out.println(SEPARATOR);
        System.out.printf("%-20s  %-14s  %-14s  %-10s%n",
                "Nama Dokumen", "Thread", "Durasi (ms)", "Jumlah Kata");
        System.out.println(DIVIDER);

        int  totalKata  = 0;
        long totalDurasi = 0;

        for (String nama : dokumen) {
            int   kata   = hasilIndex.getOrDefault(nama, 0);
            String thread = infoThread.getOrDefault(nama, "N/A");
            long  durasi = durasiProses.getOrDefault(nama, 0L);

            totalKata   += kata;
            totalDurasi += durasi;

            System.out.printf("%-20s  %-14s  %-14d  %-10d%n",
                    nama, thread, durasi, kata);
        }

        System.out.println(DIVIDER);

        double rataRataDurasi = (double) totalDurasi / dokumen.size();

        System.out.printf("%-20s  %-14s  %-14s  %-10d%n",
                "TOTAL (" + dokumen.size() + " dok)", "", "", totalKata);
        System.out.printf("%-20s  %-14s  %-14.2f%n",
                "RATA-RATA", "", rataRataDurasi);
        System.out.println(SEPARATOR);
        System.out.printf("  Total kata keseluruhan      : %d kata%n", totalKata);
        System.out.printf("  Rata-rata waktu pemrosesan  : %.2f ms / dokumen%n", rataRataDurasi);
        System.out.printf("  Total waktu keseluruhan     : %d ms%n", totalWaktu);
        System.out.println(SEPARATOR);
    }
}
