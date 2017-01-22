package com.puzino.a8bitface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment02Buttons extends Fragment {

    private int mHat = 1;   //image number of hat part
    private int mHead = 1;  //image number of head part
    private int mBody = 1;  //image number of body part

    Button mPlus;
    Button mMinus;

    View mView;

    public MainFragment02Buttons() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_images, container, false);

        if(savedInstanceState != null){
            mHat = savedInstanceState.getInt("hatID");
            mHead = savedInstanceState.getInt("headID");
            mBody = savedInstanceState.getInt("bodyID");
        }

        // Listener that acts when profile is selected
        View.OnClickListener mListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.decrease_hat:
                        mHat = decreaseNumber(mHat, HatsData.mHatsDatas.length);
                        break;
                    case R.id.increase_hat:
                        mHat = increaseNumber(mHat, HatsData.mHatsDatas.length);
                        break;

                    case R.id.decrease_head:
                        mHead = decreaseNumber(mHead, HeadsData.mHeadsDatas.length);
                        break;
                    case R.id.increase_head:
                        mHead = increaseNumber(mHead, HeadsData.mHeadsDatas.length);
                        break;

                    case R.id.decrease_body:
                        mBody = decreaseNumber(mBody, BodysData.mBodysDatas.length);
                        break;
                    case R.id.increase_body:
                        mBody = increaseNumber(mBody, BodysData.mBodysDatas.length);
                        break;

                }
                //update all textViews
                updateAllViews();
            }
        };

        // Attach clicks to Minus buttons
        mMinus = (Button) view.findViewById(R.id.decrease_hat);
        mMinus.setOnClickListener(mListener);
        mMinus = (Button) view.findViewById(R.id.decrease_head);
        mMinus.setOnClickListener(mListener);
        mMinus = (Button) view.findViewById(R.id.decrease_body);
        mMinus.setOnClickListener(mListener);

        // Attach clicks to Plus buttons
        mPlus = (Button) view.findViewById(R.id.increase_hat);
        mPlus.setOnClickListener(mListener);
        mPlus = (Button) view.findViewById(R.id.increase_head);
        mPlus.setOnClickListener(mListener);
        mPlus = (Button) view.findViewById(R.id.increase_body);
        mPlus.setOnClickListener(mListener);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        mView = getView();
        if(mView != null){

            updateAllViews();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("hatID", mHat);
        savedInstanceState.putInt("headID", mHead);
        savedInstanceState.putInt("bodyID", mBody);
    }

    //@param hat image number
    public void setHat(int hat){
        mHat = hat;
    }

    //@param head image number
    public void setHead(int head){
        mHead = head;
    }

    //@param body image number
    public void setBody(int body){
        mBody = body;
    }

    public int decreaseNumber(int num, int limit){

        if(num == 1){
            num = limit;
        }else{
            num--;
        }
        return num;
    }

    public int increaseNumber(int num, int limit){

        if(num == limit){
            num = 1;
        }else{
            num++;
        }
        return num;
    }

    public void updateAllViews(){
        TextView textViewHat = (TextView) mView.findViewById(R.id.textView_hat_number);
        textViewHat.setText(Integer.toString(mHat));

        TextView textViewHead = (TextView) mView.findViewById(R.id.textView_head_number);
        textViewHead.setText(Integer.toString(mHead));

        TextView textViewBody = (TextView) mView.findViewById(R.id.textView_body_number);
        textViewBody.setText(Integer.toString(mBody));
    }

}
