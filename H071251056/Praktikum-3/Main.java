public class Main {
    public static void main(String[] args) {
        System.out.println("===== SISTEM E-WALLET =====");
        
        DompetDigital dompetku = new DompetDigital("kalengSardenNo1", "123456");

        System.out.println("identitas akun: " + dompetku.getIdNasabah());

        System.out.println("\n ===== uji fungsi pin =====");
        dompetku.ubahPin("111111", "654321");
        dompetku.ubahPin("123456", "654321" );

        System.out.println("\n ===== uji fungsi transaksi ======");
        dompetku.setorTunai(50000);
        dompetku.setorTunai(-10000);
        dompetku.tarikTunai("123456", 25000);
        dompetku.tarikTunai("654321", 25000);

        System.out.println("\n === cek saldo akhir");
        System.out.println("sisa saldo : " + dompetku.getSaldo());


    }
 
}
