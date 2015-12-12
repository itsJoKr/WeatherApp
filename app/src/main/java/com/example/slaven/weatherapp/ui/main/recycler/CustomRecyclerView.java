package com.example.slaven.weatherapp.ui.main.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by JK on 29.8.2015..
 */
public class CustomRecyclerView extends RecyclerView {

    private RecyclerScrollComm scrollComm;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void registerOnScrollListener(RecyclerScrollComm scrollListener){
        scrollComm = scrollListener;
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (scrollComm!=null){
            scrollComm.onRecycleScrolled(state);
        }
    }
}
