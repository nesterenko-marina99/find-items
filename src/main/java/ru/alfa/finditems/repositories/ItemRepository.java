package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alfa.finditems.models.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository <Item, Integer> {
    List<Item> findByContainedInAndColor (Integer containedIn, String color);
}
