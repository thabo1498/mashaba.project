package projects.mashaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1100; //first screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //below is the code for Splash or preview screen
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(MainActivity.this, LoginWindow.class);
            startActivity(homeIntent);
            finish();
        },SPLASH_TIME_OUT);






    }


}