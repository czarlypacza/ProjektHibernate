package com.projekthibernate.Dao;

import com.projekthibernate.DBAcess;
import com.projekthibernate.POJO.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {

    public List<User> getUsers(){
        Query query = DBAcess.getEntityManager().createQuery("from User");
        return query.getResultList();
    }


}
