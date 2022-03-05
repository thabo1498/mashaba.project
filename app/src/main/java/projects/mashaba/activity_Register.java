package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_Register extends AppCompatActivity {
    //declare variables
    EditText username, password, confirmPassword;
    Button register;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    //initialising the variables
        username = (EditText) findViewById(R.id.edtRegUsername);
        password = (EditText) findViewById(R.id.edtRegPassword);
        confirmPassword = (EditText) findViewById(R.id.edtConfirmPass);
        register = (Button) findViewById(R.id.btnRegister);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String re_Enter= confirmPassword.getText().toString();

                if (user.equals("")|| pass.equals("")||re_Enter.equals(""))
                    Toast.makeText(activity_Register.this,"one or more of the fields is empty!", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(re_Enter)){
                        boolean checkuser = DB.checkusername(user);
                        if (!checkuser){
                            Boolean insert =DB.insertData(user,pass);
                            if (insert){
                                Toast.makeText(activity_Register.this, "You have successfully registered!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity_Register.this,MainMenu.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(activity_Register.this,"Registration failed!",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(activity_Register.this, "User already exist! please Login.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(activity_Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}