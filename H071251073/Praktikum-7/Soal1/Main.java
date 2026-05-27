import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Gudang gudang = new Gudang(20);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(new Pemasok(gudang, "Pemasok-1"));
        executor.execute(new Pemasok(gudang, "Pemasok-2"));

        executor.execute(new Kurir(gudang, "Kurir-1"));
        executor.execute(new Kurir(gudang, "Kurir-2"));
        executor.execute(new Kurir(gudang, "Kurir-3"));

        Thread monitoring = new Thread(new Monitoring(gudang));
        monitoring.start();

        try {

            Thread.sleep(15000);

            executor.shutdownNow();

            monitoring.interrupt();

            executor.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("\nSistem gudang dihentikan.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}