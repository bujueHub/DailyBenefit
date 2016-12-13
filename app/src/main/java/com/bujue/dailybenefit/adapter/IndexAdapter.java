package com.bujue.dailybenefit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bujue.dailybenefit.R;
import com.bujue.dailybenefit.entity.ContentItem;
import com.huxq17.swipecardsview.BaseCardAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 首页显示Adapter
 * @author bujue
 * @date 16/12/13
 */
public class IndexAdapter extends BaseCardAdapter<ContentItem> {

    private static final String TAG = "IndexAdapter";

    private Context mContext;
    private List<ContentItem> mList;

    public IndexAdapter(Context context){
        this.mContext = context;
    }

    public IndexAdapter(Context context,List<ContentItem> list){
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<ContentItem> list){
        this.mList = list;
    }

    public void addData(List<ContentItem> list){
        if(this.mList == null){
            this.mList = list;
        }
        this.mList.addAll(list);
    }

    public ContentItem getData(int index){
        if(mList == null){
            return null;
        }
        return mList.get(index);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getCardLayoutId() {
        return R.layout.card_item;
    }

    @Override
    public void onBindData(int position, View cardview) {
        if (mList == null || mList.size() == 0) {
            return;
        }
        ImageView imageView = (ImageView) cardview.findViewById(R.id.iv_meizi);
        ContentItem contentItem = mList.get(position);
        String url = contentItem.getImageurl();
        Picasso.with(mContext).load(url).config(Bitmap.Config.RGB_565).into(imageView);
    }

    /**
     * 如果可见的卡片数是3，则可以不用实现这个方法
     * @return
     */
    @Override
    public int getVisibleCardCount() {
        return 3;
    }
}
