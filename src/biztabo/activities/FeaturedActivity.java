package biztabo.activities;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import biztabo.tools.FeaturedAdapter;

import com.example.biztabo.R;

public class FeaturedActivity extends ListActivity {
	// dummy data
		private static final String[] DUMMIES_SRC = new String[] {"radroom", "ayala", "radbuffet", "radpool"};
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//setContentView(R.layout.activity_main);
			
			setListAdapter(new FeaturedAdapter(this, DUMMIES_SRC));
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
}
