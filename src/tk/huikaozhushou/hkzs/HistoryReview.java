package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.R.bool;
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

public class HistoryReview extends Activity {

	SeekBar bar = null;
	Button bPlus = null, bMinus = null;
	CheckBox bShowAns = null, bShowDes = null;
	TextView bDescript = null, bAnswer = null, bPage = null;
	int total = 0, now = 0;
	boolean answer_status = false, descript_status = false;
	boolean answer_always = false, descript_always = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_review);
		getLink();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		now = 0;
		initBar();
		pageRefresh(now);
		bShowAns.setChecked(false);
		bShowDes.setChecked(false);
		bAnswer.setVisibility(View.VISIBLE);
		bDescript.setVisibility(View.VISIBLE);
	}

	private void initBar() {
		// TODO Auto-generated method stub
		total = MyData.ReviewNum[MyData.historyreview] - 1;
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
		if(!descript_always) bDescript.setText(R.string.clickToShow);
		else bDescript.setText(MyData.Review[MyData.historyreview][now].getDescription());
		if(!answer_always) bAnswer.setText(R.string.clickToShow);
		else bAnswer.setText(MyData.Review[MyData.historyreview][now].getAnswer());
		descript_status = answer_status = false;
		bar.setProgress(now);
	}

	private void getLink() {
		// TODO Auto-generated method stub
		bar = (SeekBar) findViewById(R.id.b_seekbar);
		bPlus = (Button) findViewById(R.id.b_Plus);
		bMinus = (Button) findViewById(R.id.b_Minus);
		bShowAns = (CheckBox) findViewById(R.id.b_ShowAns);
		bShowDes = (CheckBox) findViewById(R.id.b_ShowDes);
		bDescript = (TextView) findViewById(R.id.b_Descript);
		bPage = (TextView) findViewById(R.id.b_Page);
		bAnswer = (TextView) findViewById(R.id.b_Answer);
		
		bPlus.setOnClickListener(new ButtonPlus_Click());
		bMinus.setOnClickListener(new ButtonMinus_Click());
		bar.setOnSeekBarChangeListener(new bar_Change());
		bShowAns.setOnCheckedChangeListener(new CheckBox_Ans_Change());
		bShowDes.setOnCheckedChangeListener(new CheckBox_Des_Change());
		bDescript.setOnClickListener(new ButtonDescript_Click());
		bAnswer.setOnClickListener(new ButtonAnswer_Click());
		
		return ;		
	}
	
	class CheckBox_Ans_Change implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			// TODO Auto-generated method stub
			if( isChecked ) {
				answer_always = true;
				answer_status = true;
				bAnswer.setText(MyData.Review[MyData.historyreview][now].getAnswer());
			}
			else {
				answer_always = false;
			}
			return ;
		}
		
	}
	
	class CheckBox_Des_Change implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			// TODO Auto-generated method stub
			if( isChecked ) {
				descript_always = true;
				descript_status = true;
				bDescript.setText(MyData.Review[MyData.historyreview][now].getDescription());
			} 
			else {
				descript_always = false;
			}
			return ;
		}
		
	}
	class ButtonDescript_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(!descript_always) descript_status = !descript_status;
			if(descript_status == true)
				bDescript.setText(MyData.Review[MyData.historyreview][now].getDescription());
			else
				bDescript.setText(R.string.clickToShow);
			return ;
		}
		
	}
	class ButtonAnswer_Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(!answer_always) answer_status = !answer_status;
			if(answer_status == true)
				bAnswer.setText(MyData.Review[MyData.historyreview][now].getAnswer());
			else
				bAnswer.setText(R.string.clickToShow);
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
