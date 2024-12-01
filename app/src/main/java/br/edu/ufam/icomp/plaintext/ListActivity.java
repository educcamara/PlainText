package br.edu.ufam.icomp.plaintext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PasswordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.itemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PasswordsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String login = getIntent().getStringExtra("login");

        Toast.makeText(this, "Oi, " + login + "!", Toast.LENGTH_SHORT).show();
    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }
}

class PasswordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView login, name;
    public int id;

    public PasswordsViewHolder(ConstraintLayout v, Context context) {
        super(v);
        this.context = context;
        name = v.findViewById(R.id.itemName);
        login = v.findViewById(R.id.itemLogin);
        v.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("passwordId", id);
        context.startActivity(intent);
    }
}