package models;


import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import play.db.jpa.JPA;

@Entity
public class Field {

    public int id;
    public String label;
    public FieldType fieldType;


    public boolean required = false;
    public boolean active = false;
    public List<Option> options;
    public List<Value> values;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type_field")
    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Column(name = "required")
    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//, mappedBy="field")
    @JoinColumn(name="field_id")
    public List<Option> getOptions() {
        return options;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="field_id")
    public List<Value> getValues() {
        return values;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public String optionsValue(){
        String val = "";
        if(options != null) {
            for (Option opt : options) {
                val = val + opt.getName() + System.getProperty("line.separator");
            }
        }
        return val;
    }

}
