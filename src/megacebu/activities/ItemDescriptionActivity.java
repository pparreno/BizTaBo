package megacebu.activities;

import megacebu.searchview.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/* This is the activity that shows the complete information of an item */

public class ItemDescriptionActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstances)
	{
		super.onCreate(savedInstances);
		setContentView(R.layout.item_description);
		
		ImageView item_image = (ImageView) findViewById(R.id.desc_item_img);
		ImageView item_rating = (ImageView) findViewById(R.id.desc_item_rating);
		TextView item_name = (TextView) findViewById(R.id.desc_item_name);
		TextView item_type = (TextView) findViewById(R.id.desc_item_type);
		// Change available amneties to activities (for tourist destination)
		TextView amnety_label = (TextView) findViewById(R.id.amneties_label);
		
		Intent i = getIntent();
		
		item_image.setBackgroundResource(i.getExtras().getInt("icon"));
		item_rating.setBackgroundResource(i.getExtras().getInt("rating"));
		item_name.setText(i.getExtras().getString("item_name"));
		item_type.setText(i.getExtras().getString("item_type"));
		
		if(i.getExtras().getString("item_type").contains("Hotel"))
		{
			amnety_label.setText("Amneties available: ");
		} else if(i.getExtras().getString("item_type").contains("Tourist Destination"))
		{
			amnety_label.setText("Suggested activities: ");
		}
		
	}

}
