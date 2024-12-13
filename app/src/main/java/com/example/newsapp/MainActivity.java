package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newsapp.Adapters.NewsAdapters;
import com.example.newsapp.Api.ApiClient;
import com.example.newsapp.Interfaces.ApiServices;
import com.example.newsapp.Models.NewResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<NewResponse.Article> newsList = new ArrayList<>();
    NewsAdapters adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiServices apiService = ApiClient.getApiService();

        //dropdown
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<String> adaptercat = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                Arrays.asList("All", "business", "entertainment", "general", "health","science","sports","technology")
        );
        adaptercat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adaptercat);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                if(!selectedCategory.equals("ALL")) {
                    newsList.clear();
                    adapter.notifyDataSetChanged();
                }
                apiService.getDataByCategories(selectedCategory.toString()).enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            List<NewResponse.Article> data = response.body().getArticles();
                            System.out.println("fetching data");
                            Log.d("ApiActivity", "Données reçues : " + data.toString());
                            for(int i=0 ;i<20;i++){
                                NewResponse.Article originalArticle = data.get(i);
                                String dateFormat=originalArticle.getPublishedAt().split("T")[0];


                                NewResponse.Article newArticle = new NewResponse.Article(
                                        originalArticle.getTitle(),
                                        dateFormat,
                                        originalArticle.getContent(),
                                        originalArticle.getUrlToImage()

                                );


                                newsList.add(newArticle);
                                adapter.notifyDataSetChanged();



                                Log.d("ApiActivity", "New Article Instance: " + newArticle.toString());



                            }








                            //Log.d("ApiActivity", "Données reçues : " + newsList.toString());

                            //adapter.notifyDataSetChanged();


                            //Log.d("ApiActivity", "Données reçues : " + newsList.toString());
                        }
                        else {

                            Log.e("ApiActivity", "Erreur dans la réponse : " + response.code());
                        }
                    }


                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {

                        Log.e("ApiActivity", "Erreur  : ", t);
                       // Toast.makeText(MainActivity.this, "Échec de", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });



        //fetchina data
        // NewResponse.Article originalArticlee=new NewResponse.Article("aa","aa","aa","aa");
        // newsList.add(originalArticlee);

        apiService.getData().enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<NewResponse.Article> data = response.body().getArticles();
                    System.out.println("fetching data");
                    Log.d("ApiActivity", "Données reçues : " + data.toString());
                    for(int i=0 ;i<20;i++){
                        NewResponse.Article originalArticle = data.get(i);
                        String dateFormat=originalArticle.getPublishedAt().split("T")[0];


                        NewResponse.Article newArticle = new NewResponse.Article(
                                originalArticle.getTitle(),
                                dateFormat,
                                originalArticle.getContent(),
                                originalArticle.getUrlToImage()

                        );


                        newsList.add(newArticle);
                        adapter.notifyDataSetChanged();



                        Log.d("ApiActivity", "New Article Instance: " + newArticle.toString());



                    }








                    //Log.d("ApiActivity", "Données reçues : " + newsList.toString());

                    //adapter.notifyDataSetChanged();


                    //Log.d("ApiActivity", "Données reçues : " + newsList.toString());
                }
                else {

                    Log.e("ApiActivity", "Erreur dans la réponse : " + response.code());
                   // Toast.makeText(MainActivity.this, "Erreur de chargement des données", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {

                Log.e("ApiActivity", "Erreur  : ", t);
               // Toast.makeText(MainActivity.this, "Échec de", Toast.LENGTH_SHORT).show();
            }
        });


        //configuration lista
        adapter = new NewsAdapters(this, newsList);
        RecyclerView recyclerView = findViewById(R.id.news_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}