package mhst.project.uc04plusuc09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class b4 extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b3_nhap);
		final Button next3Button = (Button) findViewById(R.id.btn_next3);
		next3Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(b4.this, b5.class);
				startActivity(i);
				//finish();

			}
		});
		final Button back3Button = (Button) findViewById(R.id.btn_back3);
		back3Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(b4.this, b3.class);
				startActivity(i);
				//finish();

			}
		});
		
		
	}
}
