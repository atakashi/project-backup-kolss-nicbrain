package br.com.kolss.nicbrainmobile.util.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;

public class UtilActivity {

    public static final String URI_NICBRAINREST = "http://192.168.0.170:8080/nicBrain/rest";
	
    /**
     * Set Header
     * 
     * @param activity Activity
     * @param title String
     * @param btnICLauncherVisible boolean
     * @param btnNICBrainVisible boolean
     * @param btnComentarioVisible boolean
     */
    public static void setHeader(final Activity activity, final String title, final boolean btnICLauncherVisible, final boolean btnNICBrainVisible, final boolean btnComentarioVisible) {
		final ViewStub stub = (ViewStub) activity.findViewById(R.id.vsHeader);
		final View inflated = stub.inflate();
      
		final TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
		txtTitle.setText(title);

		final Button btnNICBrain = (Button) inflated.findViewById(R.id.btnNICBrain);
		if (!btnNICBrainVisible) {
			btnNICBrain.setVisibility(View.INVISIBLE);
		}

		if (btnICLauncherVisible) {
			btnNICBrain.setBackgroundResource(R.drawable.ic_launcher);
			btnNICBrain.setClickable(false);
			btnNICBrain.setVisibility(View.VISIBLE);
		}
		
		final Button btnComentario = (Button) inflated.findViewById(R.id.btnComentario);
		if (!btnComentarioVisible) {
			btnComentario.setVisibility(View.INVISIBLE);
		}
    }
    
	public static void displaySairSistema(final Context context, final Activity activity) {
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(context.getString(R.string.sairsistema))
			//.setMessage("Clique em Sim para Sair!")
			.setCancelable(false)
			.setPositiveButton(context.getString(R.string.sim), new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int which) {
					activity.finish();
				}
			})
			.setNegativeButton(context.getString(R.string.nao), new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int which) {
					dialog.cancel();
				}
			});
		final AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();		
	}
    
}
