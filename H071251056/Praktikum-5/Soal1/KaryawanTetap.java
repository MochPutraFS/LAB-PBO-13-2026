package Soal1;

public class KaryawanTetap extends Karyawan {
    private double gajiPokok;
    private double tunjanganMakan;

    public KaryawanTetap(String nama, String idKaryawan, double gajiPokok, double tunjanganMakan) {
        super(nama, idKaryawan);
        this.gajiPokok = gajiPokok;
        this.tunjanganMakan = tunjanganMakan;

    }
    
    @Override
    public double hitungGaji(){
        return gajiPokok + (tunjanganMakan * getJumlahKehadiran());
    }
    public double hitungGaji(double bonusKinerja){
        return hitungGaji() + bonusKinerja;
    }

    public void tampilkanGaji(double bonusKinerja) {
        System.out.println("=== slip gaji karyawan tatap ===");
        System.out.println("nama : " + getNama());
        System.out.println("id :" + getidKaryawan());
        System.out.println("kehadiran :" + getJumlahKehadiran());
        System.out.printf("gaji pokok : Rp%.0f%n", gajiPokok);
        System.out.printf("tunjangan : Rp%.0f x %d hari = Rp%.0fn", tunjanganMakan, getJumlahKehadiran(), tunjanganMakan * getJumlahKehadiran());
        System.err.printf("bonus kinerja : Rp%.0f%n", bonusKinerja );
        System.out.printf("total gaji : Rp%.0f%n", hitungGaji(bonusKinerja));
        System.err.println("==================================");

    }
}
