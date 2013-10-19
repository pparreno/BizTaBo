package megacebu.data_objects;

import java.util.Comparator;
import java.util.Locale;

import megacebu.searchview.R;

public class SearchedItem {
	
	// The following are images for the searched item
	// Change data type when the dummy data are replaced with the ones in DB
	private int item_icon;
	private int item_rating;		// Please change this accordingly when rating algorithm is established
	private float item_price_from;
	private float item_price_to;
	private double item_long;
	private double item_lat;
	
	private String item_name, item_type, item_description, item_location;
	
	public SearchedItem()
	{
		super();
	}
	
	public SearchedItem(int item_ic, int item_rate, String item_nme, String item_typ)
	{
		this.item_icon = item_ic;
		this.item_rating = item_rate;
		this.item_name = item_nme;
		this.item_type = item_typ;
	}
	
	// Getters and Setters
	
	public int getItem_icon() {
		return item_icon;
	}

	public void setItem_icon(int item_icon) {
		this.item_icon = item_icon;
	}

	public int getItem_rating() {
		return item_rating;
	}

	public void setItem_rating(int item_rating) {		
		this.item_rating = item_rating;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	
	
	public float getItem_price_from() {
		return item_price_from;
	}

	public void setItem_price_from(float item_price_from) {
		this.item_price_from = item_price_from;
	}


	public float getItem_price_to() {
		return item_price_to;
	}

	public void setItem_price_to(float item_price_to) {
		this.item_price_to = item_price_to;
	}


	public double getItem_long() {
		return item_long;
	}

	public void setItem_long(double item_long) {
		this.item_long = item_long;
	}


	public double getItem_lat() {
		return item_lat;
	}

	public void setItem_lat(double item_lat) {
		this.item_lat = item_lat;
	}


	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}


	public String getItem_location() {
		return item_location;
	}

	public void setItem_location(String item_location) {
		this.item_location = item_location;
	}


	public static Comparator<SearchedItem> ItemNameComparator 
    = new Comparator<SearchedItem>() {

			public int compare(SearchedItem item1, SearchedItem item2) {
			
			String item_name1 = item1.getItem_name().toLowerCase(Locale.getDefault());
			String item_name2 = item2.getItem_name().toLowerCase(Locale.getDefault());
			
			//ascending order
			return item_name1.compareTo(item_name2);
			
			//descending order
			//return item_name2.compareTo(item_name1);
			}
		
		};
		
	public int convertStringResource(String s)
	{
		// For the meantime
		return R.drawable.alba_uno_hotel;
	}
	

}
