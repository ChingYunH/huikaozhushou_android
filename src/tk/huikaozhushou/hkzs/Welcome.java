package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class Welcome extends Activity {
	/** Called when the activity is first created. */
	private static final String[] subjects = {"生物","历史","信息","地理"};
	private TextView txt = null;
	private TextView ms = null;
//	private TextView chosensubject = null;
//	private Button reselect = null;
	private Spinner subjectSelect = null;
	private ArrayAdapter<String> adapter = null;
	boolean galLoaded = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		getLink();
		getDateDiff();
		UILoad2();
	}

	private void prepareGallery(){
		Integer[] images = {R.drawable.test,R.drawable.browse,R.drawable.about};
		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();
		GalleryFlow gf=(GalleryFlow) findViewById(R.id.m_Gallery);
		gf.setAdapter(adapter);
		gf.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) {
				// TODO Auto-generated method stub
				switch(pos) {
				case 0:
					clickButton1();
					break;
				case 1:
					clickButton2();
					break;
				case 2:
					clickButton3();
					break;
				}
			}

		});
		gf.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3) {
				// TODO Auto-generated method stub
				switch(pos) {
				case 0:
					ms.setText(R.string.mB1);
					break;
				case 1:
					ms.setText(R.string.mB2);
					break;
				case 2:
					ms.setText(R.string.mB3);
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
	}
	protected void clickButton1() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(Welcome.this, ProblemTab.class);
		startActivity(intent);
	}

	protected void clickButton2() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Welcome.this, ReviewTab.class);
		startActivity(intent);
	}

	protected void clickButton3() {
		// TODO Auto-generated method stub
		Log.i("yjc","Button click Receive");
		Intent intent = new Intent();
		intent.setClass(Welcome.this, About.class);
		startActivity(intent);
	}

	private void getDateDiff() {
		// TODO Auto-generated method stub
		long diff = 0;
		long nowMs;
		final long DIV = 86400000;
		nowMs = System.currentTimeMillis();
		diff = 15519 - nowMs / DIV;
		if(diff <= 0) return ;

		String toput = "";
		toput = toput + getResources().getString(R.string.mDateDiff) + diff + getResources().getString(R.string.mDateDay);
	}


	private void getLink() {
		// TODO Auto-generated method stub
		txt=(TextView) findViewById(R.id.m_Text);
//		chosensubject=(TextView) findViewById(R.id.subjectChosen);
//		reselect= (Button) findViewById(R.id.reselect);
//		reselect.setOnClickListener(new ButtonReselect_Click());
		ms = (TextView) findViewById(R.id.m_Selected);
		subjectSelect = (Spinner) findViewById(R.id.select);
	}

	private void UILoad2() {
		//txt.setVisibility(View.VISIBLE);
		txt.setText(R.string.mHello);
		if(galLoaded == false) {
			prepareGallery();
			galLoaded = true;
		}
		ms.setVisibility(View.VISIBLE);
//		final CharSequence[] items = {"生物","历史","信息","地理"};  
//		chosensubject.setText("当前题库为："+items[MyData.subject]);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjects);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSelect.setAdapter(adapter); 
		subjectSelect.setOnItemSelectedListener(new SpinnerSelectedListener());
		
	}

	class SpinnerSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int item,
				long arg3) {
			// TODO Auto-generated method stub
			MyData.subject = item;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
		
/*	class ButtonReselect_Click implements OnClickListener{
		
		public void onClick(View v){
			final CharSequence[] items = {"生物","历史","信息","地理"};   
			AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);  
			builder.setTitle(R.string.pleaseChoose);  
			builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int item) {  
			        MyData.subject = item;
			        dialog.dismiss();
			        chosensubject.setText("当前题库为："+items[MyData.subject]);
			    }  
			});  
			AlertDialog alert = builder.create(); 
			alert.show();
		}
	}
	*/
	
}