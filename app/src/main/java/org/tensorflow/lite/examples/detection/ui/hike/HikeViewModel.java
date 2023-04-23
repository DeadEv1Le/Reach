package org.tensorflow.lite.examples.detection.ui.hike;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HikeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HikeViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}