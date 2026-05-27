public class Gudang {
    private int stok;
    private final int kapasitasMaksimal;

    public Gudang(int kapasitasMaksimal) {
        this.stok = 0;
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    // Method untuk menambah stok (dipanggil oleh Pemasok)
    public synchronized void tambahStok(int jumlah) throws InterruptedException {
        // Selama gudang penuh, thread Pemasok harus menunggu
        while (stok + jumlah > kapasitasMaksimal) {
            System.out.println("[PEMASOK] Gudang hampir penuh (stok=" + stok + "). Menunggu...");
            wait(); // Thread melepas lock dan menunggu notifikasi
        }
        stok += jumlah;
        System.out.println("[PEMASOK] Menambah " + jumlah + " barang. Stok sekarang: " + stok);
        notifyAll(); // Beritahu semua thread yang sedang menunggu (termasuk Kurir)
    }

    // Method untuk mengambil stok (dipanggil oleh Kurir)
    public synchronized void ambilStok(int jumlah) throws InterruptedException {
        // Selama stok tidak mencukupi, thread Kurir harus menunggu
        while (stok < jumlah) {
            System.out.println("[KURIR] Stok tidak cukup (stok=" + stok + ", butuh=" + jumlah + "). Menunggu...");
            wait(); // Thread melepas lock dan menunggu notifikasi
        }
        stok -= jumlah;
        System.out.println("[KURIR] Mengambil " + jumlah + " barang. Stok sekarang: " + stok);
        notifyAll(); // Beritahu semua thread yang sedang menunggu (termasuk Pemasok)
    }

    // Getter untuk diakses oleh Monitoring tanpa mengubah nilai
    public synchronized int getStok() {
        return stok;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }
}
