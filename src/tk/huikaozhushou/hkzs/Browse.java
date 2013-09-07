package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Browse extends Activity {

	SeekBar bar = null;
	Button bPlus = null, bMinus = null;
	CheckBox bShow = null;
	TextView bProblem = null, bAnswer = null, bPage = null;
	int total = 0, now = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		getLink();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		now = 0;
		initBar();
		pageRefresh(now);
		bShow.setChecked(true);
		bAnswer.setVisibility(View.VISIBLE);
	}

	private void initBar() {
		// TODO Auto-generated method stub
		total = MyData.ChoiceNum[MyData.subject] - 1;
		bar.setMax(total);
		bar.setProgress(0);
	}

	private void pageRefresh(int pageID) {
		
		now = pageID;
		
		if( now == 0 ) {
			bMinus.setVisibility(View.INVISIBLE);
		} else {
			bMinus.setVisibility(View.VISIBLE);
		}
		if( now == total ) {
			bPlus.setVisibility(View.INVISIBLE);
		} else {
			bPlus.setVisibility(View.VISIBLE);
		}
		bPage.setText(getResources().getString(R.string.bPageNum) + (now + 1) + "/" + (total + 1));
		bProblem.setText(MyData.Choiceproblem[MyData.subject][now].getLongString());
		bAnswer.setText(MyData.Choiceproblem[MyData.subject][now].getStringAns());
		bar.setProgress(now);
	}

	private void getLink() {
		// TODO Auto-generated method stub
		bar = (SeekBar) findViewById(R.id.b_seekbar);
		bPlus = (Button) findViewById(R.id.b_Plus);
		bMinus = (Button) findViewById(R.id.b_Minus);
		bShow = (CheckBox) findViewById(R.id.b_Show);
		
		bPlus.setOnClickListener(new ButtonPlus_Click());
		bMinus.setOnClickListener(new ButtonMinus_Click());
		bar.setOnSeekBarChangeListener(new bar_Change());
		bShow.setOnCheckedChangeListener(new CheckBox_Change());
		
		bProblem = (TextView) findViewById(R.id.b_Problem);
		bPage = (TextView) findViewById(R.id.b_Page);
		bAnswer = (TextView) findViewById(R.id.b_Answer);
		
		return ;		
	}
	
	class CheckBox_Change implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			// TODO Auto-generated method stub
			if( isChecked ) {
				bAnswer.setVisibility(View.VISIBLE);
			} else {
				bAnswer.setVisibility(View.INVISIBLE);
			}
			return ;
		}
		
	}
	
	class ButtonPlus_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			pageRefresh(now + 1);
		}
		
	}
	
	class ButtonMinus_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			pageRefresh(now - 1);
		}
		
	}

	class bar_Change implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
			// TODO Auto-generated method stub
			pageRefresh(progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
