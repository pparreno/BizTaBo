package megacebu.tools;


import megacebu.searchview.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class FeaturedAdapter extends ArrayAdapter<String> {
	
	// instance variables
	private String[] str;
	private Context context;
	
	public FeaturedAdapter(Context context, String[] str) {
		super(context, R.layout.activity_featured, str);

		this.context = context;
		this.str = str;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.activity_featured, null);
			img = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(img);
			
		} else 
			img = (ImageView) convertView.getTag();
		
		img.setBackgroundResource(context.getResources().getIdentifier(str[position], "drawable", context.getPackageName()));
		return convertView;
	
	}

}
