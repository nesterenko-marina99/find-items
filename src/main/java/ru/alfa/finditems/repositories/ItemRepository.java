package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alfa.finditems.models.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item, Integer> {

    @Query(value = "WITH RECURSIVE bi AS   " +
            "(SELECT id FROM box WHERE box.id = :contained_in   " +
            "UNION ALL   " +
            "SELECT box.id FROM box INNER JOIN bi   " +
            "ON bi.id = box.contained_in)  " +
            "SELECT item.id " +
            "FROM item INNER JOIN bi  " +
            "ON item.contained_in = bi.id  " +
            "WHERE item.color = :color", nativeQuery = true)
    List<Integer> findRecursivelyByBox_IdAndColor
            (@Param("contained_in") Integer containedIn,
             @Param("color") String color);
}
