package br.com.kolss.nicbrainmobile;

/**
 * @author LuisCM
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.activities.ActivityAgua;
import br.com.kolss.nicbrainmobile.activities.ActivityAlarme;
import br.com.kolss.nicbrainmobile.activities.ActivityBriga;
import br.com.kolss.nicbrainmobile.activities.ActivityDashBoard;
import br.com.kolss.nicbrainmobile.activities.ActivityFerido;
import br.com.kolss.nicbrainmobile.activities.ActivityFogo;
import br.com.kolss.nicbrainmobile.activities.ActivityRoubo;
import br.com.kolss.nicbrainmobile.activities.ActivityTumulto;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class ActivityNICBrainMobile extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nicbrainmobile);
        UtilActivity.setHeader(ActivityNICBrainMobile.this, getString(R.string.ActivityNICBrainTitle), true, false, true);
    }
    
    /**
     * Button click handler on NicBrainMobile activity
     * 
     * @param v View
     */
    public void onButtonClicker(final View v) {
    	Intent intent = null;
    	switch (v.getId()) {
			case R.id.activity_nicbrainmobile_btn_briga:
				intent = new Intent(this, ActivityBriga.class);
				startActivity(intent);
				break;
			case R.id.activity_nicbrainmobile_btn_alarme:
				intent = new Intent(this, ActivityAlarme.class);
				startActivity(intent);
				break;
			case R.id.activity_nicbrainmobile_btn_fogo:
				intent = new Intent(this, ActivityFogo.class);
				startActivity(intent);
				break;
			case R.id.activity_nicbrainmobile_btn_tumulto:
				intent = new Intent(this, ActivityTumulto.class);
				startActivity(intent);
				break;
			case R.id.activity_nicbrainmobile_btn_roubo:
				intent = new Intent(this, ActivityRoubo.class);
				startActivity(intent);
				break;
			case R.id.activity_nicbrainmobile_btn_agua:
				intent = new Intent(this, ActivityAgua.class);
				startActivity(intent);
				break;	
			case R.id.activity_nicbrainmobile_btn_ferido:
				intent = new Intent(this, ActivityFerido.class);
				startActivity(intent);
				break;	
			default:
				break;
		}
    }
    
}