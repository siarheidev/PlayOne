package DAO;


import models.Response;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import java.util.List;

public class ResponseDAO {

    public static List<Response> getResponseList(){
        Session session = (Session) JPA.em().getDelegate();
        List<Response> fields = session.createCriteria(Response.class).list();
        return fields;
    }

    @Transactional
    public static long countResponse(){
        Session session = (Session) JPA.em().getDelegate();
        Criteria criteria = session.createCriteria(Response.class);

        criteria.setProjection(Projections.rowCount());
        long count = (Long)criteria.uniqueResult();
        System.out.println("COUNT " + count);
        return count;
    }

}
