package biztabo.data_objects;

import java.util.Comparator;
import java.util.Locale;

import com.example.biztabo.R;

public class SearchedItem {
	
	// The following are images for the searched item
	// Change data type when the dummy data are replaced with the ones in DB
	private int item_icon;
	private int item_rating;		// Please change this accordingly when rating algorithm is established
	private float item_price;
	private double item_long;
	private double item_lat;
	
	private String item_name, item_type, item_description, item_location, item_amneties;
	
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
	
	public SearchedItem(int item_ic, int item_rate, String item_nme, String item_typ, 
			float item_price, double item_lat, double item_long, String item_desp, String item_amen)
	{
		this.item_icon = item_ic;
		this.item_rating = item_rate;
		this.item_name = item_nme;
		this.item_type = item_typ;
		this.item_price = item_price;
		this.item_lat = item_lat;
		this.item_long = item_long;
		this.setItem_description(item_desp);
		this.setItem_amneties(item_amen);
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

	public float getItem_price() {
		return item_price;
	}

	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}

	public String getItem_amneties() {
		return item_amneties;
	}

	public void setItem_amneties(String item_amneties) {
		this.item_amneties = item_amneties;
	}
	

}
