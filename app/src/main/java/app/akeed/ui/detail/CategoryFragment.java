package app.akeed.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends Fragment {
    ArrayList<Item> items;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    public CategoryFragment() {
    }


    public static CategoryFragment newInstance(ArrayList<Item> items) {
        CategoryFragment restaurantFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("menu_items", items);
        restaurantFragment.setArguments(args);
        return restaurantFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        items = getArguments().getParcelableArrayList("menu_items");
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ItemAdapter adapter = new ItemAdapter(getContext(), items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
