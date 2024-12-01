package br.edu.ufam.icomp.plaintext;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLoginLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i("MainActivity", "main view created");

        EditText loginTextView = findViewById(R.id.loginTextView);
        EditText passwordTextView = findViewById(R.id.passwordTextView);
        MaterialButton signInButton = findViewById(R.id.loginButton);

        String login = loginTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();

        signInButton.setEnabled(!login.isEmpty() && !password.isEmpty());

        //TODO: For debug only
        loginTextView.setText("a");
        passwordTextView.setText("1");
        signInButtonClicked(null);


        // Create new text watcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if both fields are filled and if so, enable the button
                String login = loginTextView.getText().toString();
                String password = passwordTextView.getText().toString();

                signInButton.setEnabled(!login.isEmpty() && !password.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Trim the text
                String login = loginTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String trimmedLogin = login.trim();
                String trimmedPassword = password.trim();
                if (!trimmedLogin.isEmpty() && !trimmedPassword.isEmpty()) {
                    signInButton.setEnabled(true);
                    return;
                }

                signInButton.setEnabled(false);
            }
        };

        // Add the text watcher to the login and password fields
        loginTextView.addTextChangedListener(textWatcher);
        passwordTextView.addTextChangedListener(textWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void signInButtonClicked(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefLogin = sharedPreferences.getString("login", "");
        String prefPass  = sharedPreferences.getString("password", "");

        String login = ((EditText) findViewById(R.id.loginTextView)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordTextView)).getText().toString();

        if (!login.equals(prefLogin) || !password.equals(prefPass)) {
            Toast.makeText(this, "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mainMenuAbout) {
            AlertDialog.Builder alert = new AlertDialog.Builder((this));
            alert.setMessage("PlainText gerenciador de senhas v1.0")
                    .setNeutralButton("Ok", null)
                    .show();
            return true;
        } else if (item.getItemId() == R.id.mainMenuSettings) {
            Intent intentConfig = new Intent(this, PreferencesActivity.class);
            startActivity(intentConfig);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}