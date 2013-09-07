package tk.huikaozhushou.hkzs;

import tk.huikaozhushou.hkzs.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.view.Menu;
import android.widget.TabHost;
import android.content.DialogInterface;
import android.content.Intent;

public class ReviewTab extends ActivityGroup{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_tab);
		
		TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setup(this.getLocalActivityManager());
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent().setClass(this,HistoryReview.class);
		spec = tabHost.newTabSpec("大事年表");
		spec.setContent(intent);
		spec.setIndicator("大事年表");
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this,ChemistryReview.class);
		spec = tabHost.newTabSpec("化学方程");
		spec.setContent(intent);
		spec.setIndicator("化学方程");
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this,BiologyReview.class);
		spec = tabHost.newTabSpec("生物知识点");
		spec.setContent(intent);
		spec.setIndicator("生物知识点");
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(3);
		
	}

}
