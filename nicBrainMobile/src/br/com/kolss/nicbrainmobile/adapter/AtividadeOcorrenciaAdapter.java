package br.com.kolss.nicbrainmobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.model.AtividadeOcorrencia;

/**
 * Adapter class to show the AtividadeOcorrencia
 * 
 * @author LuisCM
 *
 */
public class AtividadeOcorrenciaAdapter extends ArrayAdapter<AtividadeOcorrencia> {
	
	private List<AtividadeOcorrencia> lstAtividadeOcorrencia = null;
	private Context context = null;
	
	public AtividadeOcorrenciaAdapter(final List<AtividadeOcorrencia> lstAtividadeOcorrencia, final Context ctx) {
		super(ctx, android.R.layout.simple_list_item_2, lstAtividadeOcorrencia);
		this.lstAtividadeOcorrencia = lstAtividadeOcorrencia;
		this.context = ctx;		
	}
	
	public int getCount() {
		if (lstAtividadeOcorrencia != null) {
			return lstAtividadeOcorrencia.size();
		}
		return 0;
	}

	public AtividadeOcorrencia getItem(final int position) {
		if (lstAtividadeOcorrencia != null) {
			return lstAtividadeOcorrencia.get(position);
		}
		return null;
	}

	public long getItemId(final int position) {
		if (lstAtividadeOcorrencia != null) {
			return lstAtividadeOcorrencia.get(position).hashCode();
		}
		return 0;
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {
		AtividadeOcorrenciaViewHolder viewHolder = null;
		if (view == null) {
			final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_row_atividade_ocorrencia, parent, false);
			viewHolder = new AtividadeOcorrenciaViewHolder();
//			viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
//			viewHolder.txtDtIni = (TextView) view.findViewById(R.id.txtDtIni);
//			viewHolder.txtDtFin = (TextView) view.findViewById(R.id.txtDtFin);
			
			viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtNroTelefoneUtil);
			viewHolder.txtDtIni = (TextView) view.findViewById(R.id.txtNomeTelefoneUtil);
			
			view.setTag(viewHolder);
		} else {
			viewHolder = (AtividadeOcorrenciaViewHolder) view.getTag();
		}
		final AtividadeOcorrencia atividadeOcorrencia = lstAtividadeOcorrencia.get(position);
		if (atividadeOcorrencia != null) {
//			viewHolder.txtDescricao.setText(atividadeOcorrencia.getDescricaoProcedimentoOcorrencia());
//			viewHolder.txtDtIni.setText(atividadeOcorrencia.getDtInicioAtividade());
//			viewHolder.txtDtFin.setText(atividadeOcorrencia.getDtFimExecucaoAtividade());
			
			viewHolder.txtDescricao.setText(atividadeOcorrencia.getDescricaoProcedimentoOcorrencia());
			viewHolder.txtDtIni.setText(atividadeOcorrencia.getDtInicioAtividade());
			
		}
		return view;
	}
	
	public List<AtividadeOcorrencia> getLstAtividadeOcorrencia() {
		return lstAtividadeOcorrencia;
	}

	public void setLstAtividadeOcorrencia(final List<AtividadeOcorrencia> lstAtividadeOcorrencia) {
		this.lstAtividadeOcorrencia = lstAtividadeOcorrencia;
	}

	static class AtividadeOcorrenciaViewHolder {
//		TextView txtDescricao = null;
//		TextView txtDtIni = null;
//		TextView txtDtFin = null;
		
		TextView txtDescricao = null;
		TextView txtDtIni = null;
	}
	
}