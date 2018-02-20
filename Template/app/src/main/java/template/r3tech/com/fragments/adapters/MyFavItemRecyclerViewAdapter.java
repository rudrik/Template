package template.r3tech.com.fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import template.r3tech.com.R;
import template.r3tech.com.fragments.dummy.DummyContent.DummyItem;

public class MyFavItemRecyclerViewAdapter extends RecyclerView.Adapter<MyFavItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private static onItemClickListener onItemClickListener;

    public MyFavItemRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;

        DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mIdView.setOnClickListener(this);
            mContentView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }


        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface onItemClickListener {
        void onItemClicked(View view, int position);
    }
}
