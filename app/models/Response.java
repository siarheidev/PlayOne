package models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Response {

    int id;
    List<Value> values;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="response_id")
    public List<Value> getValues() {
        return values;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
