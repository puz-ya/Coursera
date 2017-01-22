package com.puzino.a8bitface;

/**
 * Created by YD on 22.01.2017.
 */

public class HeadsData {

    private int mHeadNum;
    private int mHeadImage;

    public static final HeadsData[] mHeadsDatas = {
            new HeadsData(1,R.drawable.no_avatar),
            new HeadsData(2,R.drawable.no_avatar),
            new HeadsData(3,R.drawable.no_avatar),
            new HeadsData(4,R.drawable.no_avatar),
            new HeadsData(5,R.drawable.no_avatar)
    };

    private HeadsData(int num, int res){
        mHeadNum = num;
        mHeadImage = res;
    }

    public int getNum(){
        return mHeadNum;
    }

    public int getImage(){
        return mHeadImage;
    }

    //string view of this object
    public String toString(){
        return Integer.toString(mHeadNum);
    }
}
