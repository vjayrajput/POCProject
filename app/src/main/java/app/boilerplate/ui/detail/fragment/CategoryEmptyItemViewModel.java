package app.boilerplate.ui.detail.fragment;

public class CategoryEmptyItemViewModel {

    private final CategoryEmptyItemViewModelListener mListener;

    public CategoryEmptyItemViewModel(CategoryEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface CategoryEmptyItemViewModelListener {

        void onRetryClick();
    }
}
