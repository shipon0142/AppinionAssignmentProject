package com.example.appinionassignmentproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appinionassignmentproject.R;
import com.example.appinionassignmentproject.api_collections.DcrApi;
import com.example.appinionassignmentproject.api_collections.RetrofitClient;
import com.example.appinionassignmentproject.models.Dcrclass;
import com.example.appinionassignmentproject.models.GiftList;
import com.example.appinionassignmentproject.models.LiteratureList;
import com.example.appinionassignmentproject.models.PhysicianSampleList;
import com.example.appinionassignmentproject.models.ProductGroupList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Spinner productSP;
    private Spinner literatureSP;
    private Spinner physicianSP;
    private Spinner giftSP;
    private DcrApi dcrApi;
    private TextView submitTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
            }
        });
        dcrApi = RetrofitClient.getRetrofitClient().create(DcrApi.class);
        dcrApi.getDcr().enqueue(new Callback<Dcrclass>() {
            @Override
            public void onResponse(Call<Dcrclass> call, Response<Dcrclass> response) {
                Dcrclass dcrclass = response.body();
                setProductSpinner(dcrclass);
                setLiteratureSpinner(dcrclass);
                setPhysicianSpinner(dcrclass);
                setGiftSpinner(dcrclass);

            }

            @Override
            public void onFailure(Call<Dcrclass> call, Throwable t) {

            }
        });
    }

    private void setProductSpinner(Dcrclass dcrclass) {
        List<ProductGroupList> productGroupList = dcrclass.getProductGroupList();
        ArrayList<String> products = new ArrayList<>();
        products.add("Choose");    //prompt is not working for me so I added choose first
        for (int i = 0; i < productGroupList.size(); i++) {
            products.add(productGroupList.get(i).getProductGroup());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSP.setAdapter(adapter);

    }

    private void setLiteratureSpinner(Dcrclass dcrclass) {
        List<LiteratureList> literatureList = dcrclass.getLiteratureList();
        ArrayList<String> literatures = new ArrayList<>();
        literatures.add("Choose");
        for (int i = 0; i < literatureList.size(); i++) {
            literatures.add(literatureList.get(i).getLiterature());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, literatures);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        literatureSP.setAdapter(adapter);

    }

    private void setPhysicianSpinner(Dcrclass dcrclass) {
        List<PhysicianSampleList> physicianSampleList = dcrclass.getPhysicianSampleList();
        ArrayList<String> physicians = new ArrayList<>();
        physicians.add("Choose");
        for (int i = 0; i < physicianSampleList.size(); i++) {
            physicians.add(physicianSampleList.get(i).getSample());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, physicians);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        physicianSP.setAdapter(adapter);

    }

    private void setGiftSpinner(Dcrclass dcrclass) {
        List<GiftList> giftList = dcrclass.getGiftList();
        ArrayList<String> gifts = new ArrayList<>();
        gifts.add("Choose");
        for (int i = 0; i < giftList.size(); i++) {
            gifts.add(giftList.get(i).getGift());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, gifts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        giftSP.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        productSP = findViewById(R.id.ProductSP);
        literatureSP = findViewById(R.id.LiteratureSP);
        physicianSP = findViewById(R.id.PhysicianSP);
        giftSP = findViewById(R.id.GiftSP);
        submitTV = findViewById(R.id.SubmitTV);
    }
}
