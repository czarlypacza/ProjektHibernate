package com.projekthibernate.POJO;

import javax.persistence.*;

@Entity
public class Film {
    @Id
    @Column(name = "ID_filmu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID_filmu;
    int Czas_trwania;
    int Ocena;
    String Tytul;
    String rezyser;

    public Film(int ID_filmu, int czas_trwania, int ocena, String tytul, String rezyser) {
        this.ID_filmu = ID_filmu;
        this.Czas_trwania = czas_trwania;
        this.Ocena = ocena;
        this.Tytul = tytul;
        this.rezyser = rezyser;
    }

    public Film(int czas_trwania, int ocena, String tytul, String rezyser) {
        Czas_trwania = czas_trwania;
        Ocena = ocena;
        Tytul = tytul;
        this.rezyser = rezyser;
    }

    public Film() {
    }

    @Override
    public String toString() {
        return "Film{" +
                "ID_filmu=" + ID_filmu +
                ", Czas_trwania=" + Czas_trwania +
                ", Ocena=" + Ocena +
                ", Tytul='" + Tytul + '\'' +
                ", rezyser='" + rezyser + '\'' +
                '}';
    }

    public int getID_filmu() {
        return ID_filmu;
    }

    public void setID_filmu(int ID_filmu) {
        this.ID_filmu = ID_filmu;
    }

    public int getCzas_trwania() {
        return Czas_trwania;
    }

    public void setCzas_trwania(int czas_trwania) {
        this.Czas_trwania = czas_trwania;
    }

    public int getOcena() {
        return Ocena;
    }

    public void setOcena(int ocena) {
        this.Ocena = ocena;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        this.Tytul = tytul;
    }

    public String getRezyser() {
        return rezyser;
    }

    public void setRezyser(String rezyser) {
        this.rezyser = rezyser;
    }
}
