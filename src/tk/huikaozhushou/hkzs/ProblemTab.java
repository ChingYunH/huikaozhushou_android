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

public class ProblemTab extends ActivityGroup{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_problem_tab);
		
		TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setup(this.getLocalActivityManager());
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent().setClass(this,ImmediateAnswer.class);
		spec = tabHost.newTabSpec("单题随手测");
		spec.setContent(intent);
		spec.setIndicator("单题随手测");
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this,TestPaper.class);
		intent.putExtra("MODE", 1);
		spec = tabHost.newTabSpec("二十题测试");
		spec.setContent(intent);
		spec.setIndicator("二十题测试");
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this,Browse.class);
		spec = tabHost.newTabSpec("浏览全部");
		spec.setContent(intent);
		spec.setIndicator("浏览全部");
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(3);
		
	}

}
