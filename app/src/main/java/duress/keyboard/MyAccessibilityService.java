package duress.keyboard;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.*;
import android.os.UserManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.accessibility.AccessibilityEvent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;


public class MyAccessibilityService extends AccessibilityService {

	private void BindHelper() {		            
			
			   try {
                   Context appContext = getApplicationContext();
                   Intent serviceIntent = new Intent(appContext, RiderService.class);

                   appContext.bindService(serviceIntent, new ServiceConnection() {
                       @Override
                       public void onServiceConnected(ComponentName name, IBinder service) {                       
                    
                       }

                       @Override
                       public void onServiceDisconnected(ComponentName name) {                        
                       
                       }
                   }, Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT | Context.BIND_ABOVE_CLIENT);
               } catch (Throwable BindError) {}			        
	}

	@Override
	public void onCreate() {
    super.onCreate();

	try {	
    new Thread(() -> {        			
			BindHelper();	
			try {
		    Intent serviceIntent = new Intent(this, RiderService.class);
            startForegroundService(serviceIntent);
            } catch (Throwable fgErr) {}        	
    }).start();
	} catch (Throwable threadErr) {}	
	
	}


    @Override
    protected void onServiceConnected() {
		super.onServiceConnected();                	
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();        
    }

    @Override
public void onAccessibilityEvent(AccessibilityEvent event) {

}

@Override
public void onInterrupt() {
    
}


    
}
