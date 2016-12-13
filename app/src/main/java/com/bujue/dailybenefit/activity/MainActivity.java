package com.bujue.dailybenefit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bujue.dailybenefit.R;
import com.bujue.dailybenefit.adapter.IndexAdapter;
import com.bujue.dailybenefit.biz.DataManager;
import com.bujue.dailybenefit.callback.Callback;
import com.bujue.dailybenefit.entity.ContentItem;
import com.bujue.dailybenefit.utils.Logger;
import com.huxq17.swipecardsview.SwipeCardsView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String URL = "http://www.mzitu.com/mm/page/";

    private static final int DEFAULT_PAGE = 2;

    private int mPage = DEFAULT_PAGE;

    private SwipeCardsView mCardsView;
    private IndexAdapter mIndexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardsView = (SwipeCardsView) this.findViewById(R.id.cards_view);

        //whether retain last card,defalut false
        mCardsView.retainLastCard(true);
        //Pass false if you want to disable swipe feature,or do nothing.
        mCardsView.enableSwipe(true);
        //设置滑动监听
        mCardsView.setCardsSlideListener(new SwipeCardsView.CardsSlideListener() {
            @Override
            public void onShow(int index) {

                int count = mIndexAdapter.getCount();

                Logger.d(TAG,"index ="+index);
                Logger.d(TAG,"list size ="+count);
                if(count - index == 1){
                    Logger.d(TAG,"load next");
                    requestData(mPage++);
                }
            }

            @Override
            public void onCardVanish(int index, SwipeCardsView.SlideType type) {
                switch (type) {
                    case LEFT:
                        break;
                    case RIGHT:
                        break;
                }
            }

            @Override
            public void onItemClick(View cardImageView, int index) {

                ContentItem contentItem = mIndexAdapter.getData(index);
                if(contentItem == null){
                    return;
                }
                Intent intent = new Intent(MainActivity.this,MeiziDetailActivity.class);
                intent.putExtra("linkUrl",contentItem.getUrl());
                startActivity(intent);
            }
        });

        // 初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData(){
        requestData(2);
    }

    private void requestData(int page) {
        DataManager.getInstance().getMeiziContentList(URL + page, new Callback<List<ContentItem>>() {
            @Override
            public void onSuccess(List<ContentItem> result) {
                Logger.d(TAG, "result = " + result);
                show(result);
            }

            @Override
            public void onException(int code, String reason) {

            }
        });
    }

    /**
     * 显示cardsview
     */
    private void show(List<ContentItem> list) {
        if (mIndexAdapter == null) {
            mIndexAdapter = new IndexAdapter(this,list);
            mCardsView.setAdapter(mIndexAdapter);
        } else {
            int count = mIndexAdapter.getCount();
            //if you want to change the UI of SwipeCardsView,you must modify the data first
            mIndexAdapter.addData(list);
            mCardsView.notifyDatasetChanged(count--);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPage = 2;
    }
}
