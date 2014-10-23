package com.projeto.projetoexperiencias;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TitleDescriptionListAdapter extends BaseAdapter {

	private Activity context;
    private ArrayList<Map<String, String>> listItems;

    static class ViewHolder  {
        public TextView titleTextView;
        public TextView descTextView;
    }
    public TitleDescriptionListAdapter() {
		
	}
    public TitleDescriptionListAdapter(Activity context, ArrayList<Map<String, String>> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		
		View listItemView = convertView;
		if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.list_item_title_desc, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) listItemView.findViewById(R.id.listItemTitle);
            viewHolder.descTextView = (TextView) listItemView.findViewById(R.id.listItemDescription);
            listItemView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) listItemView.getTag();
        Map<String, String> titleDesc = (Map<String, String>) getItem(position);
        
        holder.titleTextView.setText(titleDesc.get("title"));
        holder.descTextView.setText(titleDesc.get("description"));

        return listItemView;
	}

}
