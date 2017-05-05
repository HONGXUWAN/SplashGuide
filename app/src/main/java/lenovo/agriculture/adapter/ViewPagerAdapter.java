package lenovo.agriculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lenovo.agriculture.activity.GuideActivity;
import lenovo.agriculture.activity.MainActivity;
import lenovo.agriculture.activity.R;

/**
 * Created by wanhongxu on 2017/5/2.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private  LayoutInflater mInflater;
    private  List<Integer> mList;
    private  Context mContext;

    public ViewPagerAdapter(Context mContext, List<Integer> mList){
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 数据源长度
     *
     * @see PagerAdapter#getCount()
     * @return
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 判断两个对象是否相同
     *
     * @see android.support.v4.view.PagerAdapter#isViewFromObject(View, Object)
     * @param arg0
     * @param arg1
     * @return
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0.equals((View) arg1);
    }

    /**
     * 销毁视图
     *
     * @see android.support.v4.view.PagerAdapter#destroyItem(ViewGroup, int, Object)
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * 自定义视图
     *
     *
     * @see android.support.v4.view.PagerAdapter {@link #instantiateItem(ViewGroup, int)}
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = (View) mInflater.inflate(R.layout.guide_item,container,false);
        RelativeLayout mLayout = (RelativeLayout) view.findViewById(R.id.picture);
        TextView mTextView = (TextView) view.findViewById(R.id.enter_tv);
        mLayout.setBackgroundResource(mList.get(position));
        mTextView.setBackgroundResource(R.mipmap.control_bg);
        //设置跳转按钮
        if (position ==2){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setBackgroundColor(Color.GREEN);
        }else {
            mTextView.setVisibility(View.GONE);
        }
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity mActivity = (GuideActivity) mContext;
                mContext.startActivity(new Intent(mContext, MainActivity.class));
                mActivity.finish();
            }
        });
        ((ViewPager) container).addView(view,0);
        return view;
    }
}
