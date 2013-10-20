package megacebu.activities;

import megacebu.searchview.R;
import megacebu.tools.CebuEventsPromosAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PromosAndEventsActivity extends Activity {
	
	// dummy data
		private String[] DUMMIES_TITLE = new String[] {
				"Deluxe Room - Radisson", "Ayala Malls",
				"Radisson's Christmas Buffet", "Radisson's Pool Party" };
		private double[] DUMMIES_PRICE = new double[] { 1, 20, 200, 1200 };
		private String[] DUMMIES_STARTDATE = new String[] {"9-23-13", "9-22-13", "9-27-13", "10-02-13"};
		private String[] DUMMIES_ENDDATE = new String[] {"9-23-13", "9-23-13", "9-27-13", "10-02-13"};
		private int[] DUMMIES_WEIGHT = new int[] {50, 1, 80, 50};
		private String[] DUMMIES_SRC = new String[] {"radroom", "ayala", "radbuffet", "radpool"};
		private static final String[] DUMMIES_SORTNAME = new String[] { "Sort by Price", "Sort by Title", "Sort by Start Date" };
		
		// instance variables
		private Boolean isSortActivated;
		ListView lview;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);

			isSortActivated = false;
			lview = (ListView) findViewById(R.id.listEventsPromos);

			// distort sorted data to prioritize weights
			prioritizeEventsPromos();
			
			setAdapters();
			
			
			// on item click listener for list view
			lview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					if(isSortActivated){
						switch(position){
						case 0: // sort by price in ascending order
							sortPrice();
							break;
							
						case 1: // sort by title in descending order
							sortTitle();
							break;
							
						case 2: // sort by start date in descending order
							sortStartDate();
							break;
						}
						
						// distort sorted data to prioritize weights
						prioritizeEventsPromos();
						
						// to return back to its list of events and promos
						isSortActivated = false;
						setAdapters();
					}
					
				}
				
			});
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		@Override
		  public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		    case R.id.action_sort:
		    	isSortActivated = (isSortActivated)? false: true;
				setAdapters();
		      break;
		    case R.id.action_options:
		      Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT)
		          .show();
		      break;

		    default:
		      break;
		    }

		    return true;
		  } 
		
		// adapters for sort and events&promos lists
			private void setAdapters() {
				lview.setAdapter(new CebuEventsPromosAdapter(this, (isSortActivated) ? DUMMIES_SORTNAME : DUMMIES_TITLE, DUMMIES_PRICE, DUMMIES_STARTDATE, DUMMIES_ENDDATE, DUMMIES_SRC, DUMMIES_WEIGHT));
			}
			
		// prioritize events and promos with less weight
		// if weight < 50, that event/promo should be first in the list (if more than 1, the positioning is sequential)
			private void prioritizeEventsPromos(){
				for(int i = 0, j = 0; j < DUMMIES_WEIGHT.length; j++){
					if(DUMMIES_WEIGHT[j] < 50){
						SwapDummies(i,j);
						i++;
					}
				} 
			}
		
		// sort events and promos by title in descending order
		private void sortTitle(){
			for(int i = 0; i < DUMMIES_TITLE.length; i++){
				for(int j = i+1; j < DUMMIES_TITLE.length; j++){
					
					if(DUMMIES_TITLE[i].compareToIgnoreCase(DUMMIES_TITLE[j]) > 0)
						SwapDummies(i,j);
				} 
			}		
		}
		
		// sort events and promos by price in ascending order
		private void sortPrice(){
			for(int i = 0; i < DUMMIES_PRICE.length; i++){
				for(int j = i+1; j < DUMMIES_PRICE.length; j++){
					
					if(DUMMIES_PRICE[i] > DUMMIES_PRICE[j])
						SwapDummies(i,j);
				} 
			}	
		}
		
		// sort events and promos by start date in ascending order
		private void sortStartDate(){
			
			String[] tempString = RemoveDelimiters();

			for(int i = 0; i < DUMMIES_STARTDATE.length; i++){
				for(int j = i+1; j < DUMMIES_STARTDATE.length; j++){
					
					System.out.println(Long.parseLong(tempString[i]));
					if(Long.parseLong(tempString[i]) > Long.parseLong(tempString[j]))
						SwapDummies(i,j);
				} 
			}	
		}
		
		// dummy method
		private String[] RemoveDelimiters(){
			String[] returnString = new String[]{"", "", "", ""};
			
			for(int counter = 0; counter < DUMMIES_STARTDATE.length; counter++){
				String[] temp = DUMMIES_STARTDATE[counter].split("-");
				
				// access all splitted strings and store them to variable returnString
				for(int subCounter = 0; subCounter < temp.length; subCounter++)
					returnString[counter] += temp[subCounter];
			}
			
			return returnString;
		}
		
		// dummy method!
		private void SwapDummies(int i, int j){
			
			// SUPER INEFFICIENT! 
			String temp = DUMMIES_TITLE[j];
			DUMMIES_TITLE[j] = DUMMIES_TITLE[i];
			DUMMIES_TITLE[i] = temp;
			
			double temp1 = DUMMIES_PRICE[j];
			DUMMIES_PRICE[j] = DUMMIES_PRICE[i];
			DUMMIES_PRICE[i] = temp1;
			
			temp = DUMMIES_STARTDATE[j];
			DUMMIES_STARTDATE[j] = DUMMIES_STARTDATE[i];
			DUMMIES_STARTDATE[i] = temp;
			
			temp = DUMMIES_ENDDATE[j];
			DUMMIES_ENDDATE[j] = DUMMIES_ENDDATE[i];
			DUMMIES_ENDDATE[i] = temp;
			
			temp = DUMMIES_SRC[j];
			DUMMIES_SRC[j] = DUMMIES_SRC[i];
			DUMMIES_SRC[i] = temp;
			
			int temp2 = DUMMIES_WEIGHT[j];
			DUMMIES_WEIGHT[j] = DUMMIES_WEIGHT[i];
			DUMMIES_WEIGHT[i] = temp2;
		}

}
