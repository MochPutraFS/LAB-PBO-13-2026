package Soal1;

public class KaryawanKontrak extends Karyawan {
    private double upahPerHari;
    private static final double BONUS_KEHADIRAN = 500_000;

    public KaryawanKontrak (String nama, String idKaryawan, double upahPerHari) {
        super(nama, idKaryawan);
        this.upahPerHari = upahPerHari;

    }

    @Override
    public double hitungGaji() {
        double totalGaji = upahPerHari * getJumlahKehadiran();
        if (getJumlahKehadiran() > 20) {
            totalGaji += BONUS_KEHADIRAN;
        }
        return totalGaji;

    }   

    public void tampilkanGaji() {
        boolean dapatBonus = getJumlahKehadiran() > 20;
        System.out.println("-----------------------slip gaji---------------------");
        System.out.println("nama : " + getNama());
        System.out.println("id : " + getidKaryawan());
        System.out.println("kehadiran :" + getJumlahKehadiran());
        System.out.printf("upah/hari : Rp%.0f%n", upahPerHari);
        System.out.printf("total upah : Rp%.0f x %d = Rp%.0f%n", 
            upahPerHari, getJumlahKehadiran(), upahPerHari * getJumlahKehadiran());

            if (dapatBonus) {
                System.out.printf("bonus (>20hari) : Rp%.0f%n", BONUS_KEHADIRAN);
            }
            System.out.printf("total gaji : Rp%.0f%n", hitungGaji());
            System.out.println("-----------------------------------------------");

    }
    
}
