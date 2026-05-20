public class Monitoring implements Runnable {
    private final Gudang gudang;
    private static final int BAR_LENGTH = 20;

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                tampilkanStatus();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Monitoring] Thread monitoring dihentikan.");
            }
        }
    }


    private void tampilkanStatus() {
        int stok         = gudang.getStok();
        int maks         = gudang.getKapasitasMaksimal();
        int persentase   = (maks > 0) ? (int) ((double) stok / maks * 100) : 0;
        int jumlahIsi    = (int) ((double) persentase / 100 * BAR_LENGTH);
        int jumlahKosong = BAR_LENGTH - jumlahIsi;

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < jumlahIsi;    i++) bar.append('#');
        for (int i = 0; i < jumlahKosong; i++) bar.append('-');
        bar.append(']');

        System.out.printf(">>> Status Gudang: %s %3d%% (%d/%d barang)%n",
                bar, persentase, stok, maks);
    }
}
