package com.puzino.a8bitface;

/**
 * Created by Puzino Yury (YD) on 22.01.2017.
 */

public class HeadsData {

    private int mHeadNum;
    private int mHeadImage;

    //1 - sort order, 2 - image source
    public static final HeadsData[] mHeadsDatas = {
            new HeadsData(1,R.drawable.head_01),
            new HeadsData(2,R.drawable.head_02),
            new HeadsData(3,R.drawable.head_03),
            new HeadsData(4,R.drawable.head_04),
            new HeadsData(5,R.drawable.head_05),
            new HeadsData(6,R.drawable.head_06)
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

    public static int getImageFromData(int num) {
        for (HeadsData item : mHeadsDatas) {
            if (item.getNum() == num) {
                return item.getImage();
            }
        }
        return -1;
    }

    //string view of this object
    public String toString(){
        return Integer.toString(mHeadNum);
    }
}
