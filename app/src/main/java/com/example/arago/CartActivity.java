package com.example.arago;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.Model.Customer;
import com.example.arago.Model.Order;
import com.example.arago.Model.Request;
import com.example.arago._USER.Adapter.CartAdapter;
import com.example.arago._USER.Database.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        TextView totalBill;
        Button btnplace;

        FirebaseDatabase database;
        DatabaseReference requests;

        List<Order> cart=new ArrayList<>();
        CartAdapter cartAdapter;
        public static Customer currentUSer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //FireBAse
        database= FirebaseDatabase.getInstance();
        requests=database.getReference("Request");

        //Init
        recyclerView=(RecyclerView)findViewById(R.id.listcart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        totalBill=(TextView)findViewById(R.id.total);
        btnplace=(Button)findViewById(R.id.btnPlcaeHolder);
        btnplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAltherDialog();
            }
        });
        loadlistfood();
    }

    private void showAltherDialog() {
        AlertDialog.Builder altherBuilder=new AlertDialog.Builder(CartActivity.this);
        altherBuilder.setTitle("One more step");
        altherBuilder.setMessage("Enter Date you want to check: ");
        final EditText editDate = new EditText(CartActivity.this);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editDate.setLayoutParams(lp);
        altherBuilder.setView(editDate);
        altherBuilder.setIcon(R.drawable.plus);
        altherBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Request request = new Request(
                        currentUSer.getCustomer_phone(),
                        currentUSer.getCustomer_name(),
                        editDate.getText().toString(),cart
                );
                //submit to firebase
                //we will using System.currentMilli to key

                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                //delete
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(CartActivity.this, "Thank you, Order Place", Toast.LENGTH_SHORT).show();
                finish();



            }
        });
        altherBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        altherBuilder.show();
    }

    private void loadlistfood() {
        cart=new Database(this).getCardt();
        cartAdapter=new CartAdapter(cart,this);
        recyclerView.setAdapter(cartAdapter);
    }
}
