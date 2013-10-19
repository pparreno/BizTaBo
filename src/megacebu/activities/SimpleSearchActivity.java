package megacebu.activities;

import java.util.ArrayList;
import java.util.List;

import megacebu.data_objects.SearchedItem;
import megacebu.searchview.R;
import megacebu.tools.SearchListAdapter;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SimpleSearchActivity extends Activity {

	private SearchListAdapter adapter;
	private ListView search_list;
	private View advanced_search;
	private Button advanced_button;
	private TextView no_res_text;
	private List<SearchedItem> items;
	
	@SuppressLint({ "Recycle", "CutPasteId" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_search);
		
		search_list = (ListView) findViewById(R.id.search_list_view);
		advanced_search = (View) findViewById(R.id.advanced_search_settings);
		advanced_button = (Button) findViewById(R.id.advanced_search_button);
		no_res_text = (TextView) findViewById(R.id.no_search_result_text);
		
		// These arrays are holders of the dummy data found in DummyData.xml
			String[] hotel_names = getResources().getStringArray(R.array.hotel_names);
			TypedArray hotel_icons = getResources().obtainTypedArray(R.array.hotel_icons);
			String[] hotel_ratings = getResources().getStringArray(R.array.hotel_ratings);
			
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
				
				items.add( new SearchedItem(hotel_icons.getResourceId(i, -1), rating_img, hotel_names[i], "Hotel") );
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
	
	
	public void onSearchButtonClicked(View view)
	{
		EditText inputSearch = (EditText) findViewById(R.id.search_input);
		System.out.println("Input: " + inputSearch.getText());
		SimpleSearchActivity.this.adapter.getFilter().filter(inputSearch.getText());
		
		if(search_list.getVisibility() == View.INVISIBLE)
		{
			advanced_button.setText(R.string.advanced_search_button_name);
			advanced_search.setVisibility(View.INVISIBLE);
			search_list.setVisibility(View.VISIBLE);
		}
		
		// BUG: SearchButton must be clicked twice before the text appears/disappears
		// Bug could be caused by the delay in the getCount
		
		if(SimpleSearchActivity.this.adapter.getCount() < 1)
			no_res_text.setVisibility(View.VISIBLE);
		else
			no_res_text.setVisibility(View.INVISIBLE);
		
		SimpleSearchActivity.this.adapter.refreshSearchList();
	}
	
	public void onAdvancedSearchButtonClicked(View view)
	{
		
		if(search_list.getVisibility() == View.VISIBLE){
			advanced_button.setText(R.string.hide_advanced_search_button);
			advanced_search.setVisibility(View.VISIBLE);
			search_list.setVisibility(View.INVISIBLE);
		} else
		{
			advanced_button.setText(R.string.advanced_search_button_name);
			advanced_search.setVisibility(View.INVISIBLE);
			search_list.setVisibility(View.VISIBLE);
		}
	}

}
