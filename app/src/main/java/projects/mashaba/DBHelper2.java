package projects.mashaba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static projects.mashaba.Add_Customer_Details.currentAmount;
import static projects.mashaba.Add_Customer_Details.depositAmount;
import static projects.mashaba.Add_Customer_Details.depositPercentage;
import static projects.mashaba.Add_Customer_Details.itemPurchased;
import static projects.mashaba.Add_Customer_Details.names;
import static projects.mashaba.Add_Customer_Details.phoneNumber;
import static projects.mashaba.Add_Customer_Details.totalAmountOwed;


public class DBHelper2 extends SQLiteOpenHelper {

    public DBHelper2(Context context) {
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE customers(names TEXT, phoneNumber TEXT PRIMARY KEY, itemPurchased TEXT,  totalAmountOwed DOUBLE, depositPercentage DOUBLE, depositAmount DOUBLE, currentAmount DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS customers");
    }
    public Boolean insertCustomer(Customer customerModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("names", names);
        cv.put("phoneNumber", phoneNumber);
        cv.put("itemPurchased", itemPurchased);
        cv.put("totalAmountOwed", totalAmountOwed);
        cv.put("depositPercentage",depositPercentage);
        cv.put("depositAmount",depositAmount);
        cv.put("currentAmount",currentAmount);
        long result = db.insert("customers", null, cv);
        return result != -1;
    }
    public Boolean checkPhoneNumberName(String names,String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM customers WHERE names  = ? and phoneNumber = ?",new String[]{names,phoneNumber});
        if (cursor.getCount()>0)
            return true;
        else
            cursor.close();
            db.close();
            return false;
    }
    public List<Customer>getCustomerData()
    {
        List<Customer>returnList= new ArrayList<>();

        //get data from the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM customers",null);

        if(cursor.moveToFirst())//loop through the cursor (result set )
        {
            do {
                String names = cursor.getString(0);
                String phoneNumber = cursor.getString(1);
                String itemPurchased = cursor.getString(2);
                Double totalAmountOwed = cursor.getDouble(3);
                Double depositPercentage = cursor.getDouble(4);
                Double depositAmount = cursor.getDouble(5);
                Double currentAmount = cursor.getDouble(6);

                Customer newCustomer= new Customer(names,phoneNumber,itemPurchased,totalAmountOwed,depositPercentage,depositAmount,currentAmount);
                returnList.add(newCustomer);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }


}
