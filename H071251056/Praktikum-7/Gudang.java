public class Gudang {

    private int stok;
    private final int kapasitasMaksimal;

    public Gudang(int kapasitasMaksimal) {
        this.stok = 0;
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    public synchronized void tambahStok(int jumlah) throws InterruptedException {
        String nama = Thread.currentThread().getName();
        while (stok + jumlah > kapasitasMaksimal) {
            System.out.printf("[%s] Gudang penuh! Stok: %d/%d. Menunggu ruang...%n",
                    nama, stok, kapasitasMaksimal);
            wait();
        }
        stok += jumlah;
        System.out.printf("[%s] (+) Menambah %d barang  -->  Stok: %d/%d%n",
                nama, jumlah, stok, kapasitasMaksimal);
        notifyAll(); 
    }

    public synchronized void ambilStok(int jumlah) throws InterruptedException {
        String nama = Thread.currentThread().getName();
        while (stok < jumlah) {
            System.out.printf("[%s] Stok kurang! Butuh %d, tersedia %d. Menunggu...%n",
                    nama, jumlah, stok);
            wait();
        }
        stok -= jumlah;
        System.out.printf("[%s] (-) Mengambil %d barang  -->  Stok: %d/%d%n",
                nama, jumlah, stok, kapasitasMaksimal);
        notifyAll(); 
    }
    public synchronized int getStok() {
        return stok;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }
}
