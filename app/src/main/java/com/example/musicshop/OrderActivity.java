package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses = {"t17fenics@gmail.com"};
    String subject = "Order from Android Shop";
    String emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent recivedOrderIntent = getIntent();
        String userName = recivedOrderIntent.getStringExtra("userName");
        String goodsName = recivedOrderIntent.getStringExtra("goodsName");
        int quantity = recivedOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = recivedOrderIntent.getDoubleExtra("orderPrice", 0);
        double price = recivedOrderIntent.getDoubleExtra("price", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        emailText = "Customer name: " + userName + "\n"
                + "Goods Name: " +goodsName + "\n"
                + "Quantity: " + quantity + "\n"
                + "Price: " + price + "\n"
                + "Order Price: " + orderPrice;
        orderTextView.setText(emailText);
        setTitle("Your Order");
    }

    public void SubmitOrder(View view) {
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_STREAM, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
