package br.edu.ufam.icomp.plaintext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i("MainActivity", "main view created");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void signInButtonClicked(View view) {
        EditText loginTextView = findViewById(R.id.loginTextView);
        String login = loginTextView.getText().toString();
        String msg = "Olá " + login + "!!";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, ListActivity.class);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//
//        }
    }
}