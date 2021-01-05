package br.com.kolss.nicbrainmobile.activities.ocorrencia;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.slidingmenu.MainActivity;

/**
 * @author LuisCM
 */

//public class ActivityOcorrencia extends ActivityDashBoard implements OnClickListener {
public class ActivityOcorrencia extends FragmentActivity implements OnClickListener {

	private LinkedHashMap<String, HeaderInfo> myOco = new LinkedHashMap<String, HeaderInfo>();
	private ArrayList<HeaderInfo> xptList = new ArrayList<HeaderInfo>();

	private OcorrenciaListAdapter listAdapter;
	private ExpandableListView myList;

	protected Bundle b = null;
	protected String activity = null;
	protected Integer idOcorrencia = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ocorrencia);

		b = getIntent().getExtras();
		if (b != null) {
			activity = b.getString("activity");
			idOcorrencia = Integer.parseInt(b.getString("response"));
		}    	
		
		loadData();

		myList = (ExpandableListView) findViewById(R.id.myList);
		listAdapter = new OcorrenciaListAdapter(ActivityOcorrencia.this, xptList);
		myList.setAdapter(listAdapter);

		expandAll();

		Button add = (Button) findViewById(R.id.ok);
		add.setOnClickListener(this);

		myList.setOnChildClickListener(myListItemClicked);
		myList.setOnGroupClickListener(myListGroupClicked);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ok:
				onBackPressed();
				break;
		}
	}

	private void expandAll() {
		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++) {
			myList.expandGroup(i);
		}
	}

	private void loadData() {
		String act = null;
		if (activity == null) {
			act = "ERROR";
		}
		act = activity.toUpperCase();
		
		if (act.equals("BRIGA")) {
			addProc("Briga", "Chame Atencao");
			addProc("Briga", "Tente Apartar");
			addProc("Briga", "Chame a Policia");
		}
		else if (act.equals("ALARME")) {
			addProc("Alarme", "Chame o Supervisor");
		}
		else if (act.equals("FOGO")) {
			addProc("Fogo", "Apage o Fogo");
			addProc("Fogo", "Chame o Bombeiro");
		}
		else if (act.equals("TUMULTO")) {
			addProc("Tumulto", "Chame Ajuda");
		}
		else if (act.equals("ROUBO")) {
			addProc("Roubo", "Chame Policia");
		}
		else if (act.equals("AGUA")) {
			addProc("Agua", "Chame Sabesp");
		}
		else if (act.equals("FERIDO")) {
			addProc("Ferido", "Tente ajudar");
			addProc("Ferido", "Primeiros socorros");
			addProc("Ferido", "Chame o Resgate");
		}
	}

	private OnChildClickListener myListItemClicked = new OnChildClickListener() {

		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
//			HeaderInfo headerInfo = xptList.get(groupPosition);
//			DetailInfo detailInfo = headerInfo.getProcList().get(childPosition);
//			Toast.makeText(
//					getBaseContext(),
//					"Clicado no Detalhe " + headerInfo.getName() + "/"
//							+ detailInfo.getName(), Toast.LENGTH_LONG).show();
			return false;
		}
	};

	private OnGroupClickListener myListGroupClicked = new OnGroupClickListener() {

		public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
//			HeaderInfo headerInfo = xptList.get(groupPosition);
//			Toast.makeText(getBaseContext(),
//					"Filho do Header " + headerInfo.getName(),
//					Toast.LENGTH_LONG).show();
			return false;
		}
	};

	private int addProc(String oco, String proc) {
		int groupPosition = 0;
		HeaderInfo headerInfo = myOco.get(oco);
		if (headerInfo == null) {
			headerInfo = new HeaderInfo();
			headerInfo.setName(oco);
			myOco.put(oco, headerInfo);
			xptList.add(headerInfo);
		}
		ArrayList<DetailInfo> procList = headerInfo.getProcList();
		int listSize = procList.size();
		listSize++;
		DetailInfo detailInfo = new DetailInfo();
		detailInfo.setSequence(String.valueOf(listSize));
		detailInfo.setName(proc);
		procList.add(detailInfo);
		headerInfo.setProcList(procList);
		groupPosition = xptList.indexOf(headerInfo);
		return groupPosition;
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