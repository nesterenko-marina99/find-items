package ru.alfa.finditems.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Item")
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "contained_in")
    private Integer containedIn;

    @Column(name = "color",
    columnDefinition = "TEXT")
    private String color;
}
