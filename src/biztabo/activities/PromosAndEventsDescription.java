package biztabo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biztabo.R;

public class PromosAndEventsDescription  extends Activity{
	
	// instance variables
	private String name, start, end, image, desc;
	private double price;
	private TextView txtTitle, txtPrice, txtDuration, txtDescription;
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		
		retrieveViews();
		retrieveIntentInfo();
	
//		img.setImageResource(this.getResources().getIdentifier(image, "drawable", "biztabo.activities"));
		txtTitle.setText(name);
		txtPrice.setText(Double.toString(price));
		txtDuration.setText(start + " to " + end);
		txtDescription.setText(desc);
	}
	
	private void retrieveViews(){
		img = (ImageView) findViewById(R.id.image);
		txtTitle = (TextView) findViewById(R.id.title);
		txtPrice = (TextView) findViewById(R.id.displayPrice);
		txtDuration = (TextView) findViewById(R.id.displayDuration);
		txtDescription = (TextView) findViewById(R.id.displayDescription);
		
	} 
	
	private void retrieveIntentInfo(){
		name = getIntent().getExtras().getString("Name");
		price = getIntent().getExtras().getDouble("Price");
		start = getIntent().getExtras().getString("Start");
		end = getIntent().getExtras().getString("End");
		image = getIntent().getExtras().getString("Image");
		desc = getIntent().getExtras().getString("Description");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_desc, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    if(item.getItemId() == R.id.back)
	    	finish();

	    return true;
	  } 
}