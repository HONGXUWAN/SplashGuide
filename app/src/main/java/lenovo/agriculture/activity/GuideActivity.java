package lenovo.agriculture.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import lenovo.agriculture.adapter.ViewPagerAdapter;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private ArrayList<Integer> mPictureList;
    private LinearLayout mPointSetLayout;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_show);
        initData();
        initView();
        initPoint();
        setViewPager();
    }

    private void setViewPager() {
        mAdapter = new ViewPagerAdapter(this,mPictureList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    private void initPoint() {
        mPointSetLayout.removeAllViews();

        for (int i = 0; i < mPictureList.size(); i++){
            ImageView mImageView = new ImageView(this);
            if (i == 0) {
                mImageView.setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                mImageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            mImageView
                    .setLayoutParams(new LayoutParams(20, 20, Gravity.CENTER));
            LayoutParams mParams = new LayoutParams(new ViewGroup.LayoutParams(
                    50, 50));
            mParams.leftMargin = 50;
            mParams.rightMargin = 50;
            mPointSetLayout.addView(mImageView,mParams);
        }
    }

    private void initView() {
        mPointSetLayout = (LinearLayout) findViewById(R.id.point_show_layout);
        mViewPager = (ViewPager) findViewById(R.id.picture_show_vp);
    }

    private void initData() {
        mPictureList = new ArrayList<Integer>();
        mPictureList.add(R.mipmap.main_bg);
        mPictureList.add(R.mipmap.history_bg);
        mPictureList.add(R.mipmap.control_bg);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int arg0) {
        selectPointShow(arg0);
    }

    private void selectPointShow(int index) {
        for (int i = 0;i<mPointSetLayout.getChildCount();i++){
            ImageView mImageView = (ImageView) mPointSetLayout.getChildAt(i);
            if (i == index) {
                mImageView.setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
               mImageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
