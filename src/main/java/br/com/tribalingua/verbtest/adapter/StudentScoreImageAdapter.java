package br.com.tribalingua.verbtest.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.tribalingua.verbtest.R;
import br.com.tribalingua.verbtest.model.Category;
import br.com.tribalingua.verbtest.model.StudentScore;
import br.com.tribalingua.verbtest.util.ImageLoader;

public class StudentScoreImageAdapter extends BaseAdapter {
	 
    private Activity activity;
    private List<StudentScore> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public StudentScoreImageAdapter(Activity activity, List<StudentScore> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return data.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.img_detail_list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        StudentScore studentScore = data.get(position);
 
        // Setting all values in listview
        title.setText(studentScore.getName());
        artist.setText(studentScore.getScore().toString());
        duration.setText("");
        imageLoader.DisplayImage(studentScore.getThumbUrl(), thumb_image);
        return vi;
    }
}
