package Praktikum2;

public class Main {
    public static void main(String[] args) {
        Pengguna spongebob = new Pengguna("SpongeBob", 99, 100);
        Pengguna nama = new Pengguna();

        KerangAjaib kerangOri = new KerangAjaib("Ungu Garis", 60, 99, spongebob);
        KerangAjaib KerangKW = new KerangAjaib();

        kerangOri.beriJawaban();
        pembatas();
        
        kerangOri.beriJawaban();
        pembatas();
        kerangOri.beriJawaban(); 
        pembatas();

        kerangOri.isiEnergi();
        pembatas();

        kerangOri.interaksiDengan(KerangKW);
        pembatas();
    }

    public static void pembatas() {
        System.out.println("======================================================");
    }
}