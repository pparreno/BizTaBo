package biztabo.tools;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biztabo.R;

public class CebuEventsPromosAdapter extends ArrayAdapter<String> {
	
	// instance variables
	private String[] str, start, end, src;
	private Context context;
	private int str_length;
	private double[] d;
	private int[] weights;

	// constructor for sorting
	public CebuEventsPromosAdapter(Context context, String[] str) {
		super(context, R.layout.activity_sort_row, str);
		this.str = str;
		this.context = context;
		this.str_length = str.length;
	}
	
	// constructor for events and promos
	public CebuEventsPromosAdapter(Context context, String[] str, double[] d, String[] start, String[] end, String[] src, int[] weights) {
		super(context, R.layout.activity_row_ambot_lang_main, str);
		this.str = str;
		this.start = start;
		this.end = end;
		this.d = d;
		this.src = src;
		this.weights = weights;
		this.context = context;
		this.str_length = str.length;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// class attributes(structure for rowlayout)
		Attributes att = new Attributes();
		TextView sortTitle;
		
		// for sorting
		if (str_length == 3) {
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.activity_sort_row, null);
				sortTitle = (TextView) convertView.findViewById(R.id.sortName);
				convertView.setTag(sortTitle);
				
			} else
				sortTitle = (TextView) convertView.getTag();
			
			switch (position) {
			case 0:
				sortTitle.setText(str[0]);
				break;
			case 1:
				sortTitle.setText(str[1]);
				break;
			case 2:
				sortTitle.setText(str[2]);
				break;
			}
		} 
		// for events and promos
		else {

			if (convertView == null) {
				convertView = View.inflate(context, R.layout.activity_row_ambot_lang_main, null);

				att.image = (ImageView) convertView.findViewById(R.id.image);
				att.title = (TextView) convertView.findViewById(R.id.title);
				att.price = (TextView) convertView.findViewById(R.id.price);
				att.starts = (TextView) convertView.findViewById(R.id.starts);
				att.ends = (TextView) convertView.findViewById(R.id.ends);
				
				att.starts.setBackgroundColor(Color.rgb(255, 140, 0));
				att.ends.setBackgroundColor(Color.rgb(255, 140, 0));

				convertView.setTag(att);
			} else 
				att = (Attributes) convertView.getTag();

			if(weights[position] < 50)
				convertView.setBackgroundColor(Color.GREEN);
			else
				convertView.setBackgroundColor(Color.WHITE);
				
			
			att.image.setBackgroundResource(context.getResources().getIdentifier(src[position], "drawable", context.getPackageName()));
			att.title.setText(str[position]);
			att.price.setText("Php " + Double.toString(d[position]));
			att.starts.setText("Starts: " + start[position]);
			att.ends.setText("Ends: " + end[position]);
		}

		return convertView; // super.getView(position, convertView, parent);
	}

	static class Attributes {
		ImageView image;
		TextView title, price, starts, ends;
	}
}
