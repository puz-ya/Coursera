package com.puzino.a8bitface;

/**
 * Created by Puzino Yury (YD) on 22.01.2017.
 */

public class BodysData {

    private int mBodyNum;
    private int mBodyImage;

    //1 - sort order, 2 - image source
    public static final BodysData[] mBodysDatas = {
            new BodysData(1,R.drawable.body_female_01),
            new BodysData(2,R.drawable.body_female_02),
            new BodysData(3,R.drawable.body_female_03),
            new BodysData(4,R.drawable.body_male_01),
            new BodysData(5,R.drawable.body_male_02),
            new BodysData(6,R.drawable.body_male_03)
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

    public static int getImageFromData(int num) {
        for (BodysData item : mBodysDatas) {
            if (item.getNum() == num) {
                return item.getImage();
            }
        }
        return -1;
    }

    //string view of this object
    public String toString(){
        return Integer.toString(mBodyNum);
    }
}
