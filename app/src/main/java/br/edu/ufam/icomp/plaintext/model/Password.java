package br.edu.ufam.icomp.plaintext.model;

public class Password {
   private int id;
   private String name;
   private String login;
   private String password;
   private String notes;

   public Password(int id, String name, String login, String password, String notes) {
       this.id = id;
       this.name = name;
       this.login = login;
       this.password = password;
       this.notes = notes;
   }

   //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    //endregion
}
