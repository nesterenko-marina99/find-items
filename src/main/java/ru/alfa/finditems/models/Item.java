package ru.alfa.finditems.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity(name = "Item")
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @Getter private Integer id;

    @ManyToOne(targetEntity = Box.class)
    @JoinColumn(name = "contained_in")
    @Getter private Box box;

    @Column(name = "color",
    columnDefinition = "TEXT")
    @Getter private String color;

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, String color) {
        this.id = id;
        this.color = color;
    }

    public Item(Integer id, Box box) {
        this.id = id;
        this.box = box;
    }
}
