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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.adapter.PendenciaOcorrenciaAdapter;
import br.com.kolss.nicbrainmobile.model.PendenciaOcorrencia;
import br.com.kolss.nicbrainmobile.util.activity.UtilActivity;

public class PendenciasOcorrenciasFragment extends Fragment {

	private static final String URI_PENDENCIA_OCORRENCIA = UtilActivity.URI_NICBRAINREST
			+ "/pendenciaocorrencia/lista";

	private Context context = null;

	private ListView lstViewPendenciaOcorrencia = null;
	private PendenciaOcorrenciaAdapter pendenciaOcorrenciaAdapter = null;

	protected Bundle bundle = null;

	View rootView = null;

	public PendenciasOcorrenciasFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = this.getActivity();
		rootView = inflater.inflate(R.layout.fragment_pendencias_ocorrencias,
				container, false);

		pendenciaOcorrenciaAdapter = new PendenciaOcorrenciaAdapter(
				new ArrayList<PendenciaOcorrencia>(), context);

		// final String uri = new StringBuilder(URI_PENDENCIA_OCORRENCIA)
		// .append(activity).append("/")
		// .append(idOcorrencia).toString();
		(new AsyncListViewLoader()).execute(URI_PENDENCIA_OCORRENCIA);

		return rootView;
	}

	private void mostra(final List<PendenciaOcorrencia> result) {
		// final List<PendenciaOcorrencia> lst =
		// pendenciaOcorrenciaAdapter.getLstPendenciaOcorrencia();
		final List<PendenciaOcorrencia> lst = result;

		TableLayout tv = (TableLayout) rootView.findViewById(R.id.table1);
		tv.removeAllViewsInLayout();

		int x_length = lst.size();
		int i = 0;

		String tstr1 = "Local";
		String tstr2 = "Evento";
		String tstr3 = "Classificação";
		String tstr4 = "Nivél Gravidade";
		String tstr5 = "Descrição";
		String tstr6 = "Data Início";
		String tstr7 = "Status";

		final TableRow ttr = new TableRow(tv.getContext());
		ttr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		ttr.setId(0);

		final TextView tb1 = new TextView(tv.getContext());
		tb1.setGravity(Gravity.LEFT);
		tb1.setTextSize(12);
		tb1.setText(Html.fromHtml(tstr1));
		tb1.setWidth(100);
		tb1.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb1);
		
		final TextView tb2 = new TextView(tv.getContext());
		tb2.setGravity(Gravity.LEFT);
		tb2.setTextSize(12);
		tb2.setText(Html.fromHtml(tstr2));
		tb2.setWidth(100);
		tb2.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb2);

		final TextView tb3 = new TextView(tv.getContext());
		tb3.setGravity(Gravity.LEFT);
		tb3.setTextSize(12);
		tb3.setText(Html.fromHtml(tstr3));
		tb3.setWidth(100);
		tb3.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb3);

		final TextView tb4 = new TextView(tv.getContext());
		tb4.setGravity(Gravity.CENTER);
		tb4.setTextSize(12);
		tb4.setText(Html.fromHtml(tstr4));
		tb4.setWidth(100);
		tb4.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb4);

		final TextView tb5 = new TextView(tv.getContext());
		tb5.setGravity(Gravity.LEFT);
		tb5.setTextSize(12);
		tb5.setText(Html.fromHtml(tstr5));
		tb5.setWidth(150);
		tb5.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb5);

		final TextView tb6 = new TextView(tv.getContext());
		tb6.setGravity(Gravity.CENTER);
		tb6.setTextSize(12);
		tb6.setText(Html.fromHtml(tstr5));
		tb6.setWidth(120);
		tb6.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb6);

		final TextView tb7 = new TextView(tv.getContext());
		tb7.setGravity(Gravity.LEFT);
		tb7.setTextSize(12);
		tb7.setText(Html.fromHtml(tstr6));
		tb7.setWidth(130);
		tb7.setBackground(getResources().getDrawable(R.color.custom_button));
		ttr.addView(tb7);

		// ttr.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

		tv.addView(ttr);

		while (i < x_length) {
			final PendenciaOcorrencia po = (PendenciaOcorrencia) lst.get(i);
			final String str1 = po.getNomeLocal();
			final String str2 = po.getNomeEvento();
			final String str3 = po.getNomeClassificacaoOcorrencia();
			final String str4 = po.getNivelGravidade();
			final String str5 = po.getDescricao();
			final String str6 = po.getDtInicio();
			final String str7 = po.getDescricaoStatus();

			final TableRow tr = new TableRow(tv.getContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			tr.setId(i);

			final TextView b1 = new TextView(tr.getContext());
			b1.setGravity(Gravity.LEFT);
			b1.setTextSize(12);
			b1.setText(Html.fromHtml(str1));
			b1.setWidth(100);
			tr.addView(b1);
			
			final TextView b2 = new TextView(tr.getContext());
			b2.setGravity(Gravity.LEFT);
			b2.setTextSize(12);
			b2.setText(Html.fromHtml(str2));
			b2.setWidth(100);
			tr.addView(b2);

			final TextView b3 = new TextView(tr.getContext());
			b3.setGravity(Gravity.LEFT);
			b3.setTextSize(12);
			b3.setText(Html.fromHtml(str3));
			b3.setWidth(100);
			tr.addView(b3);

			final TextView b4 = new TextView(tr.getContext());
			b4.setGravity(Gravity.CENTER);
			b4.setTextSize(12);
			b4.setText(Html.fromHtml(str4));
			b4.setWidth(100);
			tr.addView(b4);

			final TextView b5 = new TextView(tr.getContext());
			b5.setGravity(Gravity.LEFT);
			b5.setTextSize(12);
			b5.setText(Html.fromHtml(str5));
			b5.setWidth(150);
			tr.addView(b5);

			final TextView b6 = new TextView(tr.getContext());
			b6.setGravity(Gravity.CENTER);
			b6.setTextSize(12);
			b6.setText(Html.fromHtml(str6));
			b6.setWidth(120);
			tr.addView(b6);

			final TextView b7 = new TextView(tr.getContext());
			b7.setGravity(Gravity.LEFT);
			b7.setTextSize(12);
			b7.setText(Html.fromHtml(str7));
			b7.setWidth(130);
			tr.addView(b7);

			tv.addView(tr);
			final View vline1 = new View(tr.getContext());
			vline1.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT, 1));
			vline1.setBackgroundColor(Color.LTGRAY);
			tv.addView(vline1);
			tr.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Toast.makeText(getApplicationContext(),
					// String.valueOf(tr.getId()), Toast.LENGTH_SHORT).show();
				}
			});
			i++;
		}
	}

	private class AsyncListViewLoader extends
			AsyncTask<String, Void, List<PendenciaOcorrencia>> {
		private final ProgressDialog dialog = new ProgressDialog(context);

		@Override
		protected void onPostExecute(final List<PendenciaOcorrencia> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			pendenciaOcorrenciaAdapter.setLstPendenciaOcorrencia(result);
			pendenciaOcorrenciaAdapter.notifyDataSetChanged();
			mostra(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setTitle("Aguarde");
			dialog.setMessage("Carregando Pendências de Ocorrências...");
			dialog.show();
		}

		@Override
		protected List<PendenciaOcorrencia> doInBackground(
				final String... params) {
			final List<PendenciaOcorrencia> result = new ArrayList<PendenciaOcorrencia>();
			try {
				final URL url = new URL(params[0]);
				final HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
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
				if (jsonObj.getJSONArray("pendenciaOcorrenciaREST") != null) {
					final JSONArray arr = jsonObj
							.getJSONArray("pendenciaOcorrenciaREST");
					for (int i = 0; i < arr.length(); i++) {
						result.add(convertPendenciaOcorrencia(arr
								.getJSONObject(i)));
					}
				}
				return result;
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			return null;
		}

		private PendenciaOcorrencia convertPendenciaOcorrencia(
				final JSONObject obj) throws JSONException {
			final String nomeLocal = obj
					.isNull("nomeLocal") ? "" : obj
					.getString("nomeLocal");
			final String nomeEvento = obj
					.isNull("nomeEvento") ? "" : obj
					.getString("nomeEvento");
			final String nomeClassificacaoOcorrencia = obj
					.isNull("nomeClassificacaoOcorrencia") ? "" : obj
					.getString("nomeClassificacaoOcorrencia");
			final String nivelGravidade = obj.isNull("nivelGravidade") ? ""
					: obj.getString("nivelGravidade");
			final String descricao = obj.isNull("descricao") ? "" : obj
					.getString("descricao");
			final String dtInicio = obj.isNull("dtInicio") ? "" : obj
					.getString("dtInicio");
			final String descricaoStatus = obj.isNull("descricaoStatus") ? ""
					: obj.getString("descricaoStatus");
			return new PendenciaOcorrencia(nomeLocal, nomeEvento, nomeClassificacaoOcorrencia,
					nivelGravidade, descricao, dtInicio, descricaoStatus);
		}
	}

}