package com.intelligrape.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentOne extends Fragment {

    Intercom mIntercom;
    String TAG = "FragmentOne";

    public void updateMessage(String message, int currentIndex) {
        switch(currentIndex) {
            case 0:
                TextView tvMessage = (TextView) getActivity().findViewById(R.id.tvMessage);
                if(tvMessage != null) {
                    tvMessage.setText(message);
                } else {
                    Log.e(TAG, "no textview to update");
                }
                break;
            case 1:
                TextView tvMessage2 = (TextView) getActivity().findViewById(R.id.tvMessage2);
                if(tvMessage2 != null) {
                    tvMessage2.setText(message);
                } else {
                    Log.e(TAG, "no textview2 to update");
                }
                break;
            default:
                Log.e(TAG, "nothing to do for index : " + currentIndex);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Log.i(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnRequest = (Button) getView().findViewById(R.id.btnGet);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntercom.requestMessage(0);
            }
        });

        Button btnRequest2 = (Button) getView().findViewById(R.id.btnGet2);
        btnRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntercom.requestMessage(1);
            }
        });
    }

    public void setIntercom(Intercom mIntercom) {
        this.mIntercom = mIntercom;
    }

    interface Intercom {
        void requestMessage(int index);
    }
}
