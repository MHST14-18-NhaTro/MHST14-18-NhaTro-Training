package mhst.project.uc04anduc09;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ThongTinNhaTroActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_nha_tro);
		Intent intent = getIntent();
		TextView nhaTro = (TextView) findViewById(R.id.tv_nha_tro);
		TextView diaChi = (TextView) findViewById(R.id.tv_dia_chi);
		TextView giaPhong = (TextView) findViewById(R.id.tv_gia_phong);
		TextView soPhong = (TextView) findViewById(R.id.tv_so_phong);
		TextView dienTichPhong = (TextView) findViewById(R.id.tv_dien_tich_phong);
		TextView oGhep = (TextView) findViewById(R.id.tv_o_ghep);
		TextView thongTinThem = (TextView) findViewById(R.id.tv_thong_tin_them);
		TextView tenChuTro = (TextView) findViewById(R.id.tv_ten_chu_tro);
		TextView soDienThoai = (TextView) findViewById(R.id.tv_so_dien_thoai);
		TextView email = (TextView) findViewById(R.id.tv_email);
		ImageView hinhAnhNhaTro = (ImageView) findViewById(R.id.activity_thong_tin_nha_tro_iv_hinh_anh_nha_tro);
		nhaTro.setText(intent.getStringExtra(NhaTroDatabaseHelper.colTenNhaTro));
		diaChi.setText(intent.getStringExtra(NhaTroDatabaseHelper.colDiaChi));
		giaPhong.setText(intent
				.getStringExtra(NhaTroDatabaseHelper.colGiaPhong));
		soPhong.setText(intent.getStringExtra(NhaTroDatabaseHelper.colSoPhong));
		dienTichPhong.setText(intent
				.getStringExtra(NhaTroDatabaseHelper.colDienTichPhong));
		oGhep.setText(intent.getStringExtra(NhaTroDatabaseHelper.colOGhep));
		thongTinThem.setText(intent
				.getStringExtra(NhaTroDatabaseHelper.colThongTinThem));
		tenChuTro.setText(intent
				.getStringExtra(NhaTroDatabaseHelper.colTenChuTro));
		soDienThoai.setText(intent
				.getStringExtra(NhaTroDatabaseHelper.colSoDienThoai));
		email.setText(intent.getStringExtra(NhaTroDatabaseHelper.colEmail));
		hinhAnhNhaTro.setImageBitmap((Bitmap) intent.getExtras().get(
				NhaTroDatabaseHelper.colHinhAnh));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_tin_nha_tro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}
}
