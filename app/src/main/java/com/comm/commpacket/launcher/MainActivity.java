package com.comm.commpacket.launcher;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.comm.commpacket.ActivityMainBinding;
import com.comm.commpacket.CommItemListBinding;
import com.comm.commpacket.R;
import com.comm.commpacket.adapter.CommListAdapter;
import com.comm.commpacket.listener.ListItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListItemOnClickListener {

    ActivityMainBinding binding;
    ListView listView;
    List<Object> list;
    CommListAdapter<Object> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        listView = binding.list;
        InitData();
    }

    public void InitData()
    {
        list = new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            list.add(new Object());
        }
        listAdapter = new CommListAdapter<Object>(this,list,R.layout.item_list_view) {

            @Override
            protected int SelectTypeIndex(int position, Object data) {

                return 0;
            }

            @Override
            protected void OnBindData(ViewHolder viewHolder) {
                if(viewHolder.index%2==0) {
                    CommItemListBinding itemBinding = (CommItemListBinding) viewHolder.viewDataBinding;
                    itemBinding.tvItem.setText("" + viewHolder.index + "   lsdkjfksjdfkljsldf");
                }
            }
        };
        listAdapter.SetItemClickListener(this);

        listView.setAdapter(listAdapter);
    }


    @Override
    public void onItemClickListener(Object data, ViewDataBinding binding) {
        CommItemListBinding itemBinding = (CommItemListBinding)binding;
        itemBinding.tvItem.setText(itemBinding.tvItem.getText()+"   click");
    }
}
