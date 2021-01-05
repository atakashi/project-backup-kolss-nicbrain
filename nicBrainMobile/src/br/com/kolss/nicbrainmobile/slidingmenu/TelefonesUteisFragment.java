package br.com.kolss.nicbrainmobile.slidingmenu;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.adapter.TelefoneUtilAdapter;
import br.com.kolss.nicbrainmobile.model.TelefoneUtil;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class TelefonesUteisFragment extends Fragment {

	private static final String URI_TELEFONES = UtilActivity.URI_NICBRAINREST + "/telefoneutil/telefones";

	private Context context = null;
	
	private ListView lstViewTelefonesUteis = null; 
	private TelefoneUtilAdapter telefoneUtilAdapter = null;
	
	public TelefonesUteisFragment() {
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        
        final View rootView = inflater.inflate(R.layout.fragment_telefones_uteis, container, false);
		final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header_telefones_uteis, lstViewTelefonesUteis, false);
		
		lstViewTelefonesUteis = (ListView) rootView.findViewById(R.id.lstViewTelefonesUteis);
		lstViewTelefonesUteis.addHeaderView(header);

		telefoneUtilAdapter = new TelefoneUtilAdapter(new ArrayList<TelefoneUtil>(), context);
        lstViewTelefonesUteis = (ListView) rootView.findViewById(R.id.lstViewTelefonesUteis);
        lstViewTelefonesUteis.setAdapter(telefoneUtilAdapter);
        
        (new AsyncListViewLoader()).execute(URI_TELEFONES);
        
        return rootView;
    }

    private class AsyncListViewLoader extends AsyncTask<String, Void, List<TelefoneUtil>> {
    	private final ProgressDialog dialog = new ProgressDialog(context);
    	
		@Override
		protected void onPostExecute(final List<TelefoneUtil> result) {			
			super.onPostExecute(result);
			dialog.dismiss();
			telefoneUtilAdapter.setLstTelefoneUtil(result);
			telefoneUtilAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {		
			super.onPreExecute();
			dialog.setTitle("Aguarde");
			dialog.setMessage("Carregando Telefones Úteis...");
			dialog.show();
		}

		@Override
		protected List<TelefoneUtil> doInBackground(final String... params) {
			final List<TelefoneUtil> result = new ArrayList<TelefoneUtil>();
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
				if (jsonObj.getJSONArray("telefoneUtil") != null) {
					final JSONArray arr = jsonObj.getJSONArray("telefoneUtil");
					for (int i = 0; i < arr.length(); i++) {
						result.add(convertTelefoneUtil(arr.getJSONObject(i)));
					}
				}
				return result;
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			return null;
		}
		
		private TelefoneUtil convertTelefoneUtil(final JSONObject obj) throws JSONException {
			final String idTelefoneUtil = obj.isNull("idTelefoneUtil") ? "" : obj.getString("idTelefoneUtil");
			final String nrTelefoneUtil = obj.isNull("nrTelefoneUtil") ? "" : obj.getString("nrTelefoneUtil");
			final String nomeTelefoneUtil = obj.isNull("nomeTelefoneUtil") ? "" : obj.getString("nomeTelefoneUtil");
			return new TelefoneUtil(idTelefoneUtil, nrTelefoneUtil, nomeTelefoneUtil);
		}
    }
    
}