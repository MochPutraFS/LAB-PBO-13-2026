package Soal2;

public class Main {
    public static void main(String[] args) {
        System.out.println("===soal 2====");

        System.out.println("==smartlamp==");
        SmartLamp lamp = new SmartLamp("philips", 10);
        lamp.cekFungsi();
        lamp.infoPower();
        lamp.prosesPerintah("NYALA");
        lamp.prosesPerintah("MATI");
        System.out.println();


        System.out.println("==CCTV==");
        SmartCCTV cctv = new SmartCCTV("adalah", 20);
        cctv.cekFungsi();
        cctv.infoPower();
        cctv.konek();
        System.out.println();


        System.out.println("==spkip==");
        SmartSpeaker speaker = new SmartSpeaker("adalah1", 10);
        speaker.cekFungsi();
        speaker.infoPower();
        speaker.konek();
        speaker.prosesPerintah("PLAY MUSIK");
        System.out.println();

        System.out.println("-----");
        koneksi wifi = new SmartSpeaker("entahlah", 20);
        wifi.konek();
        suara voice = new SmartLamp("entah11", 10);
        voice.prosesPerintah("NYALA");
    }
    
}
