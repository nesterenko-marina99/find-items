package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alfa.finditems.models.Box;
import ru.alfa.finditems.models.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.color = ?1 AND i.box.id = ?2")
    List<Item> findByColorAndBox_Id (String color, Integer containedIn);


}
