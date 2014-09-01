package mhst.project.uc04anduc09;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DanhSachNhaTroActivity extends ActionBarActivity {

	private ListView lvDanhSachNhaTro;
	private NhaTroDatabaseHelper dbHelper;
	private DanhSachNhaTroAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_nha_tro);
		lvDanhSachNhaTro = (ListView) findViewById(R.id.listview_danh_sach_nha_tro);
		dbHelper = new NhaTroDatabaseHelper(this, null, null, 0);
		// dbHelper.themMotSoNhaTro();
		Cursor cursor = dbHelper.getTatCaNhaTro();
		listAdapter = new DanhSachNhaTroAdapter(this, cursor);
		lvDanhSachNhaTro.setAdapter(listAdapter);
		lvDanhSachNhaTro.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Cursor c = (Cursor) lvDanhSachNhaTro
						.getItemAtPosition(position);
				Intent intent = new Intent(DanhSachNhaTroActivity.this,
						ThongTinNhaTroActivity.class);
				intent.putExtra(
						NhaTroDatabaseHelper.colTenNhaTro,
						c.getString(c
								.getColumnIndex(NhaTroDatabaseHelper.colTenNhaTro)));
				intent.putExtra(NhaTroDatabaseHelper.colDiaChi, c.getString(c
						.getColumnIndex(NhaTroDatabaseHelper.colDiaChi)));
				intent.putExtra(
						NhaTroDatabaseHelper.colGiaPhong,
						String.valueOf(c.getInt(c
								.getColumnIndex(NhaTroDatabaseHelper.colGiaPhong))));
				intent.putExtra(
						NhaTroDatabaseHelper.colSoPhong,
						String.valueOf(c.getInt(c
								.getColumnIndex(NhaTroDatabaseHelper.colSoPhong))));
				intent.putExtra(
						NhaTroDatabaseHelper.colDienTichPhong,
						String.valueOf(c.getDouble(c
								.getColumnIndex(NhaTroDatabaseHelper.colDienTichPhong))));
				if (c.getInt(c.getColumnIndex(NhaTroDatabaseHelper.colOGhep)) == 0) {
					intent.putExtra(NhaTroDatabaseHelper.colOGhep, "Không");
				} else {
					intent.putExtra(NhaTroDatabaseHelper.colOGhep, "Có");
				}
				intent.putExtra(
						NhaTroDatabaseHelper.colThongTinThem,
						c.getString(c
								.getColumnIndex(NhaTroDatabaseHelper.colThongTinThem)));
				intent.putExtra(
						NhaTroDatabaseHelper.colHinhAnh,
						getBitmapFromBlob(c.getBlob(c
								.getColumnIndex(NhaTroDatabaseHelper.colHinhAnh))));
				intent.putExtra(
						NhaTroDatabaseHelper.colTenChuTro,
						c.getString(c
								.getColumnIndex(NhaTroDatabaseHelper.colTenChuTro)));
				intent.putExtra(
						NhaTroDatabaseHelper.colSoDienThoai,
						c.getString(c
								.getColumnIndex(NhaTroDatabaseHelper.colSoDienThoai)));
				intent.putExtra(NhaTroDatabaseHelper.colEmail, c.getString(c
						.getColumnIndex(NhaTroDatabaseHelper.colEmail)));
				startActivity(intent);
			}
		});
	}

	private Bitmap getBitmapFromBlob(byte[] blob) {
		return BitmapFactory.decodeByteArray(blob, 0, blob.length);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.them:
			themMotNhaTro();
			return true;
		case R.id.sap_xep:
			sapXepDanhSach();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void themMotNhaTro() {
		Intent intent = new Intent(this, ThemNhaTroActivity.class);
		startActivityForResult(intent, ThemNhaTroActivity.CODE_XAC_NHAN);
	}

	public void sapXepDanhSach() {
		final String[] items = { NhaTroDatabaseHelper.colTenNhaTro,
				NhaTroDatabaseHelper.colGiaPhong,
				NhaTroDatabaseHelper.colSoPhong };
		final String[] itemsShow = { "Tên nhà trọ", "Giá phòng", "Số phòng" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Sắp xếp theo");
		builder.setItems(itemsShow, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				listAdapter.changeCursor(dbHelper.sapXepNhaTro(items[which]));
				listAdapter.notifyDataSetChanged();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (resultCode == ThemNhaTroActivity.CODE_XAC_NHAN) {
			NhaTro nhaTro = new NhaTro();
			nhaTro.setTenNhaTro(intent
					.getStringExtra(NhaTroDatabaseHelper.colTenNhaTro));
			nhaTro.setDiaChi(intent
					.getStringExtra(NhaTroDatabaseHelper.colDiaChi));
			nhaTro.setGiaPhong(Integer.parseInt(intent
					.getStringExtra(NhaTroDatabaseHelper.colGiaPhong)));
			nhaTro.setSoPhong(Integer.parseInt(intent
					.getStringExtra(NhaTroDatabaseHelper.colSoPhong)));
			nhaTro.setDienTichPhong(Double.parseDouble(intent
					.getStringExtra(NhaTroDatabaseHelper.colDienTichPhong)));
			nhaTro.setoGhep(intent.getBooleanExtra(
					NhaTroDatabaseHelper.colOGhep, false));
			nhaTro.setThongTinThem(intent
					.getStringExtra(NhaTroDatabaseHelper.colThongTinThem));
			nhaTro.setTenChuTro(intent
					.getStringExtra(NhaTroDatabaseHelper.colTenChuTro));
			nhaTro.setSoDienThoai(intent
					.getStringExtra(NhaTroDatabaseHelper.colSoDienThoai));
			nhaTro.setEmail(intent
					.getStringExtra(NhaTroDatabaseHelper.colEmail));
			nhaTro.setHinhAnh((Bitmap) intent.getExtras().get(
					NhaTroDatabaseHelper.colHinhAnh));
			dbHelper.themNhaTro(nhaTro);
			listAdapter.changeCursor(dbHelper.getTatCaNhaTro());
			listAdapter.notifyDataSetChanged();
		}
	}
}
