// =============================================================
// CLASS MONITORING - Observer Thread
// Menampilkan status gudang secara visual setiap 1 detik
// =============================================================
public class Monitoring implements Runnable {
    private final Gudang gudang;
    private volatile boolean berjalan = true;

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (berjalan && !Thread.currentThread().isInterrupted()) {
            try {
                tampilkanStatus();
                Thread.sleep(1000); // Tampilkan setiap 1 detik
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void tampilkanStatus() {
        int stok = gudang.getStok();
        int maks = gudang.getKapasitasMaksimal();
        int persen = (int) ((double) stok / maks * 100);

        // Buat visual bar: total 20 karakter
        int isi = (int) ((double) stok / maks * 20);
        String bar = "#".repeat(isi) + "-".repeat(20 - isi);

        System.out.println("Status Gudang: [" + bar + "] " + persen + "% (" + stok + "/" + maks + ")");
    }

    public void stop() {
        berjalan = false;
    }
}
