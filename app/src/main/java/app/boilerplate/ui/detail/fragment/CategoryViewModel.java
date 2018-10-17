package app.boilerplate.ui.detail.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

import app.boilerplate.data.DataManager;
import app.boilerplate.data.model.api.Item;
import app.boilerplate.ui.base.BaseViewModel;
import app.boilerplate.utils.rx.SchedulerProvider;

public class CategoryViewModel extends BaseViewModel<CategoryNavigator> {
    private static final String TAG = CategoryViewModel.class.getSimpleName();

    private final ObservableList<CategoryItemViewModel> categoryItemViewModels = new ObservableArrayList<>();

    private final MutableLiveData<List<CategoryItemViewModel>> categoryItemsLiveData;

    private ArrayList<Item> items;

    public CategoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        categoryItemsLiveData = new MutableLiveData<>();
    }

    public void setRestaurantType(ArrayList<Item> items) {
        this.items = items;
        categoryItemsLiveData.setValue(getViewModelList(items));
    }

    public void addCategoryItemsToList(List<CategoryItemViewModel> openSourceItems) {
        categoryItemViewModels.clear();
        categoryItemViewModels.addAll(openSourceItems);
    }

    public ObservableList<CategoryItemViewModel> getCategoryItemViewModels() {
        return categoryItemViewModels;
    }

    public MutableLiveData<List<CategoryItemViewModel>> getCategoryRepos() {
        return categoryItemsLiveData;
    }

    public List<CategoryItemViewModel> getViewModelList(ArrayList<Item> data) {
        List<CategoryItemViewModel> categoryItemViewModels = new ArrayList<>();
        for (Item item : data) {
            categoryItemViewModels.add(new CategoryItemViewModel(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getDisplayPrice(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getMaxQuantity(),
                    item.getType()));
        }
        return categoryItemViewModels;
    }

}
