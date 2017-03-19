package com.example.majun.activityscenetransitionbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private GridView mGridView;
    private GridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(this);
        mGridViewAdapter = new GridViewAdapter();
        mGridView.setAdapter(mGridViewAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = (Item) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PARAM_ID, item.getId());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair<View, String>(view.findViewById(R.id.imageview), DetailActivity.VIEW_IMAGE), new Pair<View, String>(view.findViewById(R.id.textview), DetailActivity.VIEW_TITLE));
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }

    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int position) {
            return Item.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(getItem(position).getName());
//            try {
//                URL url = new URL("http://image.uuu9.com/pcgame/dota2//UploadFiles//201703/_Z201703171136576091.jpg");
//                Glide.with(parent.getContext()).load(url).into(viewHolder.imageView);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
            Glide.with(MainActivity.this).load(getItem(position).getThumbnailUrl()).into(viewHolder.imageView);
//            viewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//            Log.i("imagesize", "widthSize:----->" + viewHolder.imageView.getMeasuredWidth());
//            Log.i("imagesize", "heightSize:----->" + viewHolder.imageView.getMeasuredHeight());
            return convertView;
        }

        class ViewHolder {
            ImageView imageView;
            TextView textView;
        }
    }
}
