package mhst.project.uc04anduc09;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DanhSachNhaTroAdapter extends CursorAdapter {

	@SuppressWarnings("deprecation")
	public DanhSachNhaTroAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if (cursor.getPosition() % 2 == 1) {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_odd));
		} else {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_even));
		}
		TextView tenNhaTro = (TextView)view.findViewById(R.id.textview_ten_nha_tro);
		TextView diaChi = (TextView)view.findViewById(R.id.textview_dia_chi);
		TextView giaPhong = (TextView)view.findViewById(R.id.textview_gia_phong);
		TextView soPhong = (TextView)view.findViewById(R.id.textview_so_phong);
		tenNhaTro.setText(
				cursor.getString(cursor.getColumnIndex(NhaTroDatabaseHelper.colTenNhaTro)));
		diaChi.setText(
				cursor.getString(cursor.getColumnIndex(NhaTroDatabaseHelper.colDiaChi)));
		giaPhong.setText(
				cursor.getInt(cursor.getColumnIndex(NhaTroDatabaseHelper.colGiaPhong)) 
				+ " VND / ph�ng");
		soPhong.setText(
				cursor.getInt(cursor.getColumnIndex(NhaTroDatabaseHelper.colSoPhong)) 
				+ " ph�ng");
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater layoutInflater = ((DanhSachNhaTroActivity)context).getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.itemview_nha_tro, parent, false);
		return view;
	}

}
