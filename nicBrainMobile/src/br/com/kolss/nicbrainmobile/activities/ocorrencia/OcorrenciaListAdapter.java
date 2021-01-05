package br.com.kolss.nicbrainmobile.activities.ocorrencia;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import br.com.kolss.nicbrainmobile.R;

public class OcorrenciaListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<HeaderInfo> proc;

	public OcorrenciaListAdapter(Context context, ArrayList<HeaderInfo> proc) {
		this.context = context;
		this.proc = proc;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<DetailInfo> productList = proc.get(groupPosition)
				.getProcList();
		return productList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {

		DetailInfo detailInfo = (DetailInfo) getChild(groupPosition,
				childPosition);
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.activity_ocorrencia_child_row, null);
		}

		TextView sequence = (TextView) view.findViewById(R.id.sequence);
		sequence.setText(detailInfo.getSequence().trim() + ") ");
		TextView childItem = (TextView) view.findViewById(R.id.childItem);
		childItem.setText(detailInfo.getName().trim());

		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		ArrayList<DetailInfo> productList = proc.get(groupPosition)
				.getProcList();
		return productList.size();

	}

	@Override
	public Object getGroup(int groupPosition) {
		return proc.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return proc.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {

		HeaderInfo headerInfo = (HeaderInfo) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.activity_ocorrencia_group_heading, null);
		}

		TextView heading = (TextView) view.findViewById(R.id.heading);
		heading.setText(headerInfo.getName().trim());

		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}