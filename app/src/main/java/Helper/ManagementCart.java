package Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodsforyou.interFace.ChangeNumberItemListener;

import java.util.ArrayList;

import Domain.Food_Domain;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void  insertFood(Food_Domain item){
        ArrayList<Food_Domain> listFood = getListCart();
                boolean existAlready = false;
                int n = 0;
        for (int i = 0;i< listFood.size() ; i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }

        }

        if (existAlready){
            listFood.get(n).setNumberInCard(item.getNumberInCard());
        }else {
            listFood.add(item);
        }

        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();

    }
    public ArrayList<Food_Domain> getListCart(){

        return tinyDB.getListObject("CartList");
    }
    public void plusNumberFood(ArrayList<Food_Domain>listFood, int position, ChangeNumberItemListener changeNumberItemListener){
        listFood.get(position).setNumberInCard(listFood.get(position).getNumberInCard()+1);
        tinyDB.putListObject("CartList",listFood);
        changeNumberItemListener.changed();
    }
    public void minusNumberFood(ArrayList<Food_Domain> listfood,int position,ChangeNumberItemListener changeNumberItemListener){
        if (listfood.get(position).getNumberInCard()==1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCard(listfood.get(position).getNumberInCard()-1);
        }

        tinyDB.putListObject("CartList",listfood);
        changeNumberItemListener.changed();

    }

    public Double getTotalFee(){
        ArrayList<Food_Domain> listfood = getListCart();
        double fee=0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee()*listfood.get(i).getNumberInCard());

        }
        return fee;
    }
}
