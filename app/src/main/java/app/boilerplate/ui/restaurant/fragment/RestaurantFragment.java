package app.boilerplate.ui.restaurant.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import app.boilerplate.BR;
import app.boilerplate.R;
import app.boilerplate.databinding.FragmentRestaurantBinding;
import app.boilerplate.ui.base.BaseFragment;
import app.boilerplate.ui.detail.RestaurantDetailActivity;
import app.boilerplate.utils.Constants;

public class RestaurantFragment extends BaseFragment<FragmentRestaurantBinding, RestaurantViewModel>
        implements RestaurantNavigator, RestaurantAdapter.RestaurantAdapterListener {

    private static final String TAG = RestaurantFragment.class.getSimpleName();
    FragmentRestaurantBinding mFragmentRestaurantBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    RestaurantAdapter mRestaurantAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    RestaurantViewModel mRestaurantViewModel;

    private int type;

    public static RestaurantFragment newInstance(int type) {
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_TYPE, type);
        restaurantFragment.setArguments(args);
        return restaurantFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_TYPE, type);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            type = savedInstanceState.getInt(Constants.KEY_TYPE);
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_restaurant;
    }

    @Override
    public RestaurantViewModel getViewModel() {
        mRestaurantViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RestaurantViewModel.class);
        return mRestaurantViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestaurantViewModel.setNavigator(this);
        mRestaurantAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mRestaurantViewModel.fetchRestaurantList();
    }

    @Override
    public void onItemClick(View view, RestaurantItemViewModel restaurantItemViewModel) {
        Intent intent = RestaurantDetailActivity.newIntent(getBaseActivity());
        intent.putExtra("restaurant", restaurantItemViewModel);
        startActivity(intent);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt(Constants.KEY_TYPE, 0);
        mFragmentRestaurantBinding = getViewDataBinding();
        mRestaurantViewModel.setRestaurantType(type);
        setUp();
        subscribeToLiveData();
    }


    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentRestaurantBinding.restaurantRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentRestaurantBinding.restaurantRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentRestaurantBinding.restaurantRecyclerView.setAdapter(mRestaurantAdapter);
    }

    private void subscribeToLiveData() {
        mRestaurantViewModel.getRestaurantRepos().observe(this,
                restaurantItemViewModels -> mRestaurantViewModel.addRestaurantItemsToList(restaurantItemViewModels));
    }

    @Override
    public void openRestaurantDetailActivity() {

    }

}
