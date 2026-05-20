package Soal1;
public class Main {
    public static void main(String[] args) {
        System.out.println("====== Sistem Gaji ======");

        // karyawan tetap
        KaryawanTetap kt = new KaryawanTetap("meymey", "KT-001", 5_000_000, 50_000);
        System.out.println(">>>[proses absen karyawan tetap (15 hari)]<<<");
        for (int i = 0; i < 15; i++){
            kt.absen();
        }

        System.out.println();
        kt.tampilkanGaji(1_000_000);
        System.out.println();

        //karyawan kontrak
        KaryawanKontrak kk = new KaryawanKontrak("dwidwi", "KK-001", 200_000);
        System.out.println(">>>[proses absen karyawan kontrak (22 hari)");
        for (int i = 0; i < 22; i++){
            kk.absen();
        }

        System.out.println();
        kk.tampilkanGaji();
    }
}