package com.projekthibernate.POJO;

import java.time.LocalTime;
import java.util.ArrayList;

public class RepertuarEntry {

    int ID_Seansu,ID_Filmu;
    String Tytul;
    int Czas_trwania,Ocena;
    ArrayList<LocalTime>godziny;

    public RepertuarEntry(int ID_seansu,int ID_filmu,String tytul, int czas_trwania, int ocena, ArrayList<LocalTime> godziny) {
        ID_Seansu= ID_seansu;
        ID_Filmu=ID_filmu;
        Tytul = tytul;
        Czas_trwania = czas_trwania;
        Ocena = ocena;
        this.godziny = godziny;
    }

    public int getID_Filmu() {
        return ID_Filmu;
    }

    public void setID_Filmu(int ID_Filmu) {
        this.ID_Filmu = ID_Filmu;
    }

    public void addgodzina(LocalTime godz){
        godziny.add(godz);
    }
    public void godzinyclear(){
        godziny.clear();
    }

    public int getID_Seansu() {
        return ID_Seansu;
    }

    public void setID_Seansu(int ID_seansu) {
        this.ID_Seansu = ID_seansu;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    public int getCzas_trwania() {
        return Czas_trwania;
    }

    public void setCzas_trwania(int czas_trwania) {
        Czas_trwania = czas_trwania;
    }

    public int getOcena() {
        return Ocena;
    }

    public void setOcena(int ocena) {
        Ocena = ocena;
    }

    public ArrayList<LocalTime> getGodziny() {
        return godziny;
    }

    public void setGodziny(ArrayList<LocalTime> godziny) {
        this.godziny = godziny;
    }
}
