package com.vintech.shieldsecurity.main.result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vintech.shieldsecurity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincent on 2016/10/22.
 */

public class OneKeyResultAdapter extends RecyclerView.Adapter<OneKeyResultAdapter.AdapterViewHolder> {
    private List<OneKeyResultBean> mData = new ArrayList<OneKeyResultBean>();

    public OneKeyResultAdapter() {
        for (int i = 0; i < 50; i++) {
            mData.add(new OneKeyResultBean());
        }
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layer_onekey_result_item, null);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {

    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            mTextView = null;
        }
    }
}
