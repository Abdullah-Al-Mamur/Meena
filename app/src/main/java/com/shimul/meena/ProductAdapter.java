package com.shimul.meena;

import android.graphics.Paint;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<Product> productList = new ArrayList<>();
    int counter = 0;
    int cartCounter = 0;
    public HashMap<Integer, Integer> counterHash = new HashMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText, classText, priceText, bagText, withoutDiscountPriceText, productCountText;
        public ImageView productImage,deleteProImg, addItemImg, removeItemImg;


        public MyViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.nameTV);
            classText = view.findViewById(R.id.classTv);
            priceText = view.findViewById(R.id.priceTV);
            bagText = view.findViewById(R.id.AddTobagTV);
            withoutDiscountPriceText = view.findViewById(R.id.WithoutDiscountpriceTV);
            productImage = view.findViewById(R.id.imageView);
            deleteProImg = view.findViewById(R.id.deleteItemIMG);
            productCountText = view.findViewById(R.id.productCountTV);
            addItemImg = view.findViewById(R.id.addProImg);
            removeItemImg = view.findViewById(R.id.removeItemIMG);



        }
    }


    public ProductAdapter(List<Product> productList) {

        this.productList = productList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_itemview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Product product = productList.get(position);
        Picasso.get().load("http://www.meenaclick.com/back_end/assets/product_images/"+product.getProduct_image()).into(holder.productImage);
        holder.nameText.setText(product.getProduct_name().substring(0,product.getProduct_name().length() -3)+"...");
        holder.classText.setText("["+product.getWeight()+" "+product.getWeight_class()+"]");
        holder.priceText.setText("$"+String.valueOf(product.getPrice()));
        holder.withoutDiscountPriceText.setText("$512");
        holder.withoutDiscountPriceText.setPaintFlags(holder.withoutDiscountPriceText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.addItemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!counterHash.containsKey(position)){
                    counter = 0;
                    counterHash.put(position, counter);
                }
                counter++;
                if(counter == 1){
                    holder.removeItemImg.setVisibility(View.GONE);
                    holder.deleteProImg.setVisibility(View.VISIBLE);
                    holder.bagText.setVisibility(View.GONE);
                }else if(counter > 1){
                    holder.removeItemImg.setVisibility(View.VISIBLE);
                    holder.deleteProImg.setVisibility(View.GONE);
                    holder.bagText.setVisibility(View.GONE);

                }
                counterHash.put(position, counter);
                holder.productCountText.setText(String.valueOf(counterHash.get(position)));
            }
        });

        holder.deleteProImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counterHash.get(position);
                counter = 0;
                counterHash.replace(position, counter);
                holder.productCountText.setText(String.valueOf(counterHash.get(position)));
                holder.removeItemImg.setVisibility(View.GONE);
                holder.deleteProImg.setVisibility(View.GONE);
                holder.bagText.setVisibility(View.VISIBLE);


            }
        });

        holder.removeItemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counterHash.get(position);
                counter--;
                counterHash.replace(position, counter);
                holder.productCountText.setText(String.valueOf(counterHash.get(position)));
                if(counterHash.get(position) == 1){
                    holder.removeItemImg.setVisibility(View.GONE);
                    holder.deleteProImg.setVisibility(View.VISIBLE);
                    holder.bagText.setVisibility(View.GONE);
                }





            }
        });







}



    @Override
    public int getItemCount() {
        return productList.size();
    }





}