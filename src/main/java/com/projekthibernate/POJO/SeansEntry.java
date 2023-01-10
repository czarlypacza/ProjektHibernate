package com.projekthibernate.POJO;

import java.time.LocalDate;

public class SeansEntry {

    int ID_seansu,ID_filmu;
    String Tytul;

    public SeansEntry() {
    }

    public SeansEntry(int ID_seansu, int ID_filmu, String tytul) {
        this.ID_seansu = ID_seansu;
        this.ID_filmu = ID_filmu;
        Tytul = tytul;
    }

    public int getID_seansu() {
        return ID_seansu;
    }

    public void setID_seansu(int ID_seansu) {
        this.ID_seansu = ID_seansu;
    }

    public int getID_filmu() {
        return ID_filmu;
    }

    public void setID_filmu(int ID_filmu) {
        this.ID_filmu = ID_filmu;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }
}
