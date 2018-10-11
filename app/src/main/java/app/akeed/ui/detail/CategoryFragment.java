package app.akeed.ui.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.databinding.FragmentCategoryBinding;
import app.akeed.model.Item;
import app.akeed.utils.Constants;

public class CategoryFragment extends Fragment {
    FragmentCategoryBinding binding;
    private ArrayList<Item> items;

    public CategoryFragment() {
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        items = getArguments().getParcelableArrayList(Constants.KEY_MENU_ITEMS);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        ItemAdapter adapter = new ItemAdapter(items);
        binding.recyclerView.setAdapter(adapter);
    }

}
