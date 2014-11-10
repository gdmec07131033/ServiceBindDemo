package cn.edu.gdmec.s07131033.servicebinddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BoundService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "Bind Has OnCreated", 1000).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "Bind Has onDestoried", 1000).show();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Bind Has onUnbinded", 1000).show();
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Bind Has onBinded", 1000).show();
		return mBinder;
	}

	public long Avg(long a, long b) {
		return (a + b) / 2;
	}

	// 创建一个服务绑定接口，返回服务实例；
	public class LocalBinder extends Binder {
		BoundService getService() {
			return BoundService.this;

		}
	}

	private IBinder mBinder = new LocalBinder();
	public static long PI = (long) 3.1415926535798932;

}
