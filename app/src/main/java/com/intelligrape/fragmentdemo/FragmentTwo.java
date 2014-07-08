package com.intelligrape.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentTwo extends Fragment {

    Intercom mIntercom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText etMessage = (EditText) getActivity().findViewById(R.id.etMessage);
        etMessage.setHint("Enter message for fragment 1");
        Button btnSend = (Button) getActivity().findViewById(R.id.btnSend);
        btnSend.setText("Send message to fragment 1");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etMessage.getText().toString();
                mIntercom.sendMessage(message);
            }
        });
    }

    public void setIntercom(Intercom mIntercom) {
        this.mIntercom = mIntercom;
    }

    interface Intercom {
        void sendMessage(String message);
    }
}
