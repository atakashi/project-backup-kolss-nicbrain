package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import android.content.Intent;
import android.os.Bundle;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class ActivityFogo extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fogo);
        setContentView(R.layout.activity_observacao);
        UtilActivity.setHeader(ActivityFogo.this, getString(R.string.ActivityFogoTitle), false, true, true);
        
		final Intent intent = new Intent(getApplicationContext(), ActivityObservacao.class);
		intent.putExtra("activity", "FOGO");
		startActivity(intent);
    }
}
