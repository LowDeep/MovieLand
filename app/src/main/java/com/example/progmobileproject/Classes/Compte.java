package com.example.progmobileproject.Classes;

public class Compte {

    private String name;
    private String mail;
    private String numero;
    private String password;

    public Compte(String n, String m, String num, String pas){
        this.name=n;
        this.mail=m;
        this.numero=num;
        this.password=pas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
