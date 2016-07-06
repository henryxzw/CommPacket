package com.comm.commpacket.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.comm.commpacket.listener.ListItemOnClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public abstract class CommListAdapter<T> extends BaseAdapter {
    private List<T> list;
    private Context context;
    private int[] resIds;
    private ListItemOnClickListener<T> onClickListener;

    public CommListAdapter(Context context,List<T> list,int... resIds)
    {
        this.context = context;
        this.list = list;
        this.resIds = resIds;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null || resIds.length>1)
        {
            viewHolder = new ViewHolder(resIds[SelectTypeIndex(position,list.get(position))],position,list.get(position));
            convertView = viewHolder.itemView;
            if(onClickListener!=null)
            {
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         onClickListener.onItemClickListener(viewHolder.data,viewHolder.viewDataBinding);
                     }
                 });
            }
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OnBindData(viewHolder);
        return viewHolder.itemView;
    }

    public void SetItemClickListener(ListItemOnClickListener<T> listener)
    {
        this.onClickListener = listener;
    }

   protected abstract int SelectTypeIndex(int position,T data);
   protected abstract void OnBindData(ViewHolder viewHolder);

    public class ViewHolder
    {
        public int index;
        public T data;
        public View itemView;
        public ViewDataBinding viewDataBinding;
        public ViewHolder(int resId,int index,T data)
        {
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),resId,null,false);
            itemView = viewDataBinding.getRoot();
            this.index = index;
            this.data = data;
        }
    }
}
