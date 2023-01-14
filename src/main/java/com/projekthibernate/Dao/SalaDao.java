package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.Sala;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class SalaDao {

    public List<Sala> getSale(){
        Query query = DBAcess.getEntityManager().createQuery("from Sala ");
        return query.getResultList();
    }



    public Sala getSala(int id){
        TypedQuery query = DBAcess.getEntityManager().createQuery("from Sala s where s.ID_sali = ?1",Sala.class);
        query.setParameter(1,id);
        return (Sala) query.getSingleResult();
    }
}
