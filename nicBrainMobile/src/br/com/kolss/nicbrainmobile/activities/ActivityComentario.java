package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import android.os.Bundle;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class ActivityComentario extends ActivityDashBoard {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        UtilActivity.setHeader(ActivityComentario.this, getString(R.string.ActivityComentarioTitle), false, true, false);
    }
}
