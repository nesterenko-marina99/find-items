package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alfa.finditems.models.Box;

import java.util.List;


@Repository
public interface BoxRepository extends JpaRepository <Box, Integer> {

    @Query(value = "SELECT b FROM Box b WHERE b.containedIn=?1")
    List<Box> findByContainedIn (Integer containedIn);
}
