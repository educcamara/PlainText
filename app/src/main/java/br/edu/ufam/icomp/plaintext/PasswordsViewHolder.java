package br.edu.ufam.icomp.plaintext;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

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
       Toast.makeText(context, "Olá " + this.login.getText().toString(), Toast.LENGTH_LONG)
            .show();
   }
}
