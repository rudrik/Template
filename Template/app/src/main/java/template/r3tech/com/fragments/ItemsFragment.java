package template.r3tech.com.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import template.r3tech.com.ContentDetailsActivity;
import template.r3tech.com.R;
import template.r3tech.com.fragments.adapters.ItemListAdapter;
import template.r3tech.com.model.ItemModel;
import template.r3tech.com.utils.Constant;
import template.r3tech.com.utils.DBHelper;

public class ItemsFragment extends Fragment {

    private ItemListAdapter itemListAdapter;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private List<ItemModel> lstList;
    private DBHelper dbHelper;

    public ItemsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        lstList = dbHelper.getContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemListAdapter = new ItemListAdapter(lstList);
        itemListAdapter.setOnItemClickListener(new ItemListAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(getActivity(), ContentDetailsActivity.class);
                intent.putExtra(Constant.getInstance().CURRENT_POSITION, position);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(itemListAdapter);

    }
}
