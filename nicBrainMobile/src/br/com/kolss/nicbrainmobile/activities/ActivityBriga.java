package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */
import android.content.Intent;
import android.os.Bundle;

public class ActivityBriga extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_briga);
        //UtilActivity.setHeader(ActivityBriga.this, getString(R.string.ActivityBrigaTitle), false, true, true);
        
		final Intent intent = new Intent(getApplicationContext(), ActivityObservacao.class);
		intent.putExtra("activity", "BRIGA");
		startActivity(intent);
    }
}
