package com.projeto.projetoexperiencias;

import java.util.List;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.projeto.projetoexperiencias.MESSAGE";
	public final static String TAG = "MainActivity"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBAdapter.init(getApplication());
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		Log.i(TAG, "Passando no onCreate!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void limpaBase(View view){
		DBAdapter.limpaBase();
		Log.i(TAG, "Base de dados zerada!");
	}
	
	public void prossegue(View view){
		
		DateTime in = new DateTime();
		String horarioRegistrado = in.toString();
		//2014-10-20T09:06:35.980-02:00
		horarioRegistrado = horarioRegistrado.substring(11, 19);
		String dadosData = in.dayOfWeek().getAsText() + ", " + in.getDayOfMonth() + " de " + in.monthOfYear().getAsText() + " de " + in.getYear();
		TextView tv = (TextView) findViewById(R.id.dadosData);
		tv.setText(dadosData);
		
		//TODO: grava dados na base
		//buttonEntradaManha
		PontoVo ponto = new PontoVo();
		ponto.setHorario(in.toString());//TODO: se precisar, coloca o 'horarioRegistrado'
		
		Button button = (Button) view;
		switch (button.getHint().toString()) {
			case "E_M":
				ponto.setTipoEntrada("1");
				Log.i(TAG, "Cadastrando ponto: Entrada manhã!");
				break;
			case "S_M":
				ponto.setTipoEntrada("2");
				Log.i(TAG, "Cadastrando ponto: Saída manhã!");
				break;
			case "E_T":
				ponto.setTipoEntrada("3");
				Log.i(TAG, "Cadastrando ponto: Entrada tarde!");
				break;
			case "S_T":
				ponto.setTipoEntrada("4");
				Log.i(TAG, "Cadastrando ponto: Saída tarde!");
				break;
	
			default:
				break;
		}
		
		DBAdapter.addHorario(ponto);
		
		Intent intent = new Intent(this, ResultadoActivity.class);
		intent.putExtra("DADOS_DATA", dadosData);
		intent.putExtra("HORARIO_REGISTRADO", horarioRegistrado);
		startActivity(intent);
		
		
		//Toast.makeText(this, "Horário batido: '" + horarioRegistrado + "'", Toast.LENGTH_LONG).show();
		//AnalogClock clock = (AnalogClock) findViewById(R.id.analogClock1);
		//clock.get
	}
	
	
	public void mostraResumo(View view){
		//DBAdapter.limpaBase();
		//Intent intent = new Intent(this, RelatorioDiaActivity.class);
		Intent intent = new Intent(this, CustomLayoutListActivity.class);
		startActivity(intent);
		/*
		getFragmentManager().beginTransaction()
        .replace(R.id.container, new CustomLayoutListFragment())
        .commit();
        */
		//TODO: depois, fazer a consulta no BD
		Log.i(TAG, "Mostrando o resumo!");
	}
	
	public void fechaApp(View view){
		Log.i(TAG, "Fechando Aplicação!");
		this.finish();
	}
	//RelatorioDiaActivity
	//button3

	public void verPontoHoje(View view){
		Intent intent = new Intent(this, PontosHojeActivity.class);
		startActivity(intent);
		Log.i(TAG, "Mostrando os pontos batidos de hj!");
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			Log.i(TAG, "Passando no onCreateView (do fragmento)!");
			List<String> pontosBatidos = getPontosBatidos();
			for (String string : pontosBatidos) {
				switch (string) {
				case "1":
					rootView.findViewById(R.id.buttonEntradaManha).setEnabled(false);
					break;
				case "2":
					rootView.findViewById(R.id.buttonSaidaManha).setEnabled(false);
					break;
				case "3":
					rootView.findViewById(R.id.buttonEntradaTarde).setEnabled(false);
					break;
				case "4":
					rootView.findViewById(R.id.buttonSaidaTarde).setEnabled(false);
					break;
				default:
					break;
				}
			}
			return rootView;
		}
		
		public List<String> getPontosBatidos(){
			DateTime in = new DateTime();
			String dia = in.toString();
			dia = dia.substring(0, 10);
			
			DBAdapter.init(getActivity());
			List<String> pontosBatidosDia = DBAdapter.verificaPontosBatidosDia(dia);
			
			return pontosBatidosDia;
		}
		
	}
}
