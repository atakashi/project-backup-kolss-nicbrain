package br.com.kolss.nicbrainmobile.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.login.LoginActivity;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash);
        
        new CountDownTimer(5000, 1000) {        	
			@Override
			public void onFinish() {
				final Intent intent = new Intent(getBaseContext(), LoginActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();
    }

}
