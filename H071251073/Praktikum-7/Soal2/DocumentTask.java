import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class DocumentTask implements Runnable {

    private final String fileName;
    private final ConcurrentHashMap<String, Integer> hasil;
    private final ConcurrentHashMap<String, String> threadMap;
    private final ConcurrentHashMap<String, Long> durasiMap;
    private final CountDownLatch latch;

    public DocumentTask(
            String fileName,
            ConcurrentHashMap<String, Integer> hasil,
            ConcurrentHashMap<String, String> threadMap,
            ConcurrentHashMap<String, Long> durasiMap,
            CountDownLatch latch) {

        this.fileName = fileName;
        this.hasil = hasil;
        this.threadMap = threadMap;
        this.durasiMap = durasiMap;
        this.latch = latch;
    }

    @Override
    public void run() {

        DataProcessor processor = new DataProcessor();

        long start = System.currentTimeMillis();

        try {

            int jumlahKata = processor.process(fileName);

            long end = System.currentTimeMillis();

            long durasi = end - start;

            hasil.put(fileName, jumlahKata);

            threadMap.put(fileName,
                    Thread.currentThread().getName());

            durasiMap.put(fileName, durasi);

            System.out.println("[" +
                    Thread.currentThread().getName() +
                    "] selesai memproses " +
                    fileName +
                    " (" + jumlahKata + " kata)");

        } catch (InterruptedException e) {

            System.out.println(fileName +
                    " gagal diproses.");

        } finally {

            latch.countDown();
        }
    }
}