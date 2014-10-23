package com.projeto.projetoexperiencias;

import java.util.Map;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class CustomLayoutListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_layout_list_fragment);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_layout_list, menu);
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
	public static class PlaceholderFragment extends ListFragment {

		public PlaceholderFragment() {
		}

		private TitleDescriptionListAdapter adapter;

	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        //TODO: CONSULTAR NA BASE AS HORAS
	        DBAdapter.init(getActivity());

	        //adapter = new TitleDescriptionListAdapter(getActivity(), AppCommons.createItemsList());
	        adapter = new TitleDescriptionListAdapter(getActivity(), DBAdapter.getAllHorariosDataMap());
	        
	        setListAdapter(adapter);
	    }

	    @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        Map<String, String> item = (Map<String, String>) adapter.getItem(position);
	        Toast.makeText(getActivity(), item.get("description"), Toast.LENGTH_SHORT).show();
	    }
	}
}
