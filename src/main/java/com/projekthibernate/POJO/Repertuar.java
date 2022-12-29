package com.projekthibernate.POJO;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "Repertuar")
//@IdClass(Repertuar.RepertuarPK.class)
public class Repertuar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ID;

    //@Id
    @ManyToOne(targetEntity = Seans.class)
    @JoinColumn(name = "ID_seansu")
    Integer ID_seansu;
    //@Id
    @ManyToOne(targetEntity = Sala.class)
    @JoinColumn(name = "ID_sali")
    Integer ID_sali;
    LocalTime godzina;

    public Repertuar(int ID_seansu, int ID_sali, LocalTime godzina) {
        this.ID_seansu = ID_seansu;
        this.ID_sali = ID_sali;
        this.godzina = godzina;
    }

    public Repertuar() {
    }

    public int getID_seansu() {
        return ID_seansu;
    }

    public void setID_seansu(int ID_seansu) {
        this.ID_seansu = ID_seansu;
    }

    public int getID_sali() {
        return ID_sali;
    }

    public void setID_sali(int ID_sali) {
        this.ID_sali = ID_sali;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public void setGodzina(LocalTime godzina) {
        this.godzina = godzina;
    }


    static class RepertuarPK implements Serializable {
        protected Integer ID_seansu;
        protected Integer ID_sali;

        public RepertuarPK() {
        }

        public RepertuarPK(Integer ID_seamsu, Integer ID_sali) {
            this.ID_seansu = ID_seamsu;
            this.ID_sali = ID_sali;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RepertuarPK that = (RepertuarPK) o;
            return ID_seansu.equals(that.ID_seansu) && ID_sali.equals(that.ID_sali);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ID_seansu, ID_sali);
        }
    }
}
