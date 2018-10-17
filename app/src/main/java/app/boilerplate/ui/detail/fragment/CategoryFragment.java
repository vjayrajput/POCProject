package app.boilerplate.ui.detail.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import app.boilerplate.BR;
import app.boilerplate.R;
import app.boilerplate.data.model.api.Item;
import app.boilerplate.databinding.FragmentCategoryBinding;
import app.boilerplate.ui.base.BaseFragment;
import app.boilerplate.ui.restaurant.fragment.RestaurantFragment;
import app.boilerplate.utils.Constants;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel>
        implements CategoryNavigator, CategoryAdapter.CategoryAdapterListener {
    private static final String TAG = RestaurantFragment.class.getSimpleName();

    FragmentCategoryBinding mFragmentCategoryBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    CategoryAdapter mCategoryAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    CategoryViewModel mCategoryViewModel;

    private ArrayList<Item> items;


    public static CategoryFragment newInstance(ArrayList<Item> items) {
        CategoryFragment restaurantFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.KEY_MENU_ITEMS, items);
        restaurantFragment.setArguments(args);
        return restaurantFragment;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.KEY_MENU_ITEMS, items);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            items = savedInstanceState.getParcelableArrayList(Constants.KEY_MENU_ITEMS);
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public CategoryViewModel getViewModel() {
        return mCategoryViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryViewModel.setNavigator(this);
        mCategoryAdapter.setListener(this);
    }


    @Override
    public void onRetryClick() {
    }

    @Override
    public void onItemClick(View view, CategoryItemViewModel restaurantItemViewModel) {

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        items = getArguments().getParcelableArrayList(Constants.KEY_MENU_ITEMS);
        mFragmentCategoryBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCategoryBinding.categoryRecyclerView.setHasFixedSize(true);
        mFragmentCategoryBinding.categoryRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentCategoryBinding.categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentCategoryBinding.categoryRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryViewModel.setRestaurantType(items);
    }


    private void subscribeToLiveData() {
        mCategoryViewModel.getCategoryRepos().observe(this,
                categoryItemViewModels -> mCategoryViewModel.addCategoryItemsToList(categoryItemViewModels));
    }


}
