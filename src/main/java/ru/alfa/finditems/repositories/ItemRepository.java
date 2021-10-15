package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alfa.finditems.models.Box;
import ru.alfa.finditems.models.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item, Integer> {

    @Query(value = "WITH RECURSIVE bi AS   " +
            "(SELECT id FROM box b WHERE b.id = :contained_in   " +
            "UNION ALL   " +
            "SELECT bo.id FROM box bo INNER JOIN bi   " +
            "ON bi.id = bo.contained_in)  " +
            "SELECT i " +
            "FROM item i INNER JOIN bi  " +
            "ON i.contained_in = bi.id  " +
            "WHERE i.color = :color", nativeQuery = true)
    List<Item> findRecursivelyByBox_IdAndColor
            (@Param("contained_in") Integer containedIn,
             @Param("color") String color);
}
