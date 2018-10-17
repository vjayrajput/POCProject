package app.boilerplate.ui.restaurant.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.boilerplate.databinding.ItemRestaurantEmptyViewBinding;
import app.boilerplate.databinding.ItemRestaurantViewBinding;
import app.boilerplate.ui.base.BaseViewHolder;

public class RestaurantAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<RestaurantItemViewModel> mRestaurantResponseList;

    private RestaurantAdapterListener mListener;


    public RestaurantAdapter() {
        this.mRestaurantResponseList = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        if (!mRestaurantResponseList.isEmpty()) {
            return mRestaurantResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mRestaurantResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemRestaurantViewBinding restaurantViewBinding = ItemRestaurantViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new RestaurantViewHolder(restaurantViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemRestaurantEmptyViewBinding emptyViewBinding = ItemRestaurantEmptyViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<RestaurantItemViewModel> repoList) {
        mRestaurantResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mRestaurantResponseList.clear();
    }

    public void setListener(RestaurantAdapterListener listener) {
        this.mListener = listener;
    }

    public interface RestaurantAdapterListener {

        void onRetryClick();

        void onItemClick(View view, RestaurantItemViewModel restaurantItemViewModel);
    }

    public class EmptyViewHolder extends BaseViewHolder implements RestaurantEmptyItemViewModel.RestaurantEmptyItemViewModelListener {

        private final ItemRestaurantEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemRestaurantEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            RestaurantEmptyItemViewModel emptyItemViewModel = new RestaurantEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public class RestaurantViewHolder extends BaseViewHolder {

        private final ItemRestaurantViewBinding mBinding;

        public RestaurantViewHolder(ItemRestaurantViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final RestaurantItemViewModel restaurantItemViewModel = mRestaurantResponseList.get(position);
            mBinding.setViewModel(restaurantItemViewModel);
            mBinding.imgFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restaurantItemViewModel.isFavourite.set(0 == restaurantItemViewModel.isFavourite.get() ? 1 : 0);
                }
            });

            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(view, restaurantItemViewModel);
                }
            });
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

    }

}
