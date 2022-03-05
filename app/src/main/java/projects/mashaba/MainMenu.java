package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainMenu extends AppCompatActivity {
    ListView displayCustomer;
    ArrayAdapter customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu); // if a button shuts down the app then change the layout-setContent

        displayCustomer= findViewById(R.id.lstDisplayCustomer);
        Button btnRemoveCustomer = findViewById(R.id.btnRemoveCustomer);
        DBHelper2 db = new DBHelper2(MainMenu.this);

        showCustomers(db);

        btnRemoveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


         }
        });
        Button btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCustomerIntent = new Intent(MainMenu.this, Add_Customer_Details.class);
                startActivity(addCustomerIntent);
            }
        });
    }
    public void showCustomers(DBHelper2 db){
        customerArrayAdapter = new ArrayAdapter<>(MainMenu.this, android.R.layout.simple_list_item_1, db.getCustomerData());
        displayCustomer.setAdapter(customerArrayAdapter);
    }


}