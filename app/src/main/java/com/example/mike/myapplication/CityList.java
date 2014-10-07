package com.example.mike.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mike on 28.09.2014.
 */
public class CityList extends Fragment {
    static String[] cities = new String[] {"Moscow", "Kazan", "London", "New-York", "Boston", "Minsk",
    "Washington", "Kiev", "Tokyo", "Amsterdam", "Berlin", "Milan", "Odessa", "Saints-Petersburg","Madrid",
    "Barcelona"};
    private OnItemSelectedListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("CityList", "On CreateView");
        return inflater.inflate(R.layout.cities_list, null);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        Log.d("CityList", "On View Created");
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(new NewsListAdapter(cities));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView c = (TextView) view.findViewById(R.id.text);
                mCallback.onArticleSelected(c.getText().toString());
                Log.i("onViewCreated DetailFragment", c.getText().toString());
            }
        });
    }

    // Container Activity must implement this interface
    public interface OnItemSelectedListener {
        public void onArticleSelected(String city);
    }

    private class NewsListAdapter extends ArrayAdapter<String> {

        public NewsListAdapter(String[] objects) {
            super(getActivity(), 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.text.setText(getItem(position));
            return convertView;
        }

        private class ViewHolder {

            public TextView text;
        }
    }
}
