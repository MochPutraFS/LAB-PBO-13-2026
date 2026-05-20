package Soal1;
	public abstract class Karyawan {
	private String nama;
	private String idKaryawan;
	private int jumlahKehadiran;

	
	public Karyawan (String nama, String idKaryawan) {
		this.nama = nama;
		this.idKaryawan = idKaryawan;

	}


	public void absen() {
		jumlahKehadiran++;
		System.out.println(nama + "telah absen. total kehadiran" + jumlahKehadiran + "hari");
	} 

	public abstract double hitungGaji ();
	public String getNama(){return nama;}
	public String getidKaryawan() {return idKaryawan;}
	public int getJumlahKehadiran() {return jumlahKehadiran;}
}