package mhst.project.uc04anduc09;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NhaTroDatabaseHelper extends SQLiteOpenHelper {

	static final String dbNhaTro = "nhaTroDB";
	static final String tableNhaTro = "nhaTro";
	
	static final String colID = "_id";
	static final String colTenNhaTro = "tenNhaTro";
	static final String colDiaChi = "diaChi";
	static final String colGiaPhong = "giaPhong";
	static final String colSoPhong = "soPhong";
	static final String colDienTichPhong = "dienTichPhong";
	static final String colOGhep = "oGhep";
	static final String colThongTinThem = "thongTinThem";
	static final String colHinhAnh = "hinhAnh";
	
	static final String colTenChuTro = "tenChuTro";
	static final String colSoDienThoai = "soDienThoai";
	static final String colEmail = "email";
	
	private static final int dbVersion = 2;
	
	private final String createTable = "create table if not exists " + tableNhaTro + " ("
			+ colID + " integer not null, "
			+ colTenNhaTro + " text not null, "
			+ colDiaChi + " text not null, "
			+ colGiaPhong + " integer not null, "
			+ colSoPhong + " integer not null, "
			+ colDienTichPhong + " double, "
			+ colOGhep + " tinyint, "
			+ colThongTinThem + " text, "
			+ colHinhAnh + " blob, "
			+ colTenChuTro + " text not null, "
			+ colSoDienThoai + " text not null, "
			+ colEmail + " text, "
			+ "primary key(" + colID + ")"
			+ ");";
	
	public NhaTroDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, dbNhaTro, factory, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + tableNhaTro);
		onCreate(db);
	}

	public Cursor getTatCaNhaTro() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + tableNhaTro, null);
		return cursor;
	}
	
	public void themMotSoNhaTro() {
		NhaTro nhaTro1 = new NhaTro("nhà trọ 1", "địa chỉ 1", 1, 1, 1, false, 
				"đây là nhà trọ demo 1", "chủ trọ 1", "111", "email@1");
		themNhaTro(nhaTro1);
		NhaTro nhaTro2 = new NhaTro("nhà trọ 2", "địa chỉ 2", 2, 2, 2, true, 
				"đây là nhà trọ demo 2", "chủ trọ 2", "222", "email@2");
		themNhaTro(nhaTro2);
	}
	
	public void themNhaTro(NhaTro nhaTro) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value = new ContentValues();
		value.put(colTenNhaTro, nhaTro.getTenNhaTro());
		value.put(colDiaChi, nhaTro.getDiaChi());
		value.put(colGiaPhong, nhaTro.getGiaPhong());
		value.put(colSoPhong, nhaTro.getSoPhong());
		value.put(colDienTichPhong, nhaTro.getDienTichPhong());
		if (nhaTro.isoGhep()) {
			value.put(colOGhep, 0);
		} else {
			value.put(colOGhep, 1);
		}
		value.put(colThongTinThem, nhaTro.getThongTinThem());
		value.put(colTenChuTro, nhaTro.getTenChuTro());
		value.put(colSoDienThoai, nhaTro.getSoDienThoai());
		value.put(colEmail, nhaTro.getEmail());
		db.insert(tableNhaTro, null, value);
	}
	
	public Cursor sapXepNhaTro(String tieuChi) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.rawQuery("select * from " + tableNhaTro + " order by " + tieuChi, null);
	}
}
