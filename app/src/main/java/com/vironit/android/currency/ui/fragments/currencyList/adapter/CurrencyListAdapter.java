package com.vironit.android.currency.ui.fragments.currencyList.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.ArrayList;
import java.util.List;

import nz.co.trademe.covert.Covert;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.MyViewHolder> {

    private ArrayList<CryptoCurrencyResponseUI> mDataSet;
    private Covert covert;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mFullNameTv;
        TextView mShortNameTv;
        TextView mUsdPriceTv;
        TextView mVolumeTv;
        ImageView mLogoIv;
        ImageView mArrowIv;
        View mItemView;

        MyViewHolder(View v) {
            super(v);
            mShortNameTv = v.findViewById(R.id.tv_item_short_name);
            mFullNameTv = v.findViewById(R.id.tv_item_full_name);
            mUsdPriceTv = v.findViewById(R.id.tv_item_usd_price);
            mVolumeTv = v.findViewById(R.id.tv_item_volume);
            mLogoIv = v.findViewById(R.id.iv_item_icon);
            mArrowIv = v.findViewById(R.id.iv_item_arrow);
            mItemView = v.findViewById(R.id.item_layout);
        }

        void onBind(final CryptoCurrencyResponseUI data) {
            mFullNameTv.setText(data.getName());
            mShortNameTv.setText(data.getSymbol());
            mUsdPriceTv.setText(String.valueOf(data.getPrice()));
            mVolumeTv.setText(String.valueOf(data.getVolume24h()));
            mArrowIv.setImageDrawable(
                    getArrowDrawable(mArrowIv.getContext(),
                            data.getPercentChange24h()));
            Glide.with(mLogoIv.getContext())
                    .load(data.getLogoUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mLogoIv);
        }
    }

    private Drawable getArrowDrawable(Context context, Double percentChange24h) {
        if (percentChange24h > 0) {
            return context.getDrawable(R.drawable.ic_arrow_upward_black_24dp);
        } else {
            return context.getDrawable(R.drawable.ic_arrow_downward_black_24dp);
        }
    }


    public CurrencyListAdapter(Covert covert) {
        this.covert = covert;
        mDataSet = new ArrayList<>();
    }

    public void addCryptoList(ArrayList<CryptoCurrencyResponseUI> response) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new CurrencyDiffCallback(this.mDataSet, response));
        mDataSet.clear();
        mDataSet.addAll(response);
        diffResult.dispatchUpdatesTo(this);
    }

    public Integer getCryptoIdByPosition(Integer adapterPosition){
        return mDataSet.get(adapterPosition).getCryptoId();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_currency_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        covert.drawCornerFlag(holder);
        holder.onBind(mDataSet.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.contains(Covert.SKIP_FULL_BIND_PAYLOAD)) {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mDataSet != null)
            return mDataSet.size();
        else return 0;
    }
}
