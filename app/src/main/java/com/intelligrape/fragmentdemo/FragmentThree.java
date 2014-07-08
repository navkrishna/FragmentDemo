package com.intelligrape.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentThree extends Fragment {

    Intercom mIntercom;
    String prev;

    public void prevMessage(String prev) {
        this.prev = prev;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText editText = (EditText) getActivity().findViewById(R.id.etMessage2);
        Button btn = (Button) getActivity().findViewById(R.id.btnSend2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = prev + "\n" + editText.getText().toString();
                mIntercom.msgFromFragThree(message);
            }
        });
    }

    public void setIntercom(Intercom mIntercom) {
        this.mIntercom = mIntercom;
    }

    interface Intercom {
        void msgFromFragThree(String message);
    }
}
