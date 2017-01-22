package com.puzino.a8bitface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment02Buttons extends Fragment {

    private int mHat = 1;   //image number of hat part
    private int mHead = 1;  //image number of head part
    private int mBody = 1;  //image number of body part

    private int mMaxWidth = 512;    //init size of images to scale
    private int mMaxHeight = 512;

    Button mPlus;
    Button mMinus;

    MainActivity mActivity = null;
    View mFragmentView = null;

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

            mMaxWidth = savedInstanceState.getInt("maxWidth");
            mMaxHeight = savedInstanceState.getInt("maxHeight");
        }

        // Listener that acts when +\- is clicked
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
                updateAllTextViews();
                //change ImageView
                updateMainImage();
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

    @Override   //new version, NO (Activity activity) :\
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof MainActivity){
            mActivity = (MainActivity) context;
        }
    }

    //
    @Override
    public void onStart(){
        super.onStart();

        mFragmentView = getView();
       if(mFragmentView != null){

            updateAllTextViews();
            updateMainImage();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("hatID", mHat);
        savedInstanceState.putInt("headID", mHead);
        savedInstanceState.putInt("bodyID", mBody);

        //size of images to scale
        savedInstanceState.putInt("maxWidth",mMaxWidth);
        savedInstanceState.putInt("maxHeight",mMaxHeight);
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

    //no, we don't need any locale - just integer_to_string
    public void updateAllTextViews(){
        TextView textViewHat = (TextView) mFragmentView.findViewById(R.id.textView_hat_number);
        textViewHat.setText(Integer.toString(mHat));

        TextView textViewHead = (TextView) mFragmentView.findViewById(R.id.textView_head_number);
        textViewHead.setText(Integer.toString(mHead));

        TextView textViewBody = (TextView) mFragmentView.findViewById(R.id.textView_body_number);
        textViewBody.setText(Integer.toString(mBody));
    }

    public void updateMainImage(){
        if(mActivity != null) {
            //set new IDs
            mActivity.setHat(mHat);
            mActivity.setHead(mHead);
            mActivity.setBody(mBody);

            //now get ImageView and set Bitmaps
            ImageView imageView = (ImageView) mActivity.findViewById(R.id.imageView);

            int hatImage = HatsData.getImageFromData(mHat);
            int headImage = HeadsData.getImageFromData(mHead);
            int bodyImage = BodysData.getImageFromData(mBody);

            //get chosen resources
            Drawable drawableHat = ResourcesCompat.getDrawable(getResources(), hatImage, null);
            Drawable drawableHead = ResourcesCompat.getDrawable(getResources(), headImage, null);
            Drawable drawableBody = ResourcesCompat.getDrawable(getResources(), bodyImage, null);

            //try get Bitmap
            Bitmap bitmapHat = null;
            Bitmap bitmapHead = null;
            Bitmap bitmapBody = null;
            try {
                bitmapHat = ((BitmapDrawable) drawableHat).getBitmap();
                bitmapHead = ((BitmapDrawable) drawableHead).getBitmap();
                bitmapBody = ((BitmapDrawable) drawableBody).getBitmap();
            }catch (NullPointerException ex){
                Log.d("Fragment2: ",ex.getMessage());
            }

            if(bitmapHat == null || bitmapHead == null || bitmapBody == null){
                return;
            }

            //imageView is empty, after restart (change orientation)
            if(imageView.getWidth() > 0){
                mMaxWidth = imageView.getWidth();
            }

            if(imageView.getHeight() > 0){
                mMaxHeight = imageView.getHeight();
            }

            //scale for identical view
            Bitmap scaledBitmapHat = Bitmap.createScaledBitmap(bitmapHat, mMaxWidth, mMaxHeight, true);
            Bitmap scaledBitmapHead = Bitmap.createScaledBitmap(bitmapHead, mMaxWidth, mMaxHeight, true);
            Bitmap scaledBitmapBody = Bitmap.createScaledBitmap(bitmapBody, mMaxWidth, mMaxHeight, true);

            Bitmap combineImages = overlay(scaledBitmapBody, scaledBitmapHead, scaledBitmapHat);
            if (combineImages != null) {
                imageView.setImageBitmap(combineImages);
            }else{
                imageView.setImageResource(R.drawable.no_avatar);
            }
        }
    }

    //we need to overlap all 3 images - hat, head, body in 1
    // @param Bitmap - bottom image,
    // @param Bitmap - middle image,
    // @param Bitmap - top image,
    // @result Bitmap - combined image from input
    @Nullable
    public static Bitmap overlay(Bitmap bmp1Body, Bitmap bmp2Head, Bitmap bmp3Hat)
    {
        try
        {
            //TODO: our images is same size 512 - no need to worry here
            int maxWidth = bmp1Body.getWidth();
            int maxHeight = bmp1Body.getHeight();

            Bitmap bmOverlay = Bitmap.createBitmap(maxWidth, maxHeight, bmp1Body.getConfig());
            Canvas canvas = new Canvas(bmOverlay);
            canvas.drawBitmap(bmp1Body, 0, 0, null);
            canvas.drawBitmap(bmp2Head, 0, 0, null);
            canvas.drawBitmap(bmp3Hat, 0, 0, null);
            return bmOverlay;

        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
