package cn.edu.gdmec.s07131033.servicebinddemo;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button mybt1, mybt2, mybt3, mybt4;
	TextView myTv;
	Intent myIt = new Intent("cn.edu.gdmec.servicebinddemo");
	BoundService myboundservice;
	boolean isbound = false;

	ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			myboundservice = ((BoundService.LocalBinder) service).getService();
			isbound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			isbound = false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myTv = (TextView) findViewById(R.id.textView1);
		mybt1 = (Button) findViewById(R.id.button1);
		mybt2 = (Button) findViewById(R.id.button2);
		mybt3 = (Button) findViewById(R.id.button3);
		mybt4 = (Button) findViewById(R.id.button4);
		mybt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(myIt, mConnection, Context.BIND_AUTO_CREATE);

			}
		});
		mybt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(mConnection);
				isbound = false;
			}
		});
		mybt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				long a = Math.round(Math.random() * 100);
				long b = Math.round(Math.random() * 100);
				if (isbound) {
					long avg = myboundservice.Avg(a, b);
					myTv.setText("(" + String.valueOf(a) + "+"
							+ String.valueOf(b) + ")/2=" + String.valueOf(avg));
				}

			}
		});
		mybt4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isbound) {
					String str = String.valueOf(myboundservice.PI);
					myTv.setText(str);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
