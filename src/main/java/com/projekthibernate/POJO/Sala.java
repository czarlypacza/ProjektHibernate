package com.projekthibernate.POJO;

import javax.persistence.*;
import javax.persistence.Id;
@Entity
public class Sala {
    @Id
    @Column(name = "ID_sali")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ID_sali;
    Integer rzedy;
    Integer miejsca;

    public Sala(Integer ID_sali, Integer rzedy, Integer miejsca) {
        this.ID_sali = ID_sali;
        this.rzedy = rzedy;
        this.miejsca = miejsca;
    }

    public Sala(Integer rzedy, Integer miejsca) {
        this.rzedy = rzedy;
        this.miejsca = miejsca;
    }

    public Sala() {
    }

    public Integer getID_sali() {
        return ID_sali;
    }

    public void setID_sali(Integer ID_sali) {
        this.ID_sali = ID_sali;
    }

    public Integer getRzedy() {
        return rzedy;
    }

    public void setRzedy(Integer rzedy) {
        this.rzedy = rzedy;
    }

    public Integer getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(Integer miejsca) {
        this.miejsca = miejsca;
    }
}
