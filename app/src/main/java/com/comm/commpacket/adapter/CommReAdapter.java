package com.comm.commpacket.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.comm.commpacket.listener.RecyItemOnClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public abstract class CommReAdapter<T> extends RecyclerView.Adapter implements View.OnClickListener{
    private Context context;
    private List<T> list;
    private int[] resIds;

    private RecyItemOnClickListener listener;

    public CommReAdapter(Context context,List<T> list,int... resIds)
    {
        this.context = context;
        this.list = list;
        this.resIds = resIds;
    }

    @Override
    public int getItemViewType(int position) {
        if(resIds.length>0)
        {
            return SelectType(list.get(position));
        }
        else
        {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(resIds.length>1)
        {
            ViewHolder<T> viewHolder = ViewHolder.OnCreateViewHolder(resIds[viewType],context, parent);

             viewHolder.parentView.setOnClickListener(this);
            return viewHolder;
        }
        else {
            ViewHolder<T> viewHolder =ViewHolder.OnCreateViewHolder(resIds[0], context, parent);
            viewHolder.parentView.setOnClickListener(this);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.data = list.get(position);
         viewHolder.parentView.setTag(viewHolder);
        BindItemData(viewHolder,position);
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)
        {
            ViewHolder viewHolder = (ViewHolder) v.getTag();

            listener.OnBindItemClickListener(viewHolder.data,viewHolder.viewDataBinding);
        }
    }

    protected abstract int SelectType(T t);

    protected abstract void BindItemData(ViewHolder holder, int position);


    public void SetOnBindItemClick(RecyItemOnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  ViewHolder<T> extends RecyclerView.ViewHolder
    {

        public  ViewDataBinding viewDataBinding;
        public  T data;
        public View parentView;

        public ViewHolder(View itemView)
        {
            super(itemView);
        }

        public static ViewHolder OnCreateViewHolder(int resId,Context context,ViewGroup parent){
           ViewDataBinding  viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), resId,parent,false);
            ViewHolder holder = new ViewHolder(viewDataBinding.getRoot());
            holder.viewDataBinding = viewDataBinding;
            holder.parentView = viewDataBinding.getRoot();
            return holder;
        }
    }
}
