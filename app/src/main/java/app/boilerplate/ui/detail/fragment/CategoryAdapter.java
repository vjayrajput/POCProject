package app.boilerplate.ui.detail.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import app.boilerplate.databinding.ItemCategoryEmptyViewBinding;
import app.boilerplate.databinding.ItemCategoryViewBinding;
import app.boilerplate.ui.base.BaseViewHolder;
import app.boilerplate.ui.detail.TotalPrice;

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<CategoryItemViewModel> mCategoryResponseList;

    private CategoryAdapterListener mListener;

    public CategoryAdapter() {
        this.mCategoryResponseList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (!mCategoryResponseList.isEmpty()) {
            return mCategoryResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mCategoryResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemCategoryViewBinding restaurantViewBinding = ItemCategoryViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new CategoryViewHolder(restaurantViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemCategoryEmptyViewBinding emptyViewBinding = ItemCategoryEmptyViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    public void addItems(List<CategoryItemViewModel> repoList) {
        mCategoryResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCategoryResponseList.clear();
    }

    public void setListener(CategoryAdapterListener listener) {
        this.mListener = listener;
    }

    public interface CategoryAdapterListener {

        void onRetryClick();

        void onItemClick(View view, CategoryItemViewModel restaurantItemViewModel);
    }

    public class EmptyViewHolder extends BaseViewHolder implements CategoryEmptyItemViewModel.CategoryEmptyItemViewModelListener {

        private final ItemCategoryEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemCategoryEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            CategoryEmptyItemViewModel emptyItemViewModel = new CategoryEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public class CategoryViewHolder extends BaseViewHolder {

        private final ItemCategoryViewBinding mBinding;

        public CategoryViewHolder(ItemCategoryViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CategoryItemViewModel categoryItemViewModel = mCategoryResponseList.get(position);
            mBinding.setItem(categoryItemViewModel);

            mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryItemViewModel.quantity.get() < categoryItemViewModel.maxQuantity.get()) {
                        categoryItemViewModel.quantity.set(categoryItemViewModel.quantity.get() + 1);
                        EventBus.getDefault().post(new TotalPrice());
                    }
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
