package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.Film;

import javax.persistence.Query;
import java.util.List;

public class FilmDao {

    public List<Film> getFilmy(){
        Query query = DBAcess.getEntityManager().createQuery("from Film ");
        return query.getResultList();
    }

    public static Film getFilm(int ID){
        Query query = DBAcess.getEntityManager().createQuery("from Film where ID_filmu = ?1");
        query.setParameter(1,ID);
        return (Film) query.getSingleResult();
    }

    public void dodajFilm(String tytul, String rezyser, int czas, int ocena){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().persist(new Film(czas,ocena,tytul,rezyser));
        DBAcess.getEntityManager().getTransaction().commit();
    }

    public void usunFilm(Film film){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().remove(film);
        DBAcess.getEntityManager().getTransaction().commit();
    }

    public void updateFilm(){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().flush();
        DBAcess.getEntityManager().getTransaction().commit();
    }
}
