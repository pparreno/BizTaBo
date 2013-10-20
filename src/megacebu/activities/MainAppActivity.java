package megacebu.activities;

import megacebu.searchview.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainAppActivity extends TabActivity {
	
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		tabHost = getTabHost();

		TabSpec featuredSpec = tabHost.newTabSpec("Featured");
		featuredSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_featured));
		Intent featuredIntent = new Intent(this, FeaturedActivity.class);
		featuredSpec.setContent(featuredIntent);
		
		TabSpec searchSpec = tabHost.newTabSpec("Search");
		searchSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_search_button));
		Intent searchIntent = new Intent(this, SimpleSearchActivity.class);
		searchSpec.setContent(searchIntent);
		
		TabSpec mapSpec = tabHost.newTabSpec("Map");
		mapSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_show_map));
		Intent mapIntent = new Intent(this, ShowMapActivity.class);
		mapSpec.setContent(mapIntent);
		
		TabSpec promoAndEventsSpec = tabHost.newTabSpec("PromosAndEvents");
		promoAndEventsSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_promos_events));
		Intent promoAndEventsIntent = new Intent(this, PromosAndEventsActivity.class);
		promoAndEventsSpec.setContent(promoAndEventsIntent);
		
		TabSpec aboutCebuSpec = tabHost.newTabSpec("AboutCebu");
		aboutCebuSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_about_cebu));
		Intent aboutCebuIntent = new Intent(this, AboutCebuActivity.class);
		aboutCebuSpec.setContent(aboutCebuIntent);
		
		// Add all tabs
		
		tabHost.addTab(featuredSpec);
		tabHost.addTab(searchSpec);
		tabHost.addTab(mapSpec);
		tabHost.addTab(promoAndEventsSpec);
		tabHost.addTab(aboutCebuSpec);
		
		tabHost.setCurrentTabByTag("Featured");
		
	}
	
}
