package com.projekthibernate.POJO;

import javax.persistence.*;

@Entity
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID_Biletu;
    @ManyToOne
    @JoinColumn(name = "ID")
    Repertuar info;
    int Rzad;
    int Miejsce;

    public Bilet() {
    }

    public Bilet(int ID_Biletu, Repertuar info, int rzad, int miejsce) {
        this.ID_Biletu = ID_Biletu;
        this.info = info;
        Rzad = rzad;
        Miejsce = miejsce;
    }

    public int getID_Biletu() {
        return ID_Biletu;
    }

    public void setID_Biletu(int ID_Biletu) {
        this.ID_Biletu = ID_Biletu;
    }

    public Repertuar getInfo() {
        return info;
    }

    public void setInfo(Repertuar info) {
        this.info = info;
    }

    public int getRzad() {
        return Rzad;
    }

    public void setRzad(int rzad) {
        Rzad = rzad;
    }

    public int getMiejsce() {
        return Miejsce;
    }

    public void setMiejsce(int miejsce) {
        Miejsce = miejsce;
    }
}
