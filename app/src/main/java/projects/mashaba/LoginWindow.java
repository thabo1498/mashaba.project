package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginWindow extends AppCompatActivity {
    EditText username, password;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnRegisterNow;
        Button btnLogin;
        {
            btnLogin = findViewById(R.id.btnLogin);
            btnRegisterNow = findViewById(R.id.btnRegisterNow);
            username = findViewById(R.id.edtUsername);
            password = findViewById(R.id.edtRegPassword);
            DB = new DBHelper(this);
        }

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginWindow.this, activity_Register.class);
                startActivity(intentRegister);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginWindow.this, "one or more of the fields is empty!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass) //checkuserpass==true
                    {
                        Toast.makeText(LoginWindow.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intentLogin = new Intent(LoginWindow.this, MainMenu.class);
                        startActivity(intentLogin);
                    }else{
                        Toast.makeText(LoginWindow.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

}