package DAO;

import models.Field;
import models.Option;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import java.util.List;

public class FieldDAO {

    public void saveField(Field field) {
        List<Field> fields = getFields();
        boolean flag = true;
        for(int i = 0; i < fields.size(); i++){
            Field existingField = fields.get(i);
            if(existingField.getLabel().equalsIgnoreCase(field.getLabel())){
                Session session = (Session)JPA.em().getDelegate();
                List<Option> options = existingField.getOptions();
                for(Option option: options){
                    session.delete(option);
                }
                existingField.setActive(field.getActive());
                existingField.setRequired(field.getRequired());
                existingField.setFieldType(field.getFieldType());
                existingField.setOptions(field.getOptions());
                session.saveOrUpdate(existingField);
                flag = false;
            }
        }
        if(flag) {
            JPA.em().persist(field);
        }
    }

    public List<Field> getFields(){
        Session s = (Session)JPA.em().getDelegate();
        List<Field> fields = s.createCriteria(Field.class).list();
        return fields;
    }

    public static Field getById (int id){
        Field field = JPA.em().find(Field.class, id);
        System.out.println(field.getLabel());
        return field;
    }

    public static void delById(int id){
        Field field = getById(id);
        JPA.em().remove(field);
    }

}