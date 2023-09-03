package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsforyou.R;
import com.example.foodsforyou.interFace.ChangeNumberItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Adapter.CartAdapter;
import Helper.ManagementCart;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTax , deliveryTxt,taxTxt,totalTxt, emptyTax  , checkOut;
    private long tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();

    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        TextView checkOut = findViewById(R.id.chec);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartListActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity2.class));

            }
        });
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTax = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        taxTxt = findViewById(R.id.TaxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTax = findViewById(R.id.emptyTax);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.recyclerviewCart);

    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter =new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();

            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTax.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTax.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax = 0.01;
        double delivery  =10;


        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100)/100;
        double total = Math.round((managementCart.getTotalFee()+tax +delivery) * 100)/100;
        double itemTotal  =Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTax.setText("EGP "+itemTotal);

        taxTxt.setText("EGP "+tax);
        deliveryTxt.setText("EGP "+delivery);
        totalTxt.setText("EGP "+total);
    }
}