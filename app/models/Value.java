package models;

import javax.persistence.*;

@Entity
public class Value {

    int id;
    String value;
    Field field;
    Response response;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Field getField() {
        return field;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Response getResponse() {
        return response;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
