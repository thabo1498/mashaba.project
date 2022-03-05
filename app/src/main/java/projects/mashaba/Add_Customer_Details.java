package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Customer_Details extends AppCompatActivity {
    //declare variables
     EditText nameSurname,phoneNo, items, initialAmount,percentage;
     TextView currentAmountOwed, depositedAmount;
     Button saveCustomer, showAmounts;
     ListView displayCustomer;
     DBHelper2 db;

     //global variables
    public static Double currentAmount=0.0;
    public static Double depositAmount=0.0;
    public static Double depositPercentage=0.0;
    public static Double totalAmountOwed =0.0;
    public static String names;
    public static String phoneNumber;
    public static String itemPurchased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer_details);

      //initialize variables
        {
            nameSurname = findViewById(R.id.edtNames);
            phoneNo = findViewById(R.id.edtNumber);
            items = findViewById(R.id.edtItemDescription);
            initialAmount = findViewById(R.id.edtInitialAmountOwed);
            percentage = findViewById(R.id.edtPercentage);
            saveCustomer = findViewById(R.id.btnSaveCustomer);
            currentAmountOwed = findViewById(R.id.txvDisplayCurrentOwed);
            depositedAmount = findViewById(R.id.txvDisplayDiposit);
            showAmounts = findViewById(R.id.btnAmounts);
            db = new DBHelper2(this);
        }

        showAmounts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {   try {
                names = nameSurname.getText().toString();
                phoneNumber = phoneNo.getText().toString();
                itemPurchased = items.getText().toString();
                totalAmountOwed = Double.parseDouble(initialAmount.getText().toString());
                depositPercentage = Double.parseDouble(percentage.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(Add_Customer_Details.this, "One or more empty field(S)", Toast.LENGTH_SHORT).show();;
            }

                //
                if(depositPercentage>100)
                {
                    Toast.makeText(Add_Customer_Details.this, "percentage must be between 0 and 100", Toast.LENGTH_SHORT).show();
                }
                else {
                    depositAmount = totalAmountOwed * (depositPercentage / 100.0);
                    currentAmount = totalAmountOwed - depositAmount;

                    currentAmountOwed.setText(currentAmount.toString());
                    depositedAmount.setText(depositAmount.toString());
                }
            }
        });

        saveCustomer.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v) {

              //the error seems to be here! (for future reference- i used the wrong button so the button had two onclicklisteners)
              Customer customerModel;
              try {
                  if (db.checkPhoneNumberName(names, phoneNumber) == false) {
                      customerModel = new Customer(names, phoneNumber, itemPurchased, totalAmountOwed, depositPercentage, depositAmount, currentAmount);
                      DBHelper2 db = new DBHelper2(Add_Customer_Details.this);
                      Boolean insert = db.insertCustomer(customerModel);
                      clearControls();

                      if (insert == true) {
                          Toast.makeText(Add_Customer_Details.this, "Customer was successfully added", Toast.LENGTH_SHORT).show();
                      } else if (db.checkPhoneNumberName(names, phoneNumber) == true) {
                          Toast.makeText(Add_Customer_Details.this, "Customer already exist", Toast.LENGTH_SHORT).show();
                          clearControls();
                      }
                  }

                  } catch(Exception e){
                      Toast.makeText(Add_Customer_Details.this, "All fields must be provided with input, also names must be 3 or more characters long", Toast.LENGTH_LONG).show();
                  }

          }
        });

    }

    public void clearControls() //clear controls method
    {
        nameSurname.getText().clear();
        phoneNo.getText().clear();
        items.getText().clear();
        initialAmount.getText().clear();
        percentage.getText().clear();
        depositedAmount.setText("");
        currentAmountOwed.setText("");
    }
}