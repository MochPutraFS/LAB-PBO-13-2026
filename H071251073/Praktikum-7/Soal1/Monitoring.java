public class Monitoring implements Runnable {

    private final Gudang gudang;

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {

        int stokSebelumnya = -1;

        while (!Thread.currentThread().isInterrupted()) {

            try {

                int stok = gudang.getStok();
                int kapasitas = gudang.getKapasitasMaksimal();

                if (stok != stokSebelumnya) {

                    int panjangBar = 20;

                    int terisi =
                            (int) (((double) stok / kapasitas)
                                    * panjangBar);

                    int persen =
                            (int) (((double) stok / kapasitas)
                                    * 100);

                    StringBuilder bar = new StringBuilder();

                    for (int i = 0; i < panjangBar; i++) {

                        if (i < terisi) {
                            bar.append("#");
                        } else {
                            bar.append("-");
                        }
                    }

                    System.out.println(
                            "Status Gudang: ["
                                    + bar
                                    + "] "
                                    + persen + "%"
                    );

                    stokSebelumnya = stok;
                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                System.out.println("Monitoring dihentikan.");

                Thread.currentThread().interrupt();
            }
        }
    }
}