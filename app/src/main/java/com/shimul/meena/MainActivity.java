package com.shimul.meena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    List<Product> products;
    RecyclerView recyclerView;
    ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fetchData();





    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.meenaclick.com/api/catalog-contents/1/").addConverterFactory(GsonConverterFactory.create())
                .build();

        MeenaProductClient service = retrofit.create(MeenaProductClient.class);

        Call<Response> response = service.listProducts();

        response.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int size = response.body().getProducts().size();
                Product product = null;
                for(int i = 0; i<size; i++){
                    product = response.body().getProducts().get(i);
                    products.add(product);
                    mAdapter.notifyDataSetChanged();




                }


                recyclerView.setAdapter(mAdapter);






            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v("RESPONSE", String.valueOf(t.getLocalizedMessage()));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.actionbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.search){
            Toast.makeText(this, "You selected Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.cart){
            Toast.makeText(this, "You selected cart", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        toolbar = findViewById(R.id.toolBar);
        products = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.productRV);
        mAdapter = new ProductAdapter(products);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);


    }
}
