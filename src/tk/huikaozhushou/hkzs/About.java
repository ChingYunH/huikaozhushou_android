package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		TextView support = null;
		support = (TextView) findViewById(R.id.a_Support);
		support.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);  //Add Underline Style
		support.setOnClickListener(new ButtonSupport_Click());	
	}
	
	
	private void showDialog() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.support);
	}	
	
	class ButtonSupport_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog();
		}

	}
}