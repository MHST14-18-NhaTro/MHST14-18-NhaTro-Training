package mhst.project.uc04plusuc09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class b2 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b2_nhap);
		final Button next1Button = (Button) findViewById(R.id.btn_next2);
		next1Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(b2.this, b3.class);
				startActivity(i);
				finish();

			}
		});
	}
}
