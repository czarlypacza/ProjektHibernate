package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.Film;
import com.projekthibernate.POJO.Repertuar;

public class RepertuarDao {

    public void addGodzina(Repertuar rep){
        DBAcess.getEntityManager().getTransaction().begin();
        DBAcess.getEntityManager().persist(rep);
        DBAcess.getEntityManager().getTransaction().commit();

    }


}
