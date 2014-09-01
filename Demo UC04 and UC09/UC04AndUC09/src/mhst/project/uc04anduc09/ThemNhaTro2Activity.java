package mhst.project.uc04anduc09;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ThemNhaTro2Activity extends ActionBarActivity {

	private ImageView ivHinhAnhNhaTro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nha_tro2);
		ivHinhAnhNhaTro = (ImageView) findViewById(R.id.activity_them_nha_tro2_iv_hinh_anh_nha_tro);
		Intent intent = getIntent();
		Bitmap temp = (Bitmap) intent.getExtras().get(
				NhaTroDatabaseHelper.colHinhAnh);
		if (temp != null) {
			ivHinhAnhNhaTro.setImageBitmap(temp);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_nha_tro2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onHuyBo(View view) {
		finish();
	}

	public void onXacNhan(View view) {
		Intent intent = getIntent();
		addDataToIntent(intent);
		setResult(ThemNhaTroActivity.CODE_XAC_NHAN, intent);
		finish();
	}

	public void onQuayLai(View view) {
		Intent intent = getIntent();
		addDataToIntent(intent);
		setResult(ThemNhaTroActivity.CODE_QUAY_LAI, intent);
		finish();
	}

	/**
	 * @param intent
	 */
	private void addDataToIntent(Intent intent) {
		BitmapDrawable bmDrawable = (BitmapDrawable) ivHinhAnhNhaTro
				.getDrawable();
		if (bmDrawable != null) {
			Bitmap bm = bmDrawable.getBitmap();
			if (bm != null) {
				intent.putExtra(NhaTroDatabaseHelper.colHinhAnh, bm);
			}
		}
	}

	public void onDangHinhAnh(View view) {
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
	}

	public void onLayTuGallery(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setType("image/*");
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				1);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Log.i("dkm", "result ok");
			if (requestCode == 0) {
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				ivHinhAnhNhaTro.setImageBitmap(bm);
			} else {
				if (requestCode == 1) {
					Uri selectedImage = data.getData();
					if (selectedImage != null) {
						try {
							String[] filePathColumn = { MediaStore.Images.Media.DATA };
							Cursor cursor = managedQuery(selectedImage,
									filePathColumn, null, null, null);
							cursor.moveToFirst();
							int columnIndex = cursor
									.getColumnIndexOrThrow(filePathColumn[0]);
							String picturePath = cursor.getString(columnIndex);
							cursor.close();
							ivHinhAnhNhaTro.setImageBitmap(BitmapFactory
									.decodeFile(picturePath,
											new BitmapFactory.Options()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}
}
