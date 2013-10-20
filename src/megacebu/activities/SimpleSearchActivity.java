package megacebu.activities;

import java.util.ArrayList;
import java.util.List;

import megacebu.data_objects.SearchedItem;
import megacebu.searchview.R;
import megacebu.tools.SearchListAdapter;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SimpleSearchActivity extends Activity {

	private SearchListAdapter adapter;
	private ListView search_list;
	private View advanced_search;
	private TextView no_res_text;
	private TextView price_from_text;
	private TextView price_to_text;
	private List<SearchedItem> items;
	
	@SuppressLint({ "Recycle", "CutPasteId" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_search);
		
		search_list = (ListView) findViewById(R.id.search_list_view);
		advanced_search = (View) findViewById(R.id.advanced_search_settings);
		no_res_text = (TextView) findViewById(R.id.no_search_result_text);
		price_from_text = (TextView) findViewById(R.id.from_price);
		price_to_text = (TextView) findViewById(R.id.to_price);
		
		
		// These arrays are holders of the dummy data found in DummyData.xml
			String[] hotel_names = getResources().getStringArray(R.array.hotel_names);
			TypedArray hotel_icons = getResources().obtainTypedArray(R.array.hotel_icons);
			String[] hotel_ratings = getResources().getStringArray(R.array.hotel_ratings);
			String[] hotel_price = getResources().getStringArray(R.array.hotel_price);
			//These holds the String values to be parsed to Float
			String[] hotel_str_lat = getResources().getStringArray(R.array.hotel_coor_lat);
			String[] hotel_str_long = getResources().getStringArray(R.array.hotel_coor_long);
			
			double[] hotel_coor_lat = new double[hotel_str_lat.length];
			double[] hotel_coor_long = new double[hotel_str_long.length];
			
			// Converting to float
			for( int i = 0; i < hotel_str_lat.length; i++)
			{
				hotel_coor_lat[i] = Double.parseDouble(hotel_str_lat[i]);
			}
			
			for( int i = 0; i < hotel_str_long.length; i++)
			{
				hotel_coor_long[i] = Double.parseDouble(hotel_str_long[i]);
			}
			
			String[] hotel_description = getResources().getStringArray(R.array.hotel_description);
			String[] hotel_amenities = getResources().getStringArray(R.array.hotel_amneties);
			
			String[] dest_names = getResources().getStringArray(R.array.dest_names);
			TypedArray dest_icons = getResources().obtainTypedArray(R.array.dest_icons);
			String[] dest_ratings = getResources().getStringArray(R.array.dest_ratings);
			
			items = new ArrayList<SearchedItem>();
			
			int i;				// index used to populate the items, both hotels and tourist destinations
			int rating_img;		// used to identify which image for rating will be used
			
			// Populating hotel items
			for(i = 0; i < hotel_names.length; i++)
			{
				switch( Math.round( Float.parseFloat((hotel_ratings[i])) ) )
				{
					case 1: rating_img = R.drawable.rating_1_star; break;
					case 2: rating_img = R.drawable.rating_2_star; break;
					case 3: rating_img = R.drawable.rating_3_star; break;
					case 4: rating_img = R.drawable.rating_4_star; break;
					case 5: rating_img = R.drawable.rating_5_star; break;
					default: rating_img = R.drawable.rating_default_star; break;
				}
				
				items.add( new SearchedItem(hotel_icons.getResourceId(i, -1), rating_img, hotel_names[i], "Hotel", 
						Float.parseFloat(hotel_price[i]), hotel_coor_lat[i], hotel_coor_long[i], hotel_description[i], hotel_amenities[i]) );
				System.out.println("Item: " + items.get(i).getItem_description());
			}
			
			// Populating the tourist destination items
			
			for(int j = 0; j < dest_names.length; j++, i++)
			{
				switch( Math.round( Float.parseFloat((dest_ratings[j])) ) )
				{
					case 1: rating_img = R.drawable.rating_1_star; break;
					case 2: rating_img = R.drawable.rating_2_star; break;
					case 3: rating_img = R.drawable.rating_3_star; break;
					case 4: rating_img = R.drawable.rating_4_star; break;
					case 5: rating_img = R.drawable.rating_5_star; break;
					default: rating_img = R.drawable.rating_default_star; break;
				}
				
				items.add( new SearchedItem(dest_icons.getResourceId(j, -1), rating_img, dest_names[j], "Tourist Destination") );
			}
		
			
		adapter = new SearchListAdapter(this, R.layout.search_result, items);

		
		ListView list = (ListView) findViewById(R.id.search_list_view);

		System.out.println("List:" + list);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(getApplicationContext(), ItemDescriptionActivity.class);
				
				List<SearchedItem> searched_items = ((SearchListAdapter) parent.getAdapter()).getItems();
				
				i.putExtra("icon", searched_items.get(position).getItem_icon());
				i.putExtra("item_name", searched_items.get(position).getItem_name());
				i.putExtra("item_type", searched_items.get(position).getItem_type());
				i.putExtra("rating", searched_items.get(position).getItem_rating());
				i.putExtra("item_price", searched_items.get(position).getItem_price());
				i.putExtra("item_description", searched_items.get(position).getItem_description());
				i.putExtra("item_amenities", searched_items.get(position).getItem_amneties());
				
				startActivity(i);
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_search, menu);
		return true;
	}
	
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public void onSearchButtonClicked(View view)
	{
		EditText inputSearch = (EditText) findViewById(R.id.search_input);
		System.out.println("Input: " + inputSearch.getText());
		
		// Setting the price to the search filter
		String price_from = price_from_text.getText().toString();
		String price_to = price_to_text.getText().toString();
		if(!price_to.isEmpty())
		{
			if(price_from.isEmpty())
				price_from = String.valueOf(0);
			
			SimpleSearchActivity.this.adapter.setPriceRange(Float.parseFloat(price_from), 
					Float.parseFloat(price_to));
		}
		
		SimpleSearchActivity.this.adapter.getFilter().filter(inputSearch.getText());

		if(search_list.getVisibility() == View.INVISIBLE)
		{
			advanced_search.setVisibility(View.INVISIBLE);
			search_list.setVisibility(View.VISIBLE);
		}
		
		// BUG: SearchButton must be clicked twice before the text appears/disappears
		// Bug could be caused by the delay in the getCount
		
		/*if(SimpleSearchActivity.this.adapter.no_results)
			no_res_text.setVisibility(View.VISIBLE);
		else
			no_res_text.setVisibility(View.INVISIBLE);*/
		
		SimpleSearchActivity.this.adapter.refreshSearchList();
	}
	
	public void onAdvancedSearchButtonClicked(View view)
	{
		
		if(search_list.getVisibility() == View.VISIBLE){
			advanced_search.setVisibility(View.VISIBLE);
			search_list.setVisibility(View.INVISIBLE);
		} else
		{
			advanced_search.setVisibility(View.INVISIBLE);
			search_list.setVisibility(View.VISIBLE);
		}
	}

}
