package tk.huikaozhushou.hkzs;

import java.io.InputStream;
import org.apache.http.util.EncodingUtils;

import tk.huikaozhushou.hkzs.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class Launcher extends Activity {

	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		//If the data have been loaded, load Welcome Directly.
		if(MyData.dataLoaded) {
			Intent i = new Intent(Launcher.this, Welcome.class);  
			startActivity(i);
			finish();
			return ;
		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		setContentView(R.layout.launcher);

		new processThread().start();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(MyData.dataLoaded==false) {
			int pid = android.os.Process.myPid() ; 
			android.os.Process.killProcess(pid) ; 
		}
	}

	Handler UIUpdate = new Handler() {
		public void handleMessage(Message msg) {
			MyData.dataLoaded=true;
			Intent i = new Intent(Launcher.this, Welcome.class);  
			startActivity(i);
			finish();
		}
	};

	class processThread extends Thread {
		private final char BOM = 65279;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String choice="";
			String review="";
			MyData.subject = MyData.biologychoice;
			choice = getFromRaw(R.raw.resbiology);
			handleChoice(choice);
			MyData.subject = MyData.historychoice;
			choice = getFromRaw(R.raw.reshistory);
			handleChoice(choice);
			MyData.subject = MyData.infomaticschoice;
			choice = getFromRaw(R.raw.resinfomatics);
			handleChoice(choice);
			MyData.subject = MyData.geographychoice;
			choice = getFromRaw(R.raw.resgeography);
			handleChoice(choice);
			MyData.subjectR = MyData.biologyreview;
			review = getFromRaw(R.raw.biology);
			handleReview(review);
			MyData.subjectR = MyData.historyreview;
			review = getFromRaw(R.raw.history);
			handleReview(review);
			MyData.subjectR = MyData.chemistryreview;
			review = getFromRaw(R.raw.chemistry);
			handleReview(review);
			setView();
		}


		/*
		private void handleTF(String tf) {
			// TODO Auto-generated method stub
			int pointer=0, end ,ans;
			int strlen=tf.length();
			String nextLine = "\n\n";
			if(tf.charAt(pointer) == BOM) pointer++;
			while(pointer<strlen) {
				end=tf.indexOf(nextLine, pointer);  
				if(end == -1) break;
				ans = transferTF(tf.charAt(pointer));
				addNewTF(tf.substring(pointer + 2, end) , ans);
				pointer = end + 2;
			}
		}
		private void addNewTF(String des, int ans) {
			// TODO Auto-generated method stub
			MyData.TFproblem[MyData.TFNum] = new TFProblem();
			MyData.TFproblem[MyData.TFNum].setDescription(des);
			MyData.TFproblem[MyData.TFNum].setRightAns(ans);
			MyData.TFNum++;
			return ;
		}

		private int transferTF(char x) {
			// TODO Auto-generated method stub
			if(x=='T') return 1;
			if(x=='F') return 0;
			return -1;
		}
		 */
		private void handleChoice(String choice) {
			// TODO Auto-generated method stub
			int pointer = 0, len = choice.length();
			int l1,l2,ans,end;
			String options[] = new String[5];
			if(choice.charAt(pointer)==BOM) pointer++;
			while(pointer < len) {
				
				while (true) {
					if(pointer >= len) {
						break;
					}
					if(choice.charAt(pointer) == 10 || choice.charAt(pointer) == 13) {
						pointer ++;
					} else {
						break;
					}
				}
				
				if( pointer >= len ) break;
				
				ans = choice.charAt(pointer) - 'A';
				end = choice.indexOf("A.",pointer);
				if(end==-1) break;
				//A
				l1=end;
				l2=choice.indexOf('\n',l1);
				options[0] = choice.substring(l1,l2);
				//B
				l1=l2+1;
				l2=choice.indexOf('\n',l1);
				options[1] = choice.substring(l1,l2);
				//C
				l1=l2+1;
				l2=choice.indexOf('\n',l1);
				options[2] = choice.substring(l1,l2);
				//D
				l1=l2+1;
				l2=choice.indexOf('\n',l1);
				options[3] = choice.substring(l1,l2);
				addNewChoice(choice.substring(pointer + 2,end - 1), options, ans);
				pointer = l2 + 2;
			}
		}
		private void addNewChoice(String des,String[] option,int ans) {
			int i;
			MyData.Choiceproblem[MyData.subject][MyData.ChoiceNum[MyData.subject]] = new ChoiceProblem();
			for(i=0;i<=3;i++) {
				MyData.Choiceproblem[MyData.subject][MyData.ChoiceNum[MyData.subject]].setOption(option[i], i);
			}
			MyData.Choiceproblem[MyData.subject][MyData.ChoiceNum[MyData.subject]].setDescription(des);
			MyData.Choiceproblem[MyData.subject][MyData.ChoiceNum[MyData.subject]].setRightAns(ans);
			MyData.ChoiceNum[MyData.subject]++;
		}
		private void handleReview(String review)
		{
			int pointer = 0, len = review.length();
			int end,end2;
			if(review.charAt(pointer)==BOM) pointer++;
			while(pointer < len) {
				
				while (true) {
					if(pointer >= len) {
						break;
					}
					if(review.charAt(pointer) == 10 || review.charAt(pointer) == 13) {
						pointer ++;
					} else {
						break;
					}
				}
				
				if( pointer >= len ) break;
				
				end = review.indexOf("\n",pointer);
				if(end==-1) break;
				end2=review.indexOf('\n',end+1);
				addNewReview(review.substring(pointer,end), review.substring(end+1,end2));
				pointer = end2 + 2;
			}
		}
		private void addNewReview(String des,String ans){
			MyData.Review[MyData.subjectR][MyData.ReviewNum[MyData.subjectR]] = new ReviewMaterial();
			MyData.Review[MyData.subjectR][MyData.ReviewNum[MyData.subjectR]].setDescription(des);
			MyData.Review[MyData.subjectR][MyData.ReviewNum[MyData.subjectR]].setAnswer(ans);
			MyData.ReviewNum[MyData.subjectR]++;
		}
		/*
		private String getFromAssets(String fileName,int times){ 
			try { 
				InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName),"utf-8");
				BufferedReader bufReader = new BufferedReader(inputReader);

				String line="";
				String Result="";
				String nextLine="\n";
				int i=0;
				while((line = bufReader.readLine()) != null) {
					Result += line+nextLine;
					i++;
					if(i%100==0) {
						Message msg = ProgressUpdate.obtainMessage();
						if(times==1) {
							msg.arg1=60*i/MyData.ChoiceproblemLine;
						} else if(times==2){
							msg.arg1=60+20*i/MyData.TFproblemLine;
						}
						ProgressUpdate.sendMessage(msg);
					}
				}
				return Result;
			} catch (Exception e) { 
				e.printStackTrace(); 
			}
			return "";
		} */

		private String getFromRaw(int resID) {
			String rtn = "";
			try {
				InputStream in = getResources().openRawResource(resID);
				int lenght = in.available();
				byte[]  buffer = new byte[lenght];
				in.read(buffer);
				rtn = EncodingUtils.getString(buffer, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rtn;
		}

		private void setView() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			UIUpdate.sendMessage(UIUpdate.obtainMessage());
		}
	}
}
