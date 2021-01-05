package br.com.kolss.nicbrainmobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.model.PendenciaOcorrencia;

/**
 * Adapter class to show the PendenciaOcorrencia
 * 
 * @author LuisCM
 *
 */
public class PendenciaOcorrenciaAdapter extends ArrayAdapter<PendenciaOcorrencia> {
	
	private List<PendenciaOcorrencia> lstPendenciaOcorrencia = null;
	private Context context = null;
	
	public PendenciaOcorrenciaAdapter(final List<PendenciaOcorrencia> lstPendenciaOcorrencia, final Context ctx) {
		super(ctx, android.R.layout.simple_list_item_2, lstPendenciaOcorrencia);
		this.lstPendenciaOcorrencia = lstPendenciaOcorrencia;
		this.context = ctx;		
	}
	
	public int getCount() {
		if (lstPendenciaOcorrencia != null) {
			return lstPendenciaOcorrencia.size();
		}
		return 0;
	}

	public PendenciaOcorrencia getItem(final int position) {
		if (lstPendenciaOcorrencia != null) {
			return lstPendenciaOcorrencia.get(position);
		}
		return null;
	}

	public long getItemId(final int position) {
		if (lstPendenciaOcorrencia != null) {
			return lstPendenciaOcorrencia.get(position).hashCode();
		}
		return 0;
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {
		PendenciaOcorrenciaViewHolder viewHolder = null;
		if (view == null) {
			final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_row_atividade_ocorrencia, parent, false);
			viewHolder = new PendenciaOcorrenciaViewHolder();
//			viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
//			viewHolder.txtDtIni = (TextView) view.findViewById(R.id.txtDtIni);
//			viewHolder.txtDtFin = (TextView) view.findViewById(R.id.txtDtFin);
			
			viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtNroTelefoneUtil);
			viewHolder.txtDtIni = (TextView) view.findViewById(R.id.txtNomeTelefoneUtil);
			
			view.setTag(viewHolder);
		} else {
			viewHolder = (PendenciaOcorrenciaViewHolder) view.getTag();
		}
		final PendenciaOcorrencia pendenciaOcorrencia = lstPendenciaOcorrencia.get(position);
		if (pendenciaOcorrencia != null) {
//			viewHolder.txtDescricao.setText(pendenciaOcorrencia.getDescricao());
//			viewHolder.txtDtIni.setText(pendenciaOcorrencia.getDtInicio());
//			viewHolder.txtDtFin.setText(pendenciaOcorrencia.getDtFimExecucao());
			
			viewHolder.txtDescricao.setText(pendenciaOcorrencia.getDescricao());
			viewHolder.txtDtIni.setText(pendenciaOcorrencia.getDtInicio());
			
		}
		return view;
	}
	
	public List<PendenciaOcorrencia> getLstPendenciaOcorrencia() {
		return lstPendenciaOcorrencia;
	}

	public void setLstPendenciaOcorrencia(final List<PendenciaOcorrencia> lstPendenciaOcorrencia) {
		this.lstPendenciaOcorrencia = lstPendenciaOcorrencia;
	}

	static class PendenciaOcorrenciaViewHolder {
//		TextView txtDescricao = null;
//		TextView txtDtIni = null;
//		TextView txtDtFin = null;
		
		TextView txtDescricao = null;
		TextView txtDtIni = null;
	}
	
}