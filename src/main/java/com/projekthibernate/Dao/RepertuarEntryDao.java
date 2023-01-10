package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.Sala;

import javax.persistence.Query;
import java.time.LocalTime;
import java.util.List;

public class RepertuarEntryDao {

    public List<?> selectRepertuarinfo(String dzien){
        Query query= DBAcess.getEntityManager().createQuery("SELECT s.ID_seansu, f.ID_filmu, f.Tytul, f.Czas_trwania, f.Ocena FROM Seans s join Film f ON f.ID_filmu = s.ID_filmu.ID_filmu WHERE s.data = '"+dzien+"'");
        return query.getResultList();
    }

    public List<LocalTime> selectGodziny(int ID_seansu){
        Query query = DBAcess.getEntityManager().createQuery("SELECT r.godzina FROM Repertuar r WHERE r.ID_seansu.ID_seansu = '"+ID_seansu+"'");
        return query.getResultList();
    }

    public int getIDsali(int seans,LocalTime godz){
        Query query = DBAcess.getEntityManager().createQuery("SELECT r.ID_sali from Repertuar r where  r.ID_seansu.ID_seansu = ?1 AND r.godzina= ?2");
        query.setParameter(1,seans);
        query.setParameter(2,godz);
        Sala result = (Sala) query.getSingleResult();
        return result.getID_sali();
    }/*Potrzebne do funkcjonalnosci przechodzenia do sali*/


}
