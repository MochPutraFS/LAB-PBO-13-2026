package Praktikum2;

public class Pengguna {
    String nama;
    int keberuntungan;
    int levelKepercayaan;
    
    public Pengguna() {
        this.nama = "NPC Misterius";
        this.keberuntungan = 50;
        this.levelKepercayaan = 50;
    }
    public Pengguna(String nama, int keberuntungan, int levelKepercayaan) {
        this.nama = nama;
        this.keberuntungan = keberuntungan;
        this.levelKepercayaan = levelKepercayaan;
    }
}