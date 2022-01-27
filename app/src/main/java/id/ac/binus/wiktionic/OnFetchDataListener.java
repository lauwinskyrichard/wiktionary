package id.ac.binus.wiktionic;

import id.ac.binus.wiktionic.Models.APIResponses;

public interface OnFetchDataListener {
    void onFetchData(APIResponses apiResponses, String message);
    void onError(String message);
}
