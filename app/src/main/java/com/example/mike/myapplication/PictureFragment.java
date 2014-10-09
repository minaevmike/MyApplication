package com.example.mike.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PictureFragment extends Fragment {
    public static final String LOG_TAG = "PictureFragment: ";
    public static String discription = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.splash, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onViewCreated");
        execSetPicture();
        /*if(this.getActivity() != null) {
            ImageView imageView = (ImageView) this.getActivity().findViewById(R.id.splashscreen);
            if(imageView != null) {

                Log.d(LOG_TAG, String.valueOf("ожд"));
                imageView.setImageResource(R.drawable.sunny);
            }
        }*/
        /*Bundle bundle = getArguments();
        if(bundle != null) {
            String discription = bundle.getString(PictureFragment.PICTURE_DISCRIPTION_TAG);
            Log.d(LOG_TAG, discription);
            if(this.getActivity() != null) {
                ImageView imageView = (ImageView) this.getActivity().findViewById(R.id.splashscreen);
                if(imageView != null) {

                    Log.d(LOG_TAG, String.valueOf(getPicture(discription)));
                    imageView.setImageResource(getPicture(discription));
                }
            }
        }*/
    }

    public void SetPicture(String newDiscription)
    {
        Log.d(LOG_TAG, "SetPicture");
        Log.d(LOG_TAG, newDiscription);
        discription = newDiscription;
        execSetPicture();
    }

    private void execSetPicture()
    {
        Log.d(LOG_TAG, "execSetPicture");
        if(this.getActivity() != null) {
            ImageView imageView = (ImageView) this.getActivity().findViewById(R.id.splashscreen);
            if(imageView != null) {

                //Log.d(LOG_TAG, String.valueOf(getPicture(discription)));
                imageView.setImageResource(getPicture(discription));
            }
        }
    }
    private int getPicture(String discription)
    {
        //Log.d(LOG_TAG, "getPicture");
        if(discription.contains("рмарка"))
        {
            //Log.d(LOG_TAG, "рмарка");
            return R.drawable.fair;
        }else if(discription.contains("ожд"))
        {
            //Log.d(LOG_TAG, "ожд");
            return R.drawable.rain;
        }else if(discription.contains("блач"))
        {
            //Log.d(LOG_TAG, "блач");
            return R.drawable.cloudy;
        }else if(discription.contains("роз"))
        {
            //Log.d(LOG_TAG, "роз");
            return R.drawable.groza;
        }else if(discription.contains("ушев"))
        {
            //Log.d(LOG_TAG, "ушев");
            return R.drawable.shower;
        }else if(discription.contains("олнеч"))
        {
            //Log.d(LOG_TAG, "олнеч");
            return R.drawable.s;
        }
        return R.drawable.girl;
    }

}
