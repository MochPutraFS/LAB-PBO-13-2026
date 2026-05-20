import java.util.Random;

public class DataProcessor {

    private static final Random random = new Random();

    public int process(String fileName) throws InterruptedException {
        int delay = 500 + random.nextInt(1501);
        Thread.sleep(delay);
        return 50 + random.nextInt(451);
    }
}
