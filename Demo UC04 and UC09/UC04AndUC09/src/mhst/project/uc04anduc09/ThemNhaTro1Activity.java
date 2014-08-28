package mhst.project.uc04anduc09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ThemNhaTro1Activity extends ActionBarActivity {

	private EditText tenChuTro;
	private EditText soDienThoai;
	private EditText email;
//	private String tenNhaTro;
//	private String diaChi;
//	private int giaPhong;
//	private int soPhong;
//	private double dienTichPhong;
//	private boolean oGhep;
//	private String thongTinThem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nha_tro1);
		tenChuTro = (EditText)findViewById(R.id.edit_ten_chu_tro);
		soDienThoai = (EditText)findViewById(R.id.edit_so_dien_thoai);
		email = (EditText)findViewById(R.id.edit_email);
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
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onHuyBo(View view) {
		finish();
	}
	
	public void onQuayLai(View view) {
		Intent intent = getIntent();
		intent.putExtra(NhaTroDatabaseHelper.colTenChuTro, tenChuTro.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colSoDienThoai, soDienThoai.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colEmail, email.getText().toString());
		setResult(ThemNhaTroActivity.CODE_QUAY_LAI, intent);
		finish();
	}
	
	public void onXacNhan(View view) {
		Intent intent = getIntent();
		intent.putExtra(NhaTroDatabaseHelper.colTenChuTro, tenChuTro.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colSoDienThoai, soDienThoai.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colEmail, email.getText().toString());
		setResult(ThemNhaTroActivity.CODE_XAC_NHAN, intent);
		finish();
	}
}
