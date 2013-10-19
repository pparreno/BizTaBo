package megacebu.activities;

import megacebu.searchview.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
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
		featuredSpec.setIndicator("Featured", getResources().getDrawable(R.drawable.ic_launcher));
		Intent featuredIntent = new Intent(this, FeaturedActivity.class);
		featuredSpec.setContent(featuredIntent);
		
		TabSpec searchSpec = tabHost.newTabSpec("Search");
		searchSpec.setIndicator("Search", getResources().getDrawable(R.drawable.ic_search_button));
		Intent searchIntent = new Intent(this, SimpleSearchActivity.class);
		searchSpec.setContent(searchIntent);
		
		TabSpec mapSpec = tabHost.newTabSpec("Map");
		mapSpec.setIndicator("Map", getResources().getDrawable(R.drawable.ic_launcher));
		Intent mapIntent = new Intent(this, ShowMapActivity.class);
		mapSpec.setContent(mapIntent);
		
		TabSpec promoAndEventsSpec = tabHost.newTabSpec("PromosAndEvents");
		promoAndEventsSpec.setIndicator("Promos and Events", getResources().getDrawable(R.drawable.ic_launcher));
		Intent promoAndEventsIntent = new Intent(this, PromosAndEventsActivity.class);
		promoAndEventsSpec.setContent(promoAndEventsIntent);
		
		TabSpec aboutCebuSpec = tabHost.newTabSpec("AboutCebu");
		aboutCebuSpec.setIndicator("About Cebu", getResources().getDrawable(R.drawable.ic_launcher));
		Intent aboutCebuIntent = new Intent(this, PromosAndEventsActivity.class);
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
