//package com.example.galleryai_diplom;
//
//import android.os.Bundle;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
////поиск лиц(пока не использую)
//public class SearchResultsActivity extends AppCompatActivity {
//    private RecyclerView resultsRecyclerView;
//    private ImageAdapter adapter;
//    private List<String> similarImages;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_results);
//
//        similarImages = getIntent().getStringArrayListExtra("similarImages");
//        if (similarImages == null) {
//            similarImages = new ArrayList<>();
//        }
//
//        resultsRecyclerView = findViewById(R.id.resultsRecyclerView);
//        resultsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new ImageAdapter(this, similarImages, null); // Слушатель не нужен
//        resultsRecyclerView.setAdapter(adapter);
//    }
//}