package app.akeed.ui.restaurant;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.model.Restaurant;
import app.akeed.ui.common.ShopStatus;
import app.akeed.ui.listener.OnRestaurantItemClickListener;
import app.akeed.utils.CustomTypefaceSpan;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    private final OnRestaurantItemClickListener listener;
    Context mContext;
    private ArrayList<Restaurant> dataSet;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data, OnRestaurantItemClickListener listener) {
        this.mContext = context;
        this.dataSet = data;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Restaurant restaurant = dataSet.get(position);
        holder.bind(restaurant, listener);
    }

    public SpannableString getFormattedName(String name, String address) {
        Typeface nameFont = ResourcesCompat.getFont(mContext, R.font.avenir_ltstd_black);
        Typeface addressFont = ResourcesCompat.getFont(mContext, R.font.avenir_ltstd_light);

        int nameTextSize = (int) mContext.getResources().getDimension(R.dimen.sp13);
        int addressTextSize = (int) mContext.getResources().getDimension(R.dimen.sp10);

        String str = name + " " + address;
        SpannableString ss1 = new SpannableString(str);
        ss1.setSpan(new AbsoluteSizeSpan(nameTextSize), 0, name.length(), SPAN_INCLUSIVE_INCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(addressTextSize), name.length() + 1, str.length(), SPAN_INCLUSIVE_INCLUSIVE);
        ss1.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.scootsy_black)), 0, name.length(), 0);
        ss1.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.scootsy_black)), name.length() + 1, str.length(), 0);
        ss1.setSpan(new CustomTypefaceSpan("", nameFont), 0, name.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new CustomTypefaceSpan("", addressFont), name.length() + 1, str.length(), SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss1;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_type)
        TextView txtType;
        @BindView(R.id.img_favourite)
        ImageView imgFavourite;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.btn_status)
        TextView btnStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Restaurant restaurant, final OnRestaurantItemClickListener listener) {
            txtName.setText(getFormattedName(restaurant.getName(), restaurant.getAddress()));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < restaurant.getFoodTypes().size(); i++) {
                sb.append("\u25CF ");
                sb.append(restaurant.getFoodTypes().get(i));
                sb.append("   ");
            }
            txtType.setText(sb);
            Picasso.get().load(restaurant.getImageUrl()).into(image);


            if (restaurant.getOpenStatus().equals(ShopStatus.CLOSE)) {
                btnStatus.setText(mContext.getResources().getString(R.string.shop_status_close));
            } else if (restaurant.getOpenStatus().equals(ShopStatus.OPEN)) {
                btnStatus.setText(mContext.getResources().getString(R.string.shop_status_open));
            } else if (restaurant.getOpenStatus().equals(ShopStatus.PRE_OPEN)) {
                btnStatus.setText(mContext.getResources().getString(R.string.shop_status_preorder));
            }

            imgFavourite.setImageResource(restaurant.getIsFavourite() == 0 ? R.drawable.ic_unfavorite : R.drawable.ic_favorite);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, restaurant);
                }
            });


            imgFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restaurant.setIsFavourite(restaurant.getIsFavourite() == 0 ? 1 : 0);
                    notifyDataSetChanged();
                }
            });


        }
    }
}
