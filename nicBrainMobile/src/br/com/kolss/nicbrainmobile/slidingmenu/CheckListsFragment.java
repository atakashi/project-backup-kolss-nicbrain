package br.com.kolss.nicbrainmobile.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.kolss.nicbrainmobile.R;

public class CheckListsFragment extends Fragment {
	
	public CheckListsFragment() {
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_check_lists, container, false);
        return rootView;
    }
	
}