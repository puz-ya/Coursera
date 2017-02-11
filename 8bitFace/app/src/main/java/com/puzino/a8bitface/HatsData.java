package com.puzino.a8bitface;

/**
 * Created by Puzino Yury (YD) on 22.01.2017.
 */

public class HatsData {

    private int mHatNum;
    private int mHatImage;

    //1 - sort order, 2 - image source
    public static final HatsData[] mHatsDatas = {
            new HatsData(1,R.drawable.hat_01),
            new HatsData(2,R.drawable.hat_02),
            new HatsData(3,R.drawable.hat_03),
            new HatsData(4,R.drawable.hat_band_01),
            new HatsData(5,R.drawable.hat_band_02),
            new HatsData(6,R.drawable.hat_band_03),
            new HatsData(7,R.drawable.hat_band_04),
            new HatsData(8,R.drawable.hat_band_05),
            new HatsData(9,R.drawable.hat_band_06)
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
    
    public static int getImageFromData(int num) {
        for (HatsData item : mHatsDatas) {
            if (item.getNum() == num) {
                return item.getImage();
            }
        }
        return -1;
    }



    //string view of this object
    public String toString(){
        return Integer.toString(mHatNum);
    }

}
