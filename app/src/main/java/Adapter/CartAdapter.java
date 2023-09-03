package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodsforyou.R;
import com.example.foodsforyou.interFace.ChangeNumberItemListener;

import java.util.ArrayList;

import Domain.Food_Domain;
import Helper.ManagementCart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Food_Domain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartAdapter(ArrayList<Food_Domain> foodDomains, Context context , ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholer_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter. ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEchItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEchItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCard() * foodDomains.get(position).getFee()) * 100) / 100));
        holder.nun.setText(String.valueOf(foodDomains.get(position).getNumberInCard()));

        int drawableReourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic()
                ,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title , feeEchItem;
        ImageView pic , plusItem, minusItem;
        TextView totalEchItem , nun;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTax);
            pic = itemView.findViewById(R.id.picCart);
            feeEchItem = itemView.findViewById(R.id.feeEchItem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartbtn);
            totalEchItem = itemView.findViewById(R.id.totalEchItem);
            nun = itemView.findViewById(R.id.numberBcrItem);

        }
    }
}
