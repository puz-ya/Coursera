package com.puzino.a8bitface;

/**
 * Created by YD on 22.01.2017.
 */

public class HatsData {

    private int mHatNum;
    private int mHatImage;

    public static final HatsData[] mHatsDatas = {
            new HatsData(1,R.drawable.no_avatar),
            new HatsData(2,R.drawable.no_avatar),
            new HatsData(3,R.drawable.no_avatar),
            new HatsData(4,R.drawable.no_avatar),
            new HatsData(5,R.drawable.no_avatar)
    };

    private HatsData(int num, int res){
        mHatNum = num;
        mHatImage = res;
    }

    public int getNum(){
        return mHatNum;
    }

    public int getImage(){
        return mHatImage;
    }

    //string view of this object
    public String toString(){
        return Integer.toString(mHatNum);
    }

}
