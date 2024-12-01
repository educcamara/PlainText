package br.edu.ufam.icomp.plaintext;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editName = findViewById(R.id.addName);
        EditText editLogin = findViewById(R.id.addLogin);
        EditText editPassword = findViewById(R.id.addPassword);
        MaterialButton saveButton = findViewById(R.id.savePasswordButton);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = editName.getText().toString().trim();
                String login = editLogin.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (name.isEmpty() || login.isEmpty() || password.isEmpty()) {
                    saveButton.setEnabled(false);
                    return;
                }

                saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                // Do nothing
                String name = editName.getText().toString();
                String trimmedName = name.trim();
                String login = editLogin.getText().toString();
                String trimmedLogin = login.trim();

                if (!name.equals(trimmedName)) {
                    editName.setText(trimmedName);
                }
                if (!login.equals(trimmedLogin)) {
                    editLogin.setText(trimmedLogin);
                }
            }
        };
    }

    public void onSaveClicked(View view) {

    }
}