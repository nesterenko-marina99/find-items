package ru.alfa.finditems.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Box")
@Table(name = "box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "contained_in")
    private Integer containedIn;

}
