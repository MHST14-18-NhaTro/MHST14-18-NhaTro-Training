package mhst.project.uc04anduc09;

import android.graphics.Bitmap;

public class NhaTro {

	// Thông tin của nhà trọ
	private String tenNhaTro;
	private String diaChi;
	private int giaPhong;
	private int soPhong;
	private double dienTichPhong;
	private boolean oGhep;
	private String thongTinThem;
	private Bitmap hinhAnh;

	// Thông tin của chủ nhà trọ
	private String tenChuTro;

	public Bitmap getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(Bitmap hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	private String soDienThoai;
	private String email;

	public NhaTro() {
		tenNhaTro = "";
		diaChi = "";
		giaPhong = 0;
		soPhong = 0;
		dienTichPhong = 0;
		oGhep = false;
		thongTinThem = "";
		tenChuTro = "";
		soDienThoai = "";
		email = "";
		hinhAnh = null;
	}

	public NhaTro(String tenNhaTro, String diaChi, int giaPhong, int soPhong,
			double dienTichPhong, boolean oGhep, String thongTinThem,
			Bitmap hinhAnh, String tenChuTro, String soDienThoai, String email) {
		this.tenNhaTro = tenNhaTro;
		this.diaChi = diaChi;
		this.giaPhong = giaPhong;
		this.soPhong = soPhong;
		this.dienTichPhong = dienTichPhong;
		this.oGhep = oGhep;
		this.thongTinThem = thongTinThem;
		this.hinhAnh = hinhAnh;
		this.tenChuTro = tenChuTro;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}

	public String getTenNhaTro() {
		return tenNhaTro;
	}

	public void setTenNhaTro(String tenNhaTro) {
		this.tenNhaTro = tenNhaTro;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(int giaPhong) {
		this.giaPhong = giaPhong;
	}

	public int getSoPhong() {
		return soPhong;
	}

	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}

	public double getDienTichPhong() {
		return dienTichPhong;
	}

	public void setDienTichPhong(double dienTichPhong) {
		this.dienTichPhong = dienTichPhong;
	}

	public boolean isoGhep() {
		return oGhep;
	}

	public void setoGhep(boolean oGhep) {
		this.oGhep = oGhep;
	}

	public String getThongTinThem() {
		return thongTinThem;
	}

	public void setThongTinThem(String thongTinThem) {
		this.thongTinThem = thongTinThem;
	}

	public String getTenChuTro() {
		return tenChuTro;
	}

	public void setTenChuTro(String tenChuTro) {
		this.tenChuTro = tenChuTro;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
