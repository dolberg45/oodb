package model.classes;

import annotations.Column;
import annotations.Entity;
import model.IBaseEntity;

@Entity
public class Dealer implements IBaseEntity {

    @Column
    private Long id;

    @Column
    private String name;

    public Dealer() {}

    public Dealer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getDescription() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
