package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginWindow extends AppCompatActivity {
    private Button btnRegisterNow;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Code for opening register window
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterNow = findViewById(R.id.btnRegisterNow);

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginWindow.this, activity_Register.class);
                startActivity(intentRegister);
            }
        });
        //code to open the MainMenu Window

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(LoginWindow.this, MainMenu.class);
                startActivity(intentLogin);
            }
        });

    }

}