package id.ac.binus.wiktionic;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.wiktionic.Models.APIResponses;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<APIResponses>> call = callDictionary.callMeanings(word);

        try{
            call.enqueue(new Callback<List<APIResponses>>() {
                @Override
                public void onResponse(Call<List<APIResponses>> call, Response<List<APIResponses>> response) {
                    if(!response.isSuccessful()){
                        MainActivity.progressDialog.dismiss();
                        Toast.makeText(context, "Word doesn't exist!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponses>> call, Throwable t) {
                    listener.onError("Request failed!");
                }
            });
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(context, "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallDictionary{
        @GET("entries/en/{word}")
        Call<List<APIResponses>> callMeanings(
                @Path("word") String word
        );
    }
}
