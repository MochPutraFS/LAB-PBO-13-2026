package Praktikum2;
import java.util.Random;

public class KerangAjaib {
    String warna;
    int tingkatEnergi;
    int tingkatKebijaksanaan;
    Pengguna pemilik; 

    public KerangAjaib() {
        this.warna = "Biasa";
        this.tingkatEnergi = 100;
        this.tingkatKebijaksanaan = 50;
        this.pemilik = new Pengguna(); 
    }

    public KerangAjaib(String warna, int tingkatEnergi, int tingkatKebijaksanaan, Pengguna pemilik) {
        this.warna = warna;
        this.tingkatEnergi = tingkatEnergi;
        this.tingkatKebijaksanaan = tingkatKebijaksanaan;
        this.pemilik = pemilik;
    }

    public void beriJawaban() {
        System.out.println(this.pemilik.nama + " menarik tali Kerang Ajaib " + this.warna + "...");
        
        if (this.tingkatEnergi < 20) {
            System.out.println("Kerang: \"Kerang sedang lelah... coba lagi nanti... zzz...\"");
            this.pemilik.levelKepercayaan -= 15;
            System.out.println("-> " + this.pemilik.nama + " kecewa. Level kepercayaannya turun menjadi " + this.pemilik.levelKepercayaan);
        } else {
            String[] jawabanKhas = {"Ya.", "Tidak.", "Mungkin suatu hari nanti.", "Ikuti kata hatimu."};
            Random rand = new Random();
            String jawaban = jawabanKhas[rand.nextInt(jawabanKhas.length)];
            
            System.out.println("Kerang: \"" + jawaban + "\"");
            this.tingkatEnergi -= 25; 
            
            if (jawaban.equals("Ya.") || jawaban.equals("Ikuti kata hatimu.")) {
                this.pemilik.levelKepercayaan += 10;
                System.out.println("-> " + this.pemilik.nama + " senang! Level kepercayaannya naik.");
            } else {
                this.pemilik.levelKepercayaan -= 5;
                System.out.println("-> " + this.pemilik.nama + " sedikit ragu. Level kepercayaannya turun.");
            }
        }
    }

    public void interaksiDengan(KerangAjaib kerangLain) {
        System.out.println("\n" + this.pemilik.nama + " mendekatkan Kerang " + this.warna + 
                           " dengan Kerang " + kerangLain.warna + " milik " + kerangLain.pemilik.nama + "!");
        System.out.println("WOOSH! Kedua kerang saling bergetar hebat dan kebingungan!");
        
        this.tingkatEnergi -= 40;
        kerangLain.tingkatEnergi -= 40;
        
        System.out.println("-> Terjadi resonansi gaib! Energi kedua kerang terkuras drastis.");
        System.out.println("-> Energi Kerang " + this.warna + " sisa: " + this.tingkatEnergi);
        System.out.println("-> Energi Kerang " + kerangLain.warna + " sisa: " + kerangLain.tingkatEnergi);
    }
    
    public void isiEnergi() {
        this.tingkatEnergi = 100;
        System.out.println("\n[SISTEM] Kerang " + this.warna + " diberi Sarilaut. Energinya pulih penuh!");
    }
}