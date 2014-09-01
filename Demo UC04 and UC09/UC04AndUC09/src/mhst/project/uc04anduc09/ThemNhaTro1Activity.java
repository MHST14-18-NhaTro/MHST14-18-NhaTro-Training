package mhst.project.uc04anduc09;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ThemNhaTro1Activity extends ActionBarActivity {

	private EditText tenChuTro;
	private EditText soDienThoai;
	private EditText email;
	private Bitmap hinhAnhNhaTro;

	// private String tenNhaTro;
	// private String diaChi;
	// private int giaPhong;
	// private int soPhong;
	// private double dienTichPhong;
	// private boolean oGhep;
	// private String thongTinThem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nha_tro1);
		tenChuTro = (EditText) findViewById(R.id.edit_ten_chu_tro);
		soDienThoai = (EditText) findViewById(R.id.edit_so_dien_thoai);
		email = (EditText) findViewById(R.id.edit_email);
		Intent intent = getIntent();
		String temp = intent.getStringExtra(NhaTroDatabaseHelper.colTenChuTro);
		if (temp != null) {
			tenChuTro.setText(temp);
		}
		temp = intent.getStringExtra(NhaTroDatabaseHelper.colSoDienThoai);
		if (temp != null) {
			soDienThoai.setText(temp);
		}
		temp = intent.getStringExtra(NhaTroDatabaseHelper.colEmail);
		if (temp != null) {
			email.setText(temp);
		}
		hinhAnhNhaTro = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_nha_tro1, menu);
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

	public void onHuyBo(View view) {
		finish();
	}

	// Cap nhat intent voi ten chu tro, so dien thoai va email,
	// roi gui lai ThemNhaTroActivity

	public void onQuayLai(View view) {
		Intent intent = getIntent();
		addInputToIntent(intent);
		setResult(ThemNhaTroActivity.CODE_QUAY_LAI, intent);
		finish();
	}

	/**
	 * @param intent
	 */
	private void addInputToIntent(Intent intent) {
		intent.putExtra(NhaTroDatabaseHelper.colTenChuTro, tenChuTro.getText()
				.toString());
		intent.putExtra(NhaTroDatabaseHelper.colSoDienThoai, soDienThoai
				.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colEmail, email.getText()
				.toString());
		if (hinhAnhNhaTro != null) {
			intent.putExtra(NhaTroDatabaseHelper.colHinhAnh, hinhAnhNhaTro);
		}
	}

	public void onTiepTuc(View view) {
		Intent intent = getIntent();
		// Intent intent = new Intent(getApplicationContext(),
		// ThemNhaTro2Activity.class);
		// intent.putExtras(data.getExtras());
		intent.setClass(this, ThemNhaTro2Activity.class);
		addInputToIntent(intent);
		Log.i("fucking info", "after initializing intent");
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case ThemNhaTroActivity.CODE_QUAY_LAI:
			hinhAnhNhaTro = (Bitmap) data.getExtras().get(
					NhaTroDatabaseHelper.colHinhAnh);
			return;
		case ThemNhaTroActivity.CODE_XAC_NHAN:
			setResult(ThemNhaTroActivity.CODE_XAC_NHAN, data);
			finish();
			return;
		default:
			finish();
		}
	}
}
