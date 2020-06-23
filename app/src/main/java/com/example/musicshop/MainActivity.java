package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();
        createMap();




    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Digma");
        spinnerArrayList.add("Samsung");
        spinnerArrayList.add("Xiaomi");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Digma", 500.0);
        goodsMap.put("Samsung", 1500.0);
        goodsMap.put("Xiaomi", 1000.0);
    }
    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decraseQuantity(View view) {
        quantity = quantity - 1;
        if(quantity < 0 ) {
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageVIew = findViewById(R.id.goodsImageView);
        /*if(goodsName.equals("Digma")) {
            goodsImageVIew.setImageResource(R.drawable.digma);
        } else if(goodsName.equals("Samsung")) {
            goodsImageVIew.setImageResource(R.drawable.samsung);
        } else if(goodsName.equals("Xiaomi")) {
            goodsImageVIew.setImageResource(R.drawable.xiaomi);
        }*/
        switch (goodsName) {
            case "Digma":
                goodsImageVIew.setImageResource(R.drawable.digma);
                break;
            case "Samsung":
                goodsImageVIew.setImageResource(R.drawable.samsung);
                break;
            case "Xiaomi":
                goodsImageVIew.setImageResource(R.drawable.xiaomi);
                break;
            default:
                goodsImageVIew.setImageResource(R.drawable.digma);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);
        startActivity(orderIntent);
    }
}
