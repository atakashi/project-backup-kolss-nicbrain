package br.com.kolss.nicbrainmobile.activities;

/**
 * @author LuisCM
 */

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.activities.atividadeocorrencia.ActivityAtividadeOcorrencia;
import br.com.kolss.nicbrainmobile.slidingmenu.MainActivity;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;
import br.com.kolss.nicbrainmobile.util.gps.GPSTracker;
import br.com.kolss.nicbrainmobile.util.rest.RestTask;

//public class ActivityObservacao extends ActivityDashBoard implements OnClickListener {
public class ActivityObservacao extends FragmentActivity implements OnClickListener {
	
	private static final String TAG = "ActivityObservacao";
	
	private static final String ACTIVITY_OBSERVACAO_ACTION = "br.com.kolss.nicbrainmobile.activities.ActivityObservacao";
	
    private static final String URI_OCORRENCIA = UtilActivity.URI_NICBRAINREST + "/ocorrencia";
    private static final String URI_PROCEDIMENTO = URI_OCORRENCIA + "/procedimento";
	
//	private View rootView = null;
	private Bundle b = null;
	private GPSTracker gps = null;	
	private ProgressDialog progress = null;
	
    private EditText txtObservacao = null;
	private Button btnEnviar = null;

	private String activity = null;
    private double latitude = 0d;
    private double longitude = 0d;
	
    public ActivityObservacao() {
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacao);
        //UtilActivity.setHeader(ActivityObservacao.this, getString(R.string.ActivityObservacaoTitle), false, true, true);
        txtObservacao = (EditText) findViewById(R.id.editObservacao);
        txtObservacao.setHint("Digite a observação...");
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        
		b = getIntent().getExtras();
		if (b != null) {
			activity = b.getString("activity");
		}
    }
	
    /**
     * onClick Button
     * 
     * @param v View
     */
	public void onClick(final View v) {
		Log.i(TAG, "Observacao");
		btnEnviar.setClickable(false);
		btnEnviar.setEnabled(false);

		gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), "Sua localizacao - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();   
        } else {
            // Can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }		
        gps.stopUsingGPS();
        
        postData();
	}

	private void postData() {
		try {
			final HttpPost request = new HttpPost(new URI(URI_PROCEDIMENTO));
			final List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			
			parameters.add(new BasicNameValuePair("NOME_CLASSIFICACAO_OCORRENCIA", activity.toUpperCase()));
			parameters.add(new BasicNameValuePair("DATA_HORA", String.valueOf(new Date())));
			parameters.add(new BasicNameValuePair("LATITUDE", Double.toString(latitude)));
			parameters.add(new BasicNameValuePair("LONGITUDE", Double.toString(longitude)));
			parameters.add(new BasicNameValuePair("DESCRICAO_OCORRENCIA", txtObservacao.getText().toString()));
			
			request.setEntity(new UrlEncodedFormEntity(parameters));

			final RestTask task = new RestTask(this, ACTIVITY_OBSERVACAO_ACTION);
			task.execute(request);

			progress = ProgressDialog.show(this, "Aguarde", "Enviando os dados...", true);
			
		} catch (final Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		registerReceiver(receiver, new IntentFilter(ACTIVITY_OBSERVACAO_ACTION));
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, Intent intent) {
			if (progress != null) {
				progress.dismiss();
				finish();
			}
			final String response = intent.getStringExtra(RestTask.HTTP_RESPONSE);
			intent = new Intent(context, ActivityAtividadeOcorrencia.class);		
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("activity", activity);
			intent.putExtra("response", response);
			startActivity(intent);
			finish();
		}
	};
    
	@Override
	public void onBackPressed() {
		final Intent intent = new Intent(getApplicationContext(), MainActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
	
}