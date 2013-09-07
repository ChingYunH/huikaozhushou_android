package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity {
	TextView rCorrectRatio = null , rWrongResult = null, rWrongTip = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		getLink();
		transferData();
	}

	private void transferData() {
		// TODO Auto-generated method stub
		String correctR = null;
		String wrongT = null;
		Intent intent = getIntent();
		correctR = rCorrectRatio.getText().toString();
		correctR = correctR + intent.getIntExtra("correctNum", -1) + "/" + intent.getIntExtra("totalNum", -1);
		rCorrectRatio.setText(correctR);
		wrongT = intent.getStringExtra("wrongResult");
		rWrongResult.setText(wrongT);
		if(wrongT.compareTo("") == 0) {
			rWrongTip.setText(getResources().getString(R.string.rAllCorrect));
		}
	}

	private void getLink() {
		// TODO Auto-generated method stub
		rCorrectRatio = (TextView) findViewById(R.id.r_CorrectRatio);
		rWrongResult = (TextView) findViewById(R.id.r_WrongResult);
		rWrongTip = (TextView) findViewById(R.id.r_WrongTip);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		/*Intent i= this.getIntent();
		i.setClass(Result.this,ProblemTab.class);
		startActivityForResult(i, 0);*/
		this.finish();
	}
		
}
