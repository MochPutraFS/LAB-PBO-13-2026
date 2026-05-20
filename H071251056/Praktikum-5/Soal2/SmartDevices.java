package Soal2;

class SmartLamp extends PerangkatElektronik implements suara  {
    public SmartLamp(String merk, int dayaListrik) {
        super(merk, dayaListrik);
    }
    @Override
    public void cekFungsi(){
        System.out.println("[smartlamp - " + merk + "] fungsi : adalah");

    }
    @Override
    public void prosesPerintah(String perintah) {
        if (perintah.equalsIgnoreCase("NYALA")) {
            System.out.println("[SmartLamp - " + merk + "] lampu berpijar");
        } else if (perintah.equalsIgnoreCase("MATI")) {
            System.out.println("[SmartLamp - " + merk + "] lampu dimatikan.");
        } else {
            System.out.println("[SmartLamp - " + merk + "] perintah tidak dikenali: " + perintah);
        }
    }

}



class SmartCCTV extends PerangkatElektronik implements koneksi {

    public SmartCCTV(String merk, int dayaListrik) {
        super(merk, dayaListrik);
    }

    @Override
    public void cekFungsi() {
        System.out.println("[SmartCCTV - " + merk + "] fungsi: Kamera keamanan dengan streaming internet.");
    }

    // Mengirim rekaman ke server melalui WiFi
    @Override
    public void konek() {
        System.out.println("[SmartCCTV - " + merk + "] terhubung ke wifi. mengirim data ke server...");
    }
}


class SmartSpeaker extends PerangkatElektronik implements koneksi, suara {

    public SmartSpeaker(String merk, int dayaListrik) {
        super(merk, dayaListrik);
    }

    @Override
    public void cekFungsi() {
        System.out.println("[SmartSpeaker - " + merk + "] Fungsi: peaker pintar dengan wifi dan kontrol suara.");
    }

    @Override
    public void konek() {
        System.out.println("[SmartSpeaker - " + merk + "] terhubung ke wifi. mengunduh playlist terbaru...");
    }

    @Override
    public void prosesPerintah(String perintah) {
        System.out.println("[SmartSpeaker - " + merk + "] memproses perintah: \"" + perintah + "\"");
        if (perintah.equalsIgnoreCase("PLAY MUSIK")) {
            System.out.println("[SmartSpeaker - " + merk + "] memutar musik...");
        } else {
            System.out.println("[SmartSpeaker - " + merk + "] menjalankan: " + perintah);
        }
    }
}

