public class DompetDigital {

    private int saldo;
    private String pin;

    protected String idNasabah;
    String mataUang;

    public DompetDigital (String inputId, String inputPin) {
        idNasabah = inputId;
        pin = inputPin;
        saldo = 0;
        mataUang = "IDR";

    }

    public String getIdNasabah() {
        return idNasabah;

    }

    public int getSaldo() {
        return saldo;

    }

    public void ubahPin(String inputPinLama, String inputPinBaru) {
        if (inputPinLama.equals(pin) && inputPinBaru.length() == 6){
            pin = inputPinBaru;
            Log("pin berhasil diubah");
        } else {
            Log("gagal! pin lama salah atau pin baru tidak 6 karakter.");
        }
    }

    public void setorTunai (int jumlahSetor) {
        if (jumlahSetor > 0) {
            saldo += jumlahSetor;
            Log("setor tunai berhasil, saldo sekarang " + saldo);
        } else {Log("gagal! nomial setor tidak valid ");}
    }

    public void tarikTunai (String inputPin, int jumlahTarik) {
        if (inputPin.equals(pin) && saldo >= jumlahTarik) {
            saldo -= jumlahTarik;
            Log("tarik tunai berhasil!, sisa saldo" + saldo);
        } else {
            Log("gagal, pin salah atau saldo tidak cukup");
        }
    }

    private void Log (String pesan) {
        System.out.println("[LOG]" + pesan);
    }
}