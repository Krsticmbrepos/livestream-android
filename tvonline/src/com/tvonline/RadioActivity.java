package com.tvonline;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RadioActivity extends android.support.v4.app.Fragment implements
		OnItemClickListener {
	String[] ds;
	ArrayAdapter<String> adapter;
	ListView list;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.radio, container, false);
		list = (ListView) v.findViewById(R.id.listkenh);
		list.setAdapter(new CustomAdapter1(getActivity().getBaseContext()));
		list.setOnItemClickListener(this);
		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity().getBaseContext(),
				StreamTV.class);
		String url = getResources().getStringArray(R.array.radio_urls)[pos];
		intent.putExtra("url", url);
		startActivity(intent);
	}

	class singlerow {
		String ds;
		int images;

		singlerow(String ds, int image) {
			this.ds = ds;
			this.images = image;
		}
	}

	public class CustomAdapter1 extends BaseAdapter {
		ArrayList<singlerow> list;
		Context context;

		CustomAdapter1(Context c) {
			context = c;
			list = new ArrayList<singlerow>();
			Resources res = c.getResources();
			String[] ds = res.getStringArray(R.array.radio_names);
			int[] images = { R.drawable.radio1, R.drawable.radio2,
					R.drawable.radio3, R.drawable.radio4, R.drawable.radio5,
					R.drawable.radio6, R.drawable.radio7 };
			for (int i = 0; i < 7; i++) {
				list.add(new singlerow(ds[i], images[i]));
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int i) {
			// TODO Auto-generated method stub
			return list.get(i);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.my_list, viewGroup, false);

			TextView title = (TextView) row.findViewById(R.id.title);
			ImageView image = (ImageView) row.findViewById(R.id.image);
			singlerow temp = list.get(i);
			title.setText(temp.ds);
			image.setImageResource(temp.images);
			return row;
		}
	}
}
