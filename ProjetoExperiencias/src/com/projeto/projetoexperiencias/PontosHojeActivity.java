package com.projeto.projetoexperiencias;

import java.util.List;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class PontosHojeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pontos_hoje);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pontos_hoje, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pontos_hoje,
					container, false);
			
			DateTime in = new DateTime();
			String dia = in.toString();
			dia = dia.substring(0, 10);
			
			DBAdapter.init(getActivity());
			List<String> pontosBatidosDia = DBAdapter.verificaPontosBatidosDia(dia);
			String texto = "Batidos: ";
			for (String string : pontosBatidosDia) {
				String virgula = ",";
				if(pontosBatidosDia.indexOf(string)==(pontosBatidosDia.size()-1)){
					virgula = "";
				}
				texto += string + virgula;
			}
			//textViewPontosHj
			TextView tv = (TextView) rootView.findViewById(R.id.textViewPontosHj);
			tv.setText(texto);
			
			return rootView;
		}
	}
}
