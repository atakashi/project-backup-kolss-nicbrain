package br.com.kolss.nicbrainmobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.model.TelefoneUtil;

/**
 * Adapter class to show the TelefoneUtil
 * 
 * @author LuisCM
 *
 */
public class TelefoneUtilAdapter extends ArrayAdapter<TelefoneUtil> {
	
	private List<TelefoneUtil> lstTelefoneUtil = null;
	private Context context = null;
	
	public TelefoneUtilAdapter(final List<TelefoneUtil> lstTelefoneUtil, final Context ctx) {
		super(ctx, android.R.layout.simple_list_item_2, lstTelefoneUtil);
		this.lstTelefoneUtil = lstTelefoneUtil;
		this.context = ctx;		
	}
	
	public int getCount() {
		if (lstTelefoneUtil != null) {
			return lstTelefoneUtil.size();
		}
		return 0;
	}

	public TelefoneUtil getItem(final int position) {
		if (lstTelefoneUtil != null) {
			return lstTelefoneUtil.get(position);
		}
		return null;
	}

	public long getItemId(final int position) {
		if (lstTelefoneUtil != null) {
			return lstTelefoneUtil.get(position).hashCode();
		}
		return 0;
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {
		TelefoneUtilViewHolder viewHolder = null;
		if (view == null) {
			final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_row_telefones_uteis, parent, false);
			viewHolder = new TelefoneUtilViewHolder();
			viewHolder.txtNroTelefoneUtil = (TextView) view.findViewById(R.id.txtNroTelefoneUtil);
			viewHolder.txtNomeTelefoneUtil = (TextView) view.findViewById(R.id.txtNomeTelefoneUtil);
			view.setTag(viewHolder);
		} else {
			viewHolder = (TelefoneUtilViewHolder) view.getTag();
		}
		final TelefoneUtil telefoneUtil = lstTelefoneUtil.get(position);
		if (telefoneUtil != null) {
			viewHolder.txtNroTelefoneUtil.setText(telefoneUtil.getNrTelefoneUtil());
			viewHolder.txtNomeTelefoneUtil.setText(telefoneUtil.getNomeTelefoneUtil());
		}
		return view;
	}
	
	public List<TelefoneUtil> getLstTelefoneUtil() {
		return lstTelefoneUtil;
	}

	public void setLstTelefoneUtil(final List<TelefoneUtil> lstTelefoneUtil) {
		this.lstTelefoneUtil = lstTelefoneUtil;
	}

	static class TelefoneUtilViewHolder {
		TextView txtNroTelefoneUtil = null;
		TextView txtNomeTelefoneUtil = null;
	}
	
}