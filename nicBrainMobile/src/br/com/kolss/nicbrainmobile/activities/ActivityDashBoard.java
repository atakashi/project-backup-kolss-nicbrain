package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.kolss.nicbrainmobile.ActivityNICBrainMobile;

public abstract class ActivityDashBoard extends Activity {
	
    public static final String URL_SERVICE = "http://192.168.0.199:8080/nicBrain/rest/ocorrencia";
    public static final String URL_PROCEDIMENTO = URL_SERVICE + "/procedimento";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    /**
     * ICLauncher button click handler
     * 
     * @param v View
     */
    public void btnICLauncherClick(final View v) {
    }
    
    /**
     * NICBrain button click handler
     * 
     * @param v View
     */
    public void btnNICBrainClick(final View v) {
    	final Intent intent = new Intent(getApplicationContext(), ActivityNICBrainMobile.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);
    }
    
    /**
     * Comentario button click handler
     * 
     * @param v View
     */
    public void btnComentarioClick(final View v) {
    	final Intent intent = new Intent(getApplicationContext(), ActivityComentario.class);
    	startActivity(intent);
    }

}