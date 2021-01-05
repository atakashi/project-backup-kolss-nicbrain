package br.com.kolss.nicbrainmobile.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.slidingmenu.MainActivity;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class LoginActivity extends Activity {

	private final Context context = this;
	
	private EditText txtUsuario = null;
	private EditText txtSenha = null;
	private Button btnLogar = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle(R.string.txtLogin);
		
		txtUsuario = (EditText) this.findViewById(R.id.txtUsuario);
		txtUsuario.setHint("Digite seu ID...");
		txtSenha = (EditText) this.findViewById(R.id.txtSenha);
		txtSenha.setHint("Digite a sua senha...");
		btnLogar = (Button) this.findViewById(R.id.btnLogar);

		btnLogar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ((txtUsuario.getText().toString().trim()).equals(txtSenha.getText().toString().trim())) {
					final Intent intent = new Intent(getBaseContext(), MainActivity.class);
					startActivity(intent);
					finish();
				} else {
			        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
			        alertDialog.setTitle(getString(R.string.usuario_senha_incorreto));
			        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
			            public void onClick(final DialogInterface dialog, final int which) {
			            	alertDialog.dismiss();
			            }
			        });
			        alertDialog.show();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		UtilActivity.displaySairSistema(context, this);
	}
	
}
