package models;


import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Option  {

    private int id;
    public String name;    //======================
    private Field field;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
