package br.com.kolss.nicbrainmobile.activities.atividadeocorrencia;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.adapter.AtividadeOcorrenciaAdapter;
import br.com.kolss.nicbrainmobile.model.AtividadeOcorrencia;
import br.com.kolss.nicbrainmobile.slidingmenu.MainActivity;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

/**
 * @author LuisCM
 */
//public class ActivityOcorrencia extends ActivityDashBoard implements OnClickListener {
public class ActivityAtividadeOcorrencia extends FragmentActivity {

	private static final String URI_ATIVIDADE_OCORRENCIA = UtilActivity.URI_NICBRAINREST + "/atividadeocorrencia/lista/";
 
	private Context context = this;
	
	private ListView lstViewAtividadeOcorrencia = null; 
	private AtividadeOcorrenciaAdapter atividadeOcorrenciaAdapter = null;
	
	protected Bundle bundle = null;
	protected String activity = null;
	protected Integer idOcorrencia = null;
	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		//setContentView(R.layout.activity_atividade_ocorrencia);
//
//		final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View view = inflater.inflate(R.layout.activity_atividade_ocorrencia, null);
//		final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header_atividade_ocorrencia, lstViewAtividadeOcorrencia, false);
//		
//		lstViewAtividadeOcorrencia = (ListView) view.findViewById(R.id.lstViewAtividadeOcorrencia);
//		lstViewAtividadeOcorrencia.addHeaderView(header);
//		
//		atividadeOcorrenciaAdapter = new AtividadeOcorrenciaAdapter(new ArrayList<AtividadeOcorrencia>(), context);
//        lstViewAtividadeOcorrencia = (ListView) view.findViewById(R.id.lstViewAtividadeOcorrencia);
//        lstViewAtividadeOcorrencia.setAdapter(atividadeOcorrenciaAdapter);
//		
//		setContentView(view);
//		
//		bundle = getIntent().getExtras();
//		if (bundle != null) {
//			activity = bundle.getString("activity");
//			idOcorrencia = Integer.parseInt(bundle.getString("response"));
//		}
//		
//		final String uri = new StringBuilder(URI_ATIVIDADE_OCORRENCIA)
//								.append(activity).append("/")
//								.append(idOcorrencia).toString();
//        (new AsyncListViewLoader()).execute(uri);
//		
//	}

	
	
	String[] nome = 
		{
			"AAAAAAAAA",
            "BBBBBBBBB",
            "CCCCCCCCC",
            "DDDDDDDDD",
            "EEEEEEEEE",
            "FFFFFFFFF",
			"AAAAAAAAA",
            "BBBBBBBBB",
            "CCCCCCCCC",
            "DDDDDDDDD",
            "EEEEEEEEE",
            "FFFFFFFFF"
		};
	String[] dataini = 
		{
			"01/01/2014",
            "10/01/2014",
            "15/01/2014",
            "20/01/2014",
            "28/01/2014",
            "31/01/2014",
			"01/01/2014",
            "10/01/2014",
            "15/01/2014",
            "20/01/2014",
            "28/01/2014",
            "31/01/2014"
		};
	String[] datafin = 
		{
            "31/01/2014",
            "28/01/2014",
            "20/01/2014",
            "15/01/2014",
            "10/01/2014",
			"01/01/2014",
            "31/01/2014",
            "28/01/2014",
            "20/01/2014",
            "15/01/2014",
            "10/01/2014",
			"01/01/2014"
		};
	
	
	
	
	
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_ocorrencia);

        atividadeOcorrenciaAdapter = new AtividadeOcorrenciaAdapter(new ArrayList<AtividadeOcorrencia>(), context);
        
		bundle = getIntent().getExtras();
		if (bundle != null) {
			activity = bundle.getString("activity");
			idOcorrencia = Integer.parseInt(bundle.getString("response"));
		}
		
		final String uri = new StringBuilder(URI_ATIVIDADE_OCORRENCIA)
								.append(activity).append("/")
								.append(idOcorrencia).toString();
        (new AsyncListViewLoader()).execute(uri);
        
        
//        List<AtividadeOcorrencia> lst = atividadeOcorrenciaAdapter.getLstAtividadeOcorrencia();
//        
//       TableLayout tv=(TableLayout) findViewById(R.id.table1);
//        tv.removeAllViewsInLayout();
//        
//        //int x_length=nome.length;
//        int x_length=lst.size();
//        int i=0;
//
//        
////        String tstr1 = "<font color=#0099ff>Nome</font> <br/> <font color=#ffffff> </font>";
////        String tstr2 = "<font color=#0099ff>Data</font> <br/> <font color=#ffffff> </font>";
//
////        String tstr1 = "<font color=#e47e25>Atividade</font>";
////        String tstr2 = "<font color=#e47e25>Data Inicial</font>";
////        String tstr3 = "<font color=#e47e25>Data Final</font>";
////        String tstr4 = "<font color=#e47e25>Realizado</font>";
//        
//        String tstr1 = "Atividade";
//        String tstr2 = "Data Inicial";
//        String tstr3 = "Data Final";
//        String tstr4 = "Observação";
//        String tstr5 = "Realizado";
//        
//        
//	  	final TableRow ttr=new TableRow(this);
//	
//	    ttr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//
//	    
//	    ttr.setId(0); 
//	     	
//	    final TextView tb1=new TextView(this);
//	    tb1.setTextSize(12);
//	    tb1.setText(Html.fromHtml(tstr1));
//	    tb1.setWidth(200);
//	    tb1.setBackground(getResources().getDrawable(R.color.custom_button));
//	    ttr.addView(tb1);
//	        
//	    final TextView tb2=new TextView(this);
//	    tb2.setTextSize(12);
//	    tb2.setText(Html.fromHtml(tstr2));
//	    tb2.setWidth(150);
//	    tb2.setBackground(getResources().getDrawable(R.color.custom_button));
//	    ttr.addView(tb2);
//
//	    final TextView tb3=new TextView(this);
//	    tb3.setTextSize(12);
//	    tb3.setText(Html.fromHtml(tstr3));
//	    tb3.setWidth(150);
//	    tb3.setBackground(getResources().getDrawable(R.color.custom_button));
//	    ttr.addView(tb3);
//	    
//	    final TextView tb4=new TextView(this);
//	    tb4.setTextSize(12);
//	    tb4.setText(Html.fromHtml(tstr4));
//	    tb4.setWidth(65);
//	    tb4.setBackground(getResources().getDrawable(R.color.custom_button));
//	    ttr.addView(tb4);
//	    
//	    final TextView tb5=new TextView(this);
//	    tb5.setTextSize(12);
//	    tb5.setText(Html.fromHtml(tstr5));
//	    //tb5.setWidth(50);
//	    tb5.setBackground(getResources().getDrawable(R.color.custom_button));
//	    ttr.addView(tb5);
//	    
//	   	//ttr.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
//	    
//	   	tv.addView(ttr);
//
//	   	
//        while(i < x_length) {
//        	
////        String str1 = "<font color=#0099ff>Nome</font> <br/> <font color=#ffffff> "+name[i]+" </font>";
////        String str2 = "<font color=#0099ff>Data</font> <br/> <font color=#ffffff> "+age[i]+" </font>";
//
//        String str1 = nome[i];
//        String str2 = dataini[i];
//        String str3 = datafin[i];
//        
//
//    	final TableRow tr=new TableRow(this);
//
//    	    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//    	       
//    	     	tr.setId(i); 
//    	     	
//    	        final TextView b1=new TextView(this);
//    	           b1.setTextSize(12);
//    	           b1.setText(Html.fromHtml(str1));
//    	           b1.setWidth(200);
//    	        tr.addView(b1);
//    	        
//    	        final TextView b2=new TextView(this);
// 	           b2.setTextSize(12);
// 	           b2.setText(Html.fromHtml(str2));
// 	           b2.setWidth(150);
// 	        tr.addView(b2);
//
//	        final TextView b3=new TextView(this);
//	           b3.setTextSize(12);
//	           b3.setText(Html.fromHtml(str3));
//	           b3.setWidth(150);
//	        tr.addView(b3);
// 	        
//	        
//	        final Button b4=new Button(this);
//	           b4.setHeight(12);
//	           b4.setTextSize(12);
//	           b4.setText("...");
//	           //b4.setWidth(70);
//	        tr.addView(b4);
// 	        
//	        final Switch mySwitch = new Switch(this);
//	        //b5.setText(Html.fromHtml(tstr5));
//	        mySwitch.setHeight(12);
//	        mySwitch.setTextSize(12);
//	        tr.addView(mySwitch);
//
//    	   	  //tr.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
//    	       tv.addView(tr);
//    	       
//    	     //set the switch to ON
//    	       mySwitch.setChecked(false);
//    	       //attach a listener to check for changes in state
//    	       mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//    	      
//    	        @Override
//    	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//    	      
//    	         if(isChecked){
//    	        	 Toast.makeText(getApplicationContext(), "Realizado", Toast.LENGTH_SHORT).show();    	        	 
//    	         }else{
//    	        	 Toast.makeText(getApplicationContext(), "Não realizado", Toast.LENGTH_SHORT).show();    	        	 
//    	         }
//    	      
//    	        }
//    	       });
//    	        
////    	       //check the current state before we display the screen
////    	       if(mySwitch.isChecked()){
////  	        	 Toast.makeText(getApplicationContext(), "Switch is currently ON", Toast.LENGTH_SHORT).show();    	        	 
////    	       }
////    	       else {
////    	        	 Toast.makeText(getApplicationContext(), "Switch is currently OFF", Toast.LENGTH_SHORT).show();    	        	 
////    	       }
////    	      }    	       
//    	        
//    	    final View vline1 = new View(this);
//    	  vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
//    	  vline1.setBackgroundColor(Color.LTGRAY);
//    	  tv.addView(vline1);		
//          tr.setOnClickListener(new View.OnClickListener() {
//	    		@Override
//	    		public void onClick(View v) {
//	    			                
//	    			String e=String.valueOf(tr.getId());
//	    			Toast.makeText(getApplicationContext(), e, Toast.LENGTH_SHORT).show();   
//	    		}
//	    	});
//        i++;
//        }
        
    }

    
    private void mostra(final List<AtividadeOcorrencia> result) {
        //final List<AtividadeOcorrencia> lst = atividadeOcorrenciaAdapter.getLstAtividadeOcorrencia();
        final List<AtividadeOcorrencia> lst = result;
        
       TableLayout tv=(TableLayout) findViewById(R.id.table1);
        tv.removeAllViewsInLayout();
        
        //int x_length=nome.length;
        int x_length=lst.size();
        int i=0;

        
//        String tstr1 = "<font color=#0099ff>Nome</font> <br/> <font color=#ffffff> </font>";
//        String tstr2 = "<font color=#0099ff>Data</font> <br/> <font color=#ffffff> </font>";

//        String tstr1 = "<font color=#e47e25>Atividade</font>";
//        String tstr2 = "<font color=#e47e25>Data Inicial</font>";
//        String tstr3 = "<font color=#e47e25>Data Final</font>";
//        String tstr4 = "<font color=#e47e25>Realizado</font>";
        
        String tstr1 = "Atividade";
        String tstr2 = "Data Inicial";
        String tstr3 = "Data Final";
        String tstr4 = "Observação";
        String tstr5 = "Realizado";
        
        
	  	final TableRow ttr=new TableRow(this);
	
	    ttr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	    
	    ttr.setId(0); 
	     	
	    final TextView tb1=new TextView(this);
	    tb1.setGravity(Gravity.LEFT);
	    tb1.setTextSize(12);
	    tb1.setText(Html.fromHtml(tstr1));
	    tb1.setWidth(150);
	    tb1.setBackground(getResources().getDrawable(R.color.custom_button));
	    ttr.addView(tb1);
	        
	    final TextView tb2=new TextView(this);
	    tb2.setGravity(Gravity.CENTER);
	    tb2.setTextSize(12);
	    tb2.setText(Html.fromHtml(tstr2));
	    tb2.setWidth(150);
	    tb2.setBackground(getResources().getDrawable(R.color.custom_button));
	    ttr.addView(tb2);

	    final TextView tb3=new TextView(this);
	    tb3.setGravity(Gravity.CENTER);
	    tb3.setTextSize(12);
	    tb3.setText(Html.fromHtml(tstr3));
	    tb3.setWidth(150);
	    tb3.setBackground(getResources().getDrawable(R.color.custom_button));
	    ttr.addView(tb3);
	    
	    final TextView tb4=new TextView(this);
	    tb4.setGravity(Gravity.CENTER);
	    tb4.setTextSize(12);
	    tb4.setText(Html.fromHtml(tstr4));
	    tb4.setWidth(65);
	    tb4.setBackground(getResources().getDrawable(R.color.custom_button));
	    ttr.addView(tb4);
	    
	    final TextView tb5=new TextView(this);
	    tb5.setGravity(Gravity.CENTER);
	    tb5.setTextSize(12);
	    tb5.setText(Html.fromHtml(tstr5));
	    //tb5.setWidth(50);
	    tb5.setBackground(getResources().getDrawable(R.color.custom_button));
	    ttr.addView(tb5);
	    
	   	//ttr.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
	    
	   	tv.addView(ttr);

	   	
        while(i < x_length) {
        	
//        String str1 = "<font color=#0099ff>Nome</font> <br/> <font color=#ffffff> "+name[i]+" </font>";
//        String str2 = "<font color=#0099ff>Data</font> <br/> <font color=#ffffff> "+age[i]+" </font>";

        //String str1 = nome[i];
        //String str2 = dataini[i];
        //String str3 = datafin[i];
        
        final AtividadeOcorrencia ao = (AtividadeOcorrencia) lst.get(i);
        final String str1 = ao.getDescricaoProcedimentoOcorrencia();
        final String str2 = ao.getDtInicioAtividade();
        final String str3 = ao.getDtFimExecucaoAtividade();

        
    	final TableRow tr=new TableRow(this);

    	    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    	    tr.setId(i); 
    	     	
    	    final TextView b1=new TextView(this);
    		b1.setGravity(Gravity.LEFT);
    	    b1.setTextSize(12);
    	    b1.setText(Html.fromHtml(str1));
    	    b1.setWidth(150);
    	    tr.addView(b1);
    	        
    	    final TextView b2=new TextView(this);
    	    b2.setGravity(Gravity.CENTER);
 	        b2.setTextSize(12);
 	        b2.setText(Html.fromHtml(str2));
 	        b2.setWidth(150);
 	        tr.addView(b2);

	        final TextView b3=new TextView(this);
		    b3.setGravity(Gravity.CENTER);
	        b3.setTextSize(12);
	        b3.setText(Html.fromHtml(str3));
	        b3.setWidth(150);
	        tr.addView(b3);
 	        
	        final Button b4=new Button(this);
		    tb1.setGravity(Gravity.CENTER);
	        b4.setHeight(10);
	        b4.setTextSize(12);
	        b4.setText("...");
	        //b4.setWidth(70);
	        
	        b4.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View v) {
	        		AlertDialog.Builder alert = new AlertDialog.Builder(context);
	        		alert.setTitle("Observação");
	        		//alert.setMessage("");
	        		final EditText txtObservacao = new EditText(context);
	        		txtObservacao.setHint("Digite a observação...");
	        		alert.setView(txtObservacao);
	        		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        			public void onClick(DialogInterface dialog, int whichButton) {
	        				final String sObs = txtObservacao.getText().toString();
	        			}
	        		});
	        		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        			public void onClick(DialogInterface dialog, int whichButton) {
	        			}
	        		});
	        		alert.show();
	        	}
	        });
	        
	        tr.addView(b4);
 	        
	        final Switch mySwitch = new Switch(this);
		    tb1.setGravity(Gravity.CENTER);
	        //b5.setText(Html.fromHtml(tstr5));
	        mySwitch.setHeight(10);
	        mySwitch.setTextSize(12);
	        tr.addView(mySwitch);

    	   	  //tr.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
    	       tv.addView(tr);
    	       
    	     //set the switch to ON
    	       mySwitch.setChecked(false);
    	       //attach a listener to check for changes in state
    	       mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
						if (isChecked) {
							Toast.makeText(getApplicationContext(), "Contacte o Supervisor", Toast.LENGTH_SHORT).show();    	        	 
						//	Toast.makeText(getApplicationContext(), "Realizado", Toast.LENGTH_SHORT).show();    	        	 
						//} else {
						//	Toast.makeText(getApplicationContext(), "Não realizado", Toast.LENGTH_SHORT).show();    	        	 
						}
					}
    	       });
    	        
//    	       //check the current state before we display the screen
//    	       if(mySwitch.isChecked()){
//  	        	 Toast.makeText(getApplicationContext(), "Switch is currently ON", Toast.LENGTH_SHORT).show();    	        	 
//    	       }
//    	       else {
//    	        	 Toast.makeText(getApplicationContext(), "Switch is currently OFF", Toast.LENGTH_SHORT).show();    	        	 
//    	       }
//    	      }    	       
    	        
    	    final View vline1 = new View(this);
    	  vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
    	  vline1.setBackgroundColor(Color.LTGRAY);
    	  tv.addView(vline1);		
          tr.setOnClickListener(new View.OnClickListener() {
	    		@Override
	    		public void onClick(View v) {
	    			//Toast.makeText(getApplicationContext(), String.valueOf(tr.getId()), Toast.LENGTH_SHORT).show();   
	    		}
	    	});
        i++;
        }
    }
    
    private class AsyncListViewLoader extends AsyncTask<String, Void, List<AtividadeOcorrencia>> {
    	private final ProgressDialog dialog = new ProgressDialog(context);
    	
		@Override
		protected void onPostExecute(final List<AtividadeOcorrencia> result) {			
			super.onPostExecute(result);
			dialog.dismiss();
			atividadeOcorrenciaAdapter.setLstAtividadeOcorrencia(result);
			atividadeOcorrenciaAdapter.notifyDataSetChanged();
			mostra(result);
		}

		@Override
		protected void onPreExecute() {		
			super.onPreExecute();
			dialog.setTitle("Aguarde");
			dialog.setMessage("Carregando Atividades...");
			dialog.show();
		}

		@Override
		protected List<AtividadeOcorrencia> doInBackground(final String... params) {
			final List<AtividadeOcorrencia> result = new ArrayList<AtividadeOcorrencia>();
			try {
				final URL url = new URL(params[0]);
				final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				final InputStream is = conn.getInputStream();
				final byte[] b = new byte[1024];
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while (is.read(b) != -1) {
					baos.write(b);
				}
				final String JSONResp = new String(baos.toByteArray());
				final JSONObject jsonObj = new JSONObject(JSONResp);
				if (jsonObj.getJSONArray("atividadeOcorrenciaREST") != null) {
					final JSONArray arr = jsonObj.getJSONArray("atividadeOcorrenciaREST");
					for (int i = 0; i < arr.length(); i++) {
						result.add(convertAtividadeOcorrencia(arr.getJSONObject(i)));
					}
				}
				return result;
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			return null;
		}
		
		private AtividadeOcorrencia convertAtividadeOcorrencia(final JSONObject obj) throws JSONException {
			final String idAtividadeOcorrencia = obj.isNull("idAtividadeOcorrencia") ? "" : obj.getString("idAtividadeOcorrencia");
			final String idOcorrencia = obj.isNull("idOcorrencia") ? "" : obj.getString("idOcorrencia");
			final String dtInicioAtividade = obj.isNull("dtInicioAtividade") ? "" : obj.getString("dtInicioAtividade");
			final String dtFimExecucaoAtividade = obj.isNull("dtFimExecucaoAtividade") ? "" : obj.getString("dtFimExecucaoAtividade");
			final String observacao = obj.isNull("observacao") ? "" : obj.getString("observacao");
			final String descricaoStatus = obj.isNull("descricaoStatus") ? "" : obj.getString("descricaoStatus");
			final String descricaoProcedimentoOcorrencia = obj.isNull("descricaoProcedimentoOcorrencia") ? "" : obj.getString("descricaoProcedimentoOcorrencia");
			final String grResponsavel = obj.isNull("grResponsavel") ? "" : obj.getString("grResponsavel"); 
			return new AtividadeOcorrencia(idAtividadeOcorrencia, idOcorrencia, dtInicioAtividade, dtFimExecucaoAtividade, observacao, descricaoStatus, descricaoProcedimentoOcorrencia, grResponsavel);
		}
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.nicbrainmobile, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		final Intent intent = new Intent(getApplicationContext(), MainActivity.class);		
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("activity", activity);
		startActivity(intent);
		finish();
	}
	
}