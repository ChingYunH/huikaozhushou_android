package tk.huikaozhushou.hkzs;

import java.util.Random;

import tk.huikaozhushou.hkzs.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TestPaper extends Activity {
	Button tFinish = null, tNext = null, tPrevious = null, tAbort = null;
	RadioGroup tRGroup = null;
	TextView tCurrentProblem = null, tDes = null;
	RadioButton tR1 = null,tR2 = null,tR3 = null,tR4 = null;
	ChoiceProblem[] prob;
	int totalProblem = 0;
	int probIDNow = 1;  //This is user's ID.
	int[] userChoice;
	boolean artificialClick = true;
	boolean loaded = false;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testpaper);
		if(loaded == false) {
			getLink();
			buildData();
			showProblem(probIDNow);
			loaded = true;
		}
	}

	private void buildData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		int mode = intent.getIntExtra("MODE" , -1);
		switch (mode) {
		case 1:
			randomChoice20();
			break;
		case 2:
			break;
		default: 
			;
		}
	}

	private void randomChoice20() {
		// TODO Auto-generated method stub
		totalProblem = 20;
		probIDNow = 1;
		userChoice = new int[20];
		Random rdm = new Random();
		prob = new ChoiceProblem [20];
		int [] problemID = new int[20];
		int i,j;
		for(i=0;i<20;i++) {
			// Initialize the Array userChoice
			userChoice [i] = 1;

			int t = 0;
			boolean nextLoop = true;
			while (nextLoop) {
				t = rdm.nextInt(MyData.ChoiceNum[MyData.subject]);
				nextLoop = false;
				for(j=0;j<i;j++) {
					if(problemID[j] == t) {
						nextLoop = true;
						break;
					}
				}
			}
			prob[i] = MyData.Choiceproblem[MyData.subject][t];
			problemID[i] = t;
		}
	}

	private void getLink() {
		// TODO Auto-generated method stub
		tAbort = (Button) findViewById(R.id.t_Abort);
		tFinish = (Button) findViewById(R.id.t_Finish);
		tNext = (Button) findViewById(R.id.t_Next);
		tPrevious = (Button) findViewById(R.id.t_Previous);
		tCurrentProblem = (TextView) findViewById(R.id.t_CurrentProblem);
		tDes = (TextView) findViewById(R.id.t_Des);
		tRGroup = (RadioGroup) findViewById(R.id.t_RGroup);
		tR1 = (RadioButton) findViewById(R.id.t_R1);
		tR2 = (RadioButton) findViewById(R.id.t_R2);
		tR3 = (RadioButton) findViewById(R.id.t_R3);
		tR4 = (RadioButton) findViewById(R.id.t_R4);
		tAbort.setOnClickListener(new ButtonAbort_Click());
		tFinish.setOnClickListener(new ButtonFinish_Click());
		tNext.setOnClickListener(new ButtonNext_Click());
		tPrevious.setOnClickListener(new ButtonPrevious_Click());
		tPrevious.setVisibility(View.INVISIBLE);
		tR1.setOnClickListener(new ButtonR1_Click());
		tRGroup.setOnCheckedChangeListener(new OptionGroup_Change());
		return ;
	}

	private void showProblem(int ProbID) {
		ProbID--;
		tDes.setText(prob[ProbID].getDescription());
		tR1.setText(prob[ProbID].getOption(0));
		tR2.setText(prob[ProbID].getOption(1));
		tR3.setText(prob[ProbID].getOption(2));
		tR4.setText(prob[ProbID].getOption(3));
		setAnswerChecked(ProbID);
		ProbID++;
		tCurrentProblem.setText(""+(ProbID)+"/"+totalProblem);
	}
	
	private void setAnswerChecked(int problemArrayID) {
		// TODO Auto-generated method stub
		int toSet = userChoice[problemArrayID];
		artificialClick = false ;
		switch (toSet) {
		case 2:
			tR2.setChecked(true);
			break;
		case 3:
			tR3.setChecked(true);
			break;
		case 4:
			tR4.setChecked(true);
			break;
		default :
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
			tPrevious.setVisibility(View.VISIBLE);
			probIDNow ++;
			showProblem(probIDNow);
			if(probIDNow >= totalProblem) tNext.setVisibility(View.INVISIBLE);
		}

	}

	class ButtonPrevious_Click implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			tNext.setVisibility(View.VISIBLE);
			probIDNow --;
			showProblem(probIDNow);
			if(probIDNow <= 1) tPrevious.setVisibility(View.INVISIBLE);
		}

	}
	
	class ButtonR1_Click implements OnClickListener{
		public void onClick(View v){
			if(userChoice[probIDNow - 1] == 1)
			{
				tPrevious.setVisibility(View.VISIBLE);
				if(probIDNow < totalProblem){
				probIDNow ++;
				showProblem(probIDNow);
				if(probIDNow >= totalProblem) tNext.setVisibility(View.INVISIBLE);}
			}
		}
	}

	class OptionGroup_Change implements OnCheckedChangeListener {

		@Override 
		public void onCheckedChanged(RadioGroup group, int optionID) {
			// TODO Auto-generated method stub
			if(artificialClick) {
				int userAns = 0; 
				if(optionID == tR1.getId()) userAns = 1;
				if(optionID == tR2.getId()) userAns = 2;
				if(optionID == tR3.getId()) userAns = 3;
				if(optionID == tR4.getId()) userAns = 4;
				userChoice[probIDNow - 1] = userAns;
				tPrevious.setVisibility(View.VISIBLE);
				if(probIDNow < totalProblem){
				probIDNow ++;
				showProblem(probIDNow);
				if(probIDNow >= totalProblem) tNext.setVisibility(View.INVISIBLE);}
			}
		}
		
	}
	
	class ButtonFinish_Click implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int wrongNum = 0;
			String toShow = "";
			String rightAns = null , yourAns = null;
			rightAns = getResources().getString(R.string.tRightAns);
			yourAns = getResources().getString(R.string.tYourAns);
			for(int i = 0 ;i < totalProblem ; i++) {
				if(prob[i].judge(userChoice[i] - 1) == false) {
					wrongNum ++;
					toShow = toShow + (i+1) + "." + prob[i].getDescription() + "\n" ;
					toShow = toShow + prob[i].getOption(0) + "\n";
					toShow = toShow + prob[i].getOption(1) + "\n";
					toShow = toShow + prob[i].getOption(2) + "\n";
					toShow = toShow + prob[i].getOption(3) + "\n";
					toShow = toShow + rightAns + (char)('A' + prob[i].getAns()) + "  ";
					toShow = toShow + yourAns + (char)('A' + userChoice[i] - 1) + "\n\n" ;
				}
			}
			
			//Start New Activity
			Intent intent = new Intent();
			intent.setClass(TestPaper.this, Result.class);
			intent.putExtra("wrongResult", toShow);
			intent.putExtra("correctNum", totalProblem - wrongNum);
			intent.putExtra("totalNum", totalProblem);
			startActivity(intent) ;
			TestPaper.this.finish();
		}
		
	}
	
	class ButtonAbort_Click implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(TestPaper.this, R.string.tAbortTip, Toast.LENGTH_LONG).show();
			TestPaper.this.finish();
		}
		
	}
}
