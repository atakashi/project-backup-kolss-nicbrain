package br.com.kolss.nicbrainmobile.slidingmenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.kolss.nicbrainmobile.R;
import br.com.kolss.nicbrainmobile.activities.ActivityObservacao;

public class OcorrenciasFragment extends Fragment {
	
	private Intent intent = null;
	private Button btnBriga = null;
	private Button btnAlarme = null;
	private Button btnFogo = null;
	private Button btnTumulto = null;
	private Button btnRoubo = null;
	private Button btnAgua = null;
	private Button btnFerido = null;
	
	public OcorrenciasFragment() {
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ocorrencias, container, false);
        
        btnBriga = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_briga);
        btnBriga.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "BRIGA");
				startActivity(intent);
        	}
        });

        btnAlarme = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_alarme);
        btnAlarme.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "ALARME");
				startActivity(intent);
        	}
        });

        btnFogo = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_fogo);
        btnFogo.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "FOGO");
				startActivity(intent);
        	}
        });

        btnTumulto = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_tumulto);
        btnTumulto.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "TUMULTO");
				startActivity(intent);
        	}
        });

        
        btnRoubo = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_roubo);
        btnRoubo.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "ROUBO");
				startActivity(intent);
        	}
        });

        btnAgua = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_agua);
        btnAgua.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "AGUA");
				startActivity(intent);
        	}
        });

        btnFerido = (Button) rootView.findViewById(R.id.activity_nicbrainmobile_btn_ferido);
        btnFerido.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
				intent = new Intent(rootView.getContext(), ActivityObservacao.class);
				intent.putExtra("activity", "FERIDO");
				startActivity(intent);
        	}
        });
        
        return rootView;
    }
	
}
