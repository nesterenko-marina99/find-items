package ru.alfa.finditems.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity(name = "Box")
@Table(name = "box")
public class Box {

    @Id
    @Column(name = "id")
    @Getter private Integer id;

    @Column(name = "contained_in")
    private Integer containedIn;

    public Box(Integer id) {
        this.id = id;
    }

}
