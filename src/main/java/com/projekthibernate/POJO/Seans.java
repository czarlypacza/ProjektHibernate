package com.projekthibernate.POJO;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table
public class Seans {
    @Id
    @Column(name = "ID_seansu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID_seansu;

    @ManyToOne(targetEntity = Film.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_filmu")
    Film ID_filmu;

    LocalDate data;

    public Seans(int ID_seansu, Film ID_filmu, LocalDate data) {
        this.ID_seansu = ID_seansu;
        this.ID_filmu = ID_filmu;
        this.data = data;
    }

    public Seans(Film ID_filmu, LocalDate data) {
        this.ID_filmu = ID_filmu;
        this.data = data;
    }

    public Seans() {
    }

    public int getID_seansu() {
        return ID_seansu;
    }

    public void setID_seansu(int ID_seansu) {
        this.ID_seansu = ID_seansu;
    }

    public Film getID_filmu() {
        return ID_filmu;
    }

    public void setID_filmu(Film ID_filmu) {
        this.ID_filmu = ID_filmu;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
