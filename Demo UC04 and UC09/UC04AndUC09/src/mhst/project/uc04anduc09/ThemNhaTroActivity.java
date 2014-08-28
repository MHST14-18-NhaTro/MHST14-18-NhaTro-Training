package mhst.project.uc04anduc09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ThemNhaTroActivity extends ActionBarActivity {

	public static final int CODE_QUAY_LAI = 2;
	public static final int CODE_XAC_NHAN = 1;
	private EditText tenNhaTro;
	private EditText diaChi;
	private EditText giaPhong;
	private EditText soPhong;
	private EditText dienTichPhong;
	private CheckBox oGhep;
	private EditText thongTinThem;
	private String tenChuTro;
	private String soDienThoai;
	private String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nha_tro);
		tenNhaTro = (EditText)findViewById(R.id.edit_ten_nha_tro);
		diaChi = (EditText)findViewById(R.id.edit_dia_chi);
		giaPhong = (EditText)findViewById(R.id.edit_gia_phong);
		soPhong = (EditText)findViewById(R.id.edit_so_phong);
		dienTichPhong = (EditText)findViewById(R.id.edit_dien_tich_phong);
		oGhep = (CheckBox)findViewById(R.id.check_o_ghep);
		thongTinThem = (EditText)findViewById(R.id.edit_thong_tin_them);
		tenChuTro = "";
		soDienThoai = "";
		email = "";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_nha_tro, menu);
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
	
	public void onTiepTuc(View view) {
		Intent intent = new Intent(this, ThemNhaTro1Activity.class);
		intent.putExtra(NhaTroDatabaseHelper.colTenNhaTro, tenNhaTro.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colDiaChi, diaChi.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colGiaPhong, giaPhong.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colSoPhong, soPhong.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colDienTichPhong, dienTichPhong.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colOGhep, oGhep.isChecked());
		intent.putExtra(NhaTroDatabaseHelper.colThongTinThem, thongTinThem.getText().toString());
		intent.putExtra(NhaTroDatabaseHelper.colTenChuTro, tenChuTro);
		intent.putExtra(NhaTroDatabaseHelper.colSoDienThoai, soDienThoai);
		intent.putExtra(NhaTroDatabaseHelper.colEmail, email);
		startActivityForResult(intent, CODE_QUAY_LAI);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case CODE_QUAY_LAI:
			tenChuTro = data.getStringExtra(NhaTroDatabaseHelper.colTenChuTro);
			soDienThoai = data.getStringExtra(NhaTroDatabaseHelper.colSoDienThoai);
			email = data.getStringExtra(NhaTroDatabaseHelper.colEmail);
			return;
		case CODE_XAC_NHAN:
			setResult(resultCode, data);
			finish();
			return;
		default:
			finish();
		}
	}
}
