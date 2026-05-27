import java.util.Random;

public class DataProcessor {
    private static final Random random = new Random();

    /**
      @param fileName 
      @return
    */
    public int process(String fileName) throws InterruptedException {
        // Simulasi waktu baca file: 500ms - 2000ms
        int delay = 500 + random.nextInt(1500);
        Thread.sleep(delay);

        // Simulasi jumlah kata: 50 - 500 kata
        return 50 + random.nextInt(451);
    }
}
