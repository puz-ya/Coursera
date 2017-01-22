package com.puzino.a8bitface;

/**
 * Created by YD on 22.01.2017.
 */

public class BodysData {

    private int mBodyNum;
    private int mBodyImage;

    public static final BodysData[] mBodysDatas = {
            new BodysData(1,R.drawable.no_avatar),
            new BodysData(2,R.drawable.no_avatar),
            new BodysData(3,R.drawable.no_avatar),
            new BodysData(4,R.drawable.no_avatar),
            new BodysData(5,R.drawable.no_avatar)
    };

    private BodysData(int num, int res){
        mBodyNum = num;
        mBodyImage = res;
    }

    public int getNum(){
        return mBodyNum;
    }

    public int getImage(){
        return mBodyImage;
    }

    //string view of this object
    public String toString(){
        return Integer.toString(mBodyNum);
    }
}
