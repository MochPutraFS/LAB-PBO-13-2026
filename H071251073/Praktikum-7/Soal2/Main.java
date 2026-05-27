import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        ArrayList<String> dokumen = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            dokumen.add("Dokumen_" + i + ".txt");
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        ConcurrentHashMap<String, Integer> hasil =
                new ConcurrentHashMap<>();

        ConcurrentHashMap<String, String> threadMap =
                new ConcurrentHashMap<>();

        ConcurrentHashMap<String, Long> durasiMap =
                new ConcurrentHashMap<>();

        CountDownLatch latch =
                new CountDownLatch(dokumen.size());

        for (String file : dokumen) {

            executor.execute(
                    new DocumentTask(
                            file,
                            hasil,
                            threadMap,
                            durasiMap,
                            latch
                    )
            );
        }

        latch.await();

        executor.shutdown();

        System.out.println("\n===== HASIL AKHIR =====");

        int totalKata = 0;
        long totalDurasi = 0;

        System.out.printf(
                "%-20s %-20s %-15s\n",
                "Dokumen",
                "Thread",
                "Durasi(ms)"
        );

        for (String file : dokumen) {

            int kata = hasil.get(file);

            long durasi = durasiMap.get(file);

            totalKata += kata;

            totalDurasi += durasi;

            System.out.printf(
                    "%-20s %-20s %-15d\n",
                    file,
                    threadMap.get(file),
                    durasi
            );
        }

        double rataRata =
                (double) totalDurasi / dokumen.size();

        System.out.println("\nTotal Kata : " + totalKata);

        System.out.println(
                "Rata-rata Durasi : "
                        + rataRata + " ms");
    }
}