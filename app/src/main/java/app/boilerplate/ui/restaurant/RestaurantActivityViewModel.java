package app.boilerplate.ui.restaurant;

import android.arch.lifecycle.MutableLiveData;

import app.boilerplate.data.DataManager;
import app.boilerplate.ui.base.BaseViewModel;
import app.boilerplate.utils.rx.SchedulerProvider;

public class RestaurantActivityViewModel extends BaseViewModel {
    private MutableLiveData<Integer> currentPage;
    private MutableLiveData<Integer> tabSelected;

    public RestaurantActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public MutableLiveData<Integer> getCurrentPage() {
        if (currentPage == null) {
            currentPage = new MutableLiveData<Integer>();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage.setValue(currentPage);
    }


    public MutableLiveData<Integer> getTabSelected() {
        if (tabSelected == null) {
            tabSelected = new MutableLiveData<Integer>();
        }
        return tabSelected;
    }

    public void setTabSelected(int tabSelected) {
        this.tabSelected.setValue(tabSelected);
    }


    public void pageSelected(int pos) {
        setTabSelected(pos);
    }

    public void popularTabSelected() {
        setTabSelected(0);
    }

    public void newTabSelected() {
        setTabSelected(1);
    }

    public void fastestTabSelected() {
        setTabSelected(2);
    }

    public void favouriteTabSelected() {
        setTabSelected(3);
    }
}
