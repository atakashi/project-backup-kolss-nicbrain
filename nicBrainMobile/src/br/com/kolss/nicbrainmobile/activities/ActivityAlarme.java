package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import android.content.Intent;
import android.os.Bundle;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class ActivityAlarme extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alarme);
        setContentView(R.layout.activity_observacao);
        UtilActivity.setHeader(ActivityAlarme.this, getString(R.string.ActivityAlarmeTitle), false, true, true);
        
		final Intent intent = new Intent(getApplicationContext(), ActivityObservacao.class);
		intent.putExtra("activity", "ALARME");
		startActivity(intent);
    }
}
