package activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsforyou.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Adapter.PopularAdapter;
import Domain.Category_Domain;
import Domain.Food_Domain;

public class MainActivity2 extends AppCompatActivity {
   private RecyclerView.Adapter adapter,adapter2;
   private RecyclerView recyclerviewCategoryList , recyclerViewPopularList;
   TextView homeText, homeTextOrange;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//
//        homeText = findViewById(R.id.homeText);
//        homeTextOrange = findViewById(R.id.homeTextOrange);
//
//        homeTextOrange.setVisibility(View.VISIBLE);
//        homeText.setVisibility(View.GONE);

        recyclerviewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout linearLayoutBody = findViewById(R.id.linearLayoutBody);
        TextView   homeText = findViewById(R.id.homeText);
        TextView  homeTextOrange = findViewById(R.id.homeTextOrange);
        TextView  profileText = findViewById(R.id.profileText);
        TextView  profileTxOrange = findViewById(R.id.profilTxOrang);

        linearLayoutBody.setVisibility(View.VISIBLE);
        profileBtn.setVisibility(View.VISIBLE);
        homeTextOrange .setVisibility(View.VISIBLE);
        homeText.setVisibility(View.GONE);
        profileText .setVisibility(View.VISIBLE);
        profileTxOrange.setVisibility(View.GONE);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutBody.setVisibility(View.GONE);

                homeTextOrange.setVisibility(View.GONE);
                homeText .setVisibility(View.VISIBLE);
                profileTxOrange .setVisibility(View.VISIBLE);
                profileText.setVisibility(View.GONE);


            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity2.this, CartListActivity.class));
                linearLayoutBody.setVisibility(View.VISIBLE);
                homeTextOrange.setVisibility(View.VISIBLE);
                homeText .setVisibility(View.GONE);
                profileTxOrange.setVisibility(View.GONE);
                profileText.setVisibility(View.VISIBLE);

            }
        });
    }

    private void recyclerviewCategory() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerviewCategoryList=findViewById(R.id.recyclerview);
        recyclerviewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<Category_Domain>category=new ArrayList<>();
        category.add(new Category_Domain("Pizza","cat_1"));
        category.add(new Category_Domain("Burger","cat_2"));
        category.add(new Category_Domain("Hotdog","cat_3"));
        category.add(new Category_Domain("Drink","cat_4"));

        category.add(new Category_Domain("Donut","cat_5"));


        adapter=new CategoryAdapter(category);
        recyclerviewCategoryList.setAdapter(adapter);


    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerview2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<Food_Domain> foodList = new ArrayList<>();
        foodList.add(new Food_Domain("Pepperoni pizza","pop_1","slices pepperoni, mozzerella cheese, fresh orange, ground black pepper, pizza sauce",132.00));
        foodList.add(new Food_Domain("Cheese Burger","pop_2","beef, Gouda Cheese, Special Sauce , Lettuce, tomato",144.00));
        foodList.add(new Food_Domain("Vegetable pizza","pop_3","olive oil, vegetable oil, pitted kalamata, cherry tomatoes, fresh orange, basil",175.00));
        foodList.add(new Food_Domain("Cheese Burger","pop_2","beef, Gouda Cheese, Special Sauce , Lettuce, tomato",144.00));
        foodList.add(new Food_Domain("Pepperoni pizza","pop_1","slices pepperoni, mozzerella cheese, fresh orange, ground black pepper, pizza sauce",132.00));
        foodList.add(new Food_Domain("Cheese Burger","pop_2","beef, Gouda Cheese, Special Sauce , Lettuce, tomato",144.00));
        foodList.add(new Food_Domain("Pepperoni pizza","pop_1","slices pepperoni, mozzerella cheese, fresh orange, ground black pepper, pizza sauce",132.00));

        adapter2= new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}