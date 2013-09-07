package tk.huikaozhushou.hkzs;

import java.util.Random;

import tk.huikaozhushou.hkzs.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ImmediateAnswer extends Activity {
	Button tNext = null;
	RadioGroup tRGroup = null;
	ImageView icross = null, icheck = null;
	TextView tCurrentProblem = null, tDes = null;
	RadioButton tR1 = null,tR2 = null,tR3 = null,tR4 = null;
	int totalProblem = 0;
	int[] userChoice;
	boolean artificialClick = true;
	boolean loaded = false;
	Random rdm = new Random();
	int userAns;
	int probIDNow = rdm.nextInt(MyData.ChoiceNum[MyData.subject]);  //This is user's ID.
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_immediate_answer);
		if(loaded == false) {
			getLink();
			//buildData();
			showProblem(probIDNow);
			loaded = true;
		}
	}

	private void getLink() {
		// TODO Auto-generated method stub
		tNext = (Button) findViewById(R.id.t_Next);
		tDes = (TextView) findViewById(R.id.t_Des);
		icheck = (ImageView) findViewById(R.id.t_imageCheck);
		icross = (ImageView) findViewById(R.id.t_imageCross);
		tRGroup = (RadioGroup) findViewById(R.id.t_RGroup);
		tR1 = (RadioButton) findViewById(R.id.t_R1);
		tR2 = (RadioButton) findViewById(R.id.t_R2);
		tR3 = (RadioButton) findViewById(R.id.t_R3);
		tR4 = (RadioButton) findViewById(R.id.t_R4);
		tNext.setOnClickListener(new ButtonNext_Click());
		tRGroup.setOnCheckedChangeListener(new OptionGroup_Change());
		return ;
	}

	private void showProblem(int ProbID) {
		tDes.setText(MyData.Choiceproblem[MyData.subject][ProbID].getDescription());
		tR1.setText(MyData.Choiceproblem[MyData.subject][ProbID].getOption(0));
		tR2.setText(MyData.Choiceproblem[MyData.subject][ProbID].getOption(1));
		tR3.setText(MyData.Choiceproblem[MyData.subject][ProbID].getOption(2));
		tR4.setText(MyData.Choiceproblem[MyData.subject][ProbID].getOption(3));
		userAns = -1;
		icheck.setVisibility(4);
		icross.setVisibility(4);
		setAnswerChecked(ProbID);
	}
	
	private void setAnswerChecked(int problemArrayID) {
		// TODO Auto-generated method stub
		int toSet = userAns;
		artificialClick = false ;
		tR1.setChecked(false);
		tR2.setChecked(false);
		tR3.setChecked(false);
		tR4.setChecked(false);
		switch (toSet) {
		case -1:
			break;
		case 2:
			tR2.setChecked(true);
			break;
		case 3:
			tR3.setChecked(true);
			break;
		case 4:
			tR4.setChecked(true);
			break;
		case 1:
			tR1.setChecked(true);
			break;
		}
		artificialClick = true;
	}
	
	class ButtonNext_Click implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			probIDNow = rdm.nextInt(MyData.ChoiceNum[MyData.subject]);
			showProblem(probIDNow);
		}

	}

	class OptionGroup_Change implements OnCheckedChangeListener {

		@Override 
		public void onCheckedChanged(RadioGroup group, int optionID) {
			// TODO Auto-generated method stub
			if(artificialClick) {
				userAns = -1; 
				icheck.setVisibility(4);
				icross.setVisibility(4);
				if(optionID == tR1.getId()) userAns = 0;
				if(optionID == tR2.getId()) userAns = 1;
				if(optionID == tR3.getId()) userAns = 2;
				if(optionID == tR4.getId()) userAns = 3;
				if(MyData.Choiceproblem[MyData.subject][probIDNow].judge(userAns))
					icheck.setVisibility(0);
				else
					icross.setVisibility(0);
			}
		}
		
	}

	public void DisplayToast(int str)  
    {  
        Toast toast=Toast.makeText(this, str, Toast.LENGTH_LONG);  
        toast.show();  
    }  
}
