package br.edu.ufam.icomp.plaintext.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ufam.icomp.plaintext.Database;
import br.edu.ufam.icomp.plaintext.model.Password;

public class PasswordDAO {

    private Context context;
    private static ArrayList<Password> passwordsList = new ArrayList<>();

    static private SQLiteDatabase database;

    public PasswordDAO(Context context) {
        this.context = context;
        if (database == null) {
            try {
                database = (new Database(context)).getWritableDatabase();
            } catch (SQLException e) {
                Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("PasswordDAO", e.getMessage());
            }
        }
    }

    public ArrayList<Password> getList() {
        ArrayList<Password> result = new ArrayList<Password>();
        String sql = "SELECT * FROM passwords ORDER BY name";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String login = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            result.add(new Password(id, name, login, password, notes));
        }

        return result;
    }


    public boolean add(Password password) {
        String sql = "INSERT INTO passwords VALUES (NULL, "
                + "'" + password.getName() + "', "
                + "'" + password.getLogin() + "', "
                + "'" + password.getPassword() + "', "
                + "'" + password.getNotes() + "')";
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Senha salva!", Toast.LENGTH_SHORT).show();
            // Pega o id da senha recém inserida
            Cursor cursor = database.rawQuery("SELECT last_insert_rowid()", null);
            cursor.moveToFirst();
            password.setId(cursor.getInt(0));

            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public boolean update(Password password) {
        String sql = "UPDATE passwords SET "
                + "name='" + password.getName() + "', "
                + "login='" + password.getLogin() + "', "
                + "password='" + password.getPassword() + "', "
                + "notes='" + password.getNotes() + "' "
                + "WHERE id=" + password.getId();
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public Password get(int id) {
        String sql = "SELECT * FROM passwords WHERE id=" + id;
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String login = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            return new Password(id, name, login, password, notes);
        }

        return null;
    }

}

