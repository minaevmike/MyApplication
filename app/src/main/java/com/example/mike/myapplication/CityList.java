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
    String[] cities = new String[] {"Москва", "Казань", "Санкт-Петербург", "Грозный","Дагестан","Мурманск"};
    private OnItemSelectedListener mCallback;

    public static Map<String, Pair<String, String>> mCities = new TreeMap<String, Pair<String, String>>();

    public void initializeCities() {
        mCities.put("Москва", new Pair<String, String>("37.617671", "55.755768"));
        mCities.put("Казань", new Pair<String, String>("49.106585", "55.795793"));
        mCities.put("Санкт-Петербург", new Pair<String, String>("30.315868", "59.939095"));
        mCities.put("Нижний Новгород", new Pair<String, String>("44.005986", "56.326887"));
        mCities.put("Грозный", new Pair<String, String>("45.698197", "43.317992"));
        mCities.put("Дагестан", new Pair<String, String>("46.996864", "43.11083"));
        mCities.put("Мурманск", new Pair<String, String>("33.07454", "68.969563"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeCities();
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
