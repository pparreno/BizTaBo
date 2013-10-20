package biztabo.tools;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import biztabo.data_objects.SearchedItem;

import com.example.biztabo.R;

public class SearchListAdapter extends ArrayAdapter<SearchedItem> implements Filterable {

	private Context context;
	private int layoutResourceId;
	private List<SearchedItem> items;
	private List<SearchedItem> original_items;		// used to refresh the search list
	public boolean no_results = false;
	
	public float price_from;
	public float price_to = 1000000;
	
	
	public SearchListAdapter(Context context, int layoutResourceId, List<SearchedItem> items)
	{		
		super(context, layoutResourceId, items);
		
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		
		// Find a way to make the items sorted accordingly
		
		Collections.sort(items, SearchedItem.ItemNameComparator);
		
		this.setItems(items);
		this.original_items = items;
		
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(layoutResourceId, parent, false);
			
			System.out.println("Position: " + position);
			
			ImageView img = (ImageView) row.findViewById(R.id.item_icon);
			TextView name = (TextView) row.findViewById(R.id.item_name);
			TextView type = (TextView) row.findViewById(R.id.item_type);
			ImageView rating = (ImageView) row.findViewById(R.id.item_rating);
			
			SearchedItem item = getItems().get(position);
			
			System.out.println("Item icon: "+item.getItem_icon());
			img.setImageResource(item.getItem_icon());
			System.out.println("Item name: "+item.getItem_name());
			name.setText(item.getItem_name());
			type.setText(item.getItem_type());
			rating.setImageResource(item.getItem_rating());
	
		
		return row;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public Filter getFilter()
	{
		Filter filter = new Filter(){

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				FilterResults results = new FilterResults();
				ArrayList<SearchedItem> FilteredItems = new ArrayList<SearchedItem>();
				
				// Using the constraint, find the item whose name contains such char sequence
				
				constraint = constraint.toString().toLowerCase();
				
				System.out.println("Constraint: " + constraint);
				
				
				for( SearchedItem sItem : getItems() )
				{
					if(sItem.getItem_name().toLowerCase().contains(constraint))
					{
						if(sItem.getItem_price() >= price_from && sItem.getItem_price() < price_to)
						{
							System.out.println("Item added: " + sItem.getItem_name());
							FilteredItems.add(sItem);
						}
					}
				}
				
				System.out.println("Item size:" + FilteredItems.size());
				
				results.count = FilteredItems.size();
				results.values = FilteredItems;
				
				
				
				return results;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				System.out.println("Number of results: " + results.count);
				
					setItems((List<SearchedItem>) results.values);
					notifyDataSetChanged();
					
					if(results.count < 1)
					{
						no_results = true;
					} else {
						no_results = false;
					}
			}
		};
		
		
		return filter;
	}
	
	@Override
	public int getCount()
	{
		return getItems().size();
	}
	
	public void refreshSearchList()
	{
		setItems(original_items);
		//notifyDataSetChanged();
	}

	public List<SearchedItem> getItems() {
		return items;
	}

	public void setItems(List<SearchedItem> items) {
		this.items = items;
	}
	
	public void setPriceRange(float price_from, float price_to)
	{
		this.price_from = price_from;
		this.price_to = price_to;
	}
}

