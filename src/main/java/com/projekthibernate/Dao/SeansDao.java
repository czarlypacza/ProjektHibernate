package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.Film;
import com.projekthibernate.POJO.Seans;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class SeansDao {


    public List<?> getSeansEntry(String data){
        Query query = DBAcess.getEntityManager().createQuery("SELECT s.ID_seansu, s.ID_filmu.ID_filmu ,f.Tytul from Seans s inner join Film f ON s.ID_filmu.ID_filmu = f.ID_filmu where s.data='"+data+"'");
        return query.getResultList();
    }

    public Seans getSeans(int ID){
        Query query =DBAcess.getEntityManager().createQuery("from Seans where ID_seansu= ?1");
        query.setParameter(1,ID);
        return (Seans)query.getSingleResult();
    }


    public void dodajSeans(Film ID_filmu,LocalDate data){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().persist(new Seans(ID_filmu,data));
        DBAcess.getEntityManager().getTransaction().commit();
    }


    public void usunSeans(Seans seans){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().remove(seans);
        DBAcess.getEntityManager().getTransaction().commit();
    }

    public void updateSeans(){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().flush();
        DBAcess.getEntityManager().getTransaction().commit();
    }



}
