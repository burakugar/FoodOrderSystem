package util;

import java.util.UUID;

public class User {
    private String mail;
    private String password;
    private String ID;

    public User() {
        this.mail = "";
        this.password = "";
        this.ID = "";

    }

    public User(String Mail, String Password) {
        mail = Mail;
        password = Password;
        ID = UUID.randomUUID().toString();
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getID() {
        return ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object obj) {
        User data = (User) obj;
        return (data.mail.equals(this.mail));
    }
}
