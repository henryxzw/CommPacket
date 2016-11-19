package com.comm.commpacket.launcher;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.comm.commpacket.ActivityMainBinding;
import com.comm.commpacket.CommItemListBinding;
import com.comm.commpacket.R;
import com.comm.commpacket.adapter.CommListAdapter;
import com.comm.commpacket.adapter.CommReAdapter;
import com.comm.commpacket.listener.ListItemOnClickListener;
import com.comm.commpacket.listener.RecyItemOnClickListener;
import com.comm.commpacket.method.AppKeyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ListItemOnClickListener ,RecyItemOnClickListener{

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
        AppKeyMap.GetInstance().GetSharedPreferences(this,"name");


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
        RecyclerView recyclerView = binding.recycle;
        CommReAdapter<Object> adapter = new CommReAdapter<Object>(this,list,R.layout.item_list_view) {
            @Override
            protected int SelectType(Object o) {
                return 0;
            }

            @Override
            protected void BindItemData(ViewHolder holder, int position) {
                CommItemListBinding itemBinding = (CommItemListBinding)holder.viewDataBinding;
                itemBinding.tvItem.setText(""+position+" hahhhh");
            }
        };
        adapter.SetOnBindItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClickListener(Object data, ViewDataBinding binding) {
        CommItemListBinding itemBinding = (CommItemListBinding)binding;
        itemBinding.tvItem.setText(itemBinding.tvItem.getText()+"   click");
    }

    @Override
    public void OnBindItemClickListener(Object data, ViewDataBinding viewDataBinding) {
        GalleryFinal.openGalleryMuti(101, 3, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                switch (reqeustCode)
                {
                    case 101:
                    {
                        for(int i=0;i<resultList.size();i++) {
                            Log.e("rr", resultList.get(i).getPhotoPath());
                        }
                    }
                        break;
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
//        GalleryFinal.openCamera(101, new GalleryFinal.OnHanlderResultCallback() {
//            @Override
//            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                switch (reqeustCode)
//                {
//                    case 101:
//
//                        break;
//                }
//            }
//
//            @Override
//            public void onHanlderFailure(int requestCode, String errorMsg) {
//
//            }
//        });
    }
}
