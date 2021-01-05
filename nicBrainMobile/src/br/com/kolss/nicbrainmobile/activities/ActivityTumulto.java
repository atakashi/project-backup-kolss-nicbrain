package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import android.content.Intent;
import android.os.Bundle;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class ActivityTumulto extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tumulto);
        setContentView(R.layout.activity_observacao);
        UtilActivity.setHeader(ActivityTumulto.this, getString(R.string.ActivityTumultoTitle), false, true, true);
        
		final Intent intent = new Intent(getApplicationContext(), ActivityObservacao.class);
		intent.putExtra("activity", "TUMULTO");
		startActivity(intent);
    }
}
