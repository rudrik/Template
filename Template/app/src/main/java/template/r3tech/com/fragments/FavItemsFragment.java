package template.r3tech.com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import template.r3tech.com.R;
import template.r3tech.com.fragments.adapters.FavListAdapter;

public class FavItemsFragment extends Fragment {
    private FavListAdapter myItemRecyclerViewAdapter;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    public FavItemsFragment() {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myItemRecyclerViewAdapter = new FavListAdapter(DummyContent.ITEMS);
        myItemRecyclerViewAdapter.setOnItemClickListener(new FavListAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(getActivity(), DummyContent.ITEMS.get(position).content, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(myItemRecyclerViewAdapter);

    }
}
