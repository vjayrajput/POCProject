package app.akeed.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private ArrayList<Item> items = new ArrayList<>();

    private Context mContext;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_menu, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.btn_add)
        TextView btnAdd;
        @BindView(R.id.txt_quantity)
        TextView txtQuantity;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Item item) {
            txtName.setText(item.getName());
            txtDescription.setText(item.getDescription());
            if (item.getDescription().isEmpty()) {
                txtDescription.setVisibility(View.GONE);
            }
            txtPrice.setText(mContext.getResources().getString(R.string.item_price, item.getPrice()));
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getQuantity() < item.getMaxQuantity()) {
                        item.setQuantity(item.getQuantity() + 1);
                        notifyDataSetChanged();
                    }
                }
            });
            txtQuantity.setVisibility(item.getQuantity() <= 0 ? View.GONE : View.VISIBLE);
            txtQuantity.setText(String.valueOf(item.getQuantity()));
        }
    }
}