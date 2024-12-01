package br.edu.ufam.icomp.plaintext;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import br.edu.ufam.icomp.plaintext.dao.PasswordDAO;
import br.edu.ufam.icomp.plaintext.model.Password;

public class EditActivity extends AppCompatActivity {
    private PasswordDAO passwordDAO;
    private int passwordId;
    private EditText editName, editLogin, editPassword, editNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        editName = findViewById(R.id.editName);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        editNotes = findViewById(R.id.editNotes);

        Intent intent = getIntent();
        passwordId = intent.getIntExtra("passwordId", -1);

        if (passwordId != -1) {
            Password password = passwordDAO.get(passwordId);
            editName.setText(password.getName());
            editLogin.setText(password.getLogin());
            editPassword.setText(password.getPassword());
            editNotes.setText(password.getNotes());
        }

        MaterialButton saveButton = findViewById(R.id.savePasswordButton);
        saveButton.setEnabled(false);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = editName.getText().toString().trim();
                String login = editLogin.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                saveButton.setEnabled(!name.isEmpty() && !login.isEmpty() && !password.isEmpty());
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
        Password password = new Password(passwordId, editName.getText().toString(),
                editLogin.getText().toString(), editPassword.getText().toString(),
                editNotes.getText().toString());

        boolean result;
        if (passwordId == -1) result = passwordDAO.add(password);
        else                  result = passwordDAO.update(password);

        if (result) finish();
    }
}