package com.projeto.projetoexperiencias;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

	private static String dadosData = "";
	private static String horarioRegistrado = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);

		Intent intent = getIntent();
		dadosData = intent.getStringExtra("DADOS_DATA");
		horarioRegistrado = intent.getStringExtra("HORARIO_REGISTRADO");
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
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
			
			View rootView = inflater.inflate(R.layout.fragment_resultado,
					container, false);
			
			TextView tv = (TextView) rootView.findViewById(R.id.resultHorario);
			tv.setText("Ponto registrado!");
			
			TextView tvData = (TextView) rootView.findViewById(R.id.dadosData);
			tvData.setText(dadosData);
			
			TextView tvHorarioRegistrado = (TextView) rootView.findViewById(R.id.horarioRegistrado);
			tvHorarioRegistrado.setText(horarioRegistrado);
			
			
			DBAdapter.init(getActivity());
			DBAdapter.getAllHorariosData();
			//Toast.makeText(getActivity(), "Ok! Aqui apresento o hor�rio batido!", Toast.LENGTH_LONG).show();
			
			
			return rootView;
		}
	}
}
