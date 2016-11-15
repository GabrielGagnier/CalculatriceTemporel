package com.gabriel.gagnier.calculatricetemporel;

/**
 * Created by thibault on 14/11/2016.
 */

public class EventEntity {

    private int id;
    private String libelle;
    private String date;
    private String commentaire;
    private int notification;


    public EventEntity()
    {

    }

    public EventEntity(int id, String libelle, String date, String commentaire, int notification) {
        this.id = id;
        this.libelle = libelle;
        this.date = date;
        this.commentaire = commentaire;
        this.notification = notification;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }
}
