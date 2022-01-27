package id.ac.binus.wiktionic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;

import id.ac.binus.wiktionic.Adapters.MeaningsAdapter;
import id.ac.binus.wiktionic.Adapters.PhoneticsAdapter;
import id.ac.binus.wiktionic.Models.APIResponses;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textView_word;
    RecyclerView recyclerview_phonetics, recyclerview_meanings;
    public static ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningsAdapter meaningsAdapter;
    Button btnSignOut;

    private final String TAG = "Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        searchView = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recyclerview_phonetics = findViewById(R.id.recyclerview_phonetics);
        recyclerview_meanings = findViewById(R.id.recyclerview_meanings);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "Hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Ads Kit
        HwAds.init(MainActivity.this);
        BannerView bannerView = findViewById(R.id.hw_banner_view);
        bannerView.setBannerRefresh(60);
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);

        btnSignOut = findViewById(R.id.signoutBtn);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task<Void> task = LoginActivity.mAuthService.cancelAuthorization();
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                        Log.i(TAG, "cancelAuthorization success");
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.i(TAG, "cancelAuthorization failure:" + e.getClass().getSimpleName());
                    }
                });
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponses apiResponses, String message) {
            progressDialog.dismiss();
            if(apiResponses==null){
                Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponses);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
//            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponses apiResponses) {
//        textView_word.setText("Pronunciation: " + apiResponses.getWords());

        recyclerview_phonetics.setHasFixedSize(true);
        recyclerview_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponses.getPhonetics());
        recyclerview_phonetics.setAdapter(phoneticsAdapter);

        recyclerview_meanings.setHasFixedSize(true);
        recyclerview_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningsAdapter = new MeaningsAdapter(this, apiResponses.getMeanings());
        recyclerview_meanings.setAdapter(meaningsAdapter);
    }
}