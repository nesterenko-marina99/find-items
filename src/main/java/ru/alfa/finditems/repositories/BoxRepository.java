package ru.alfa.finditems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alfa.finditems.models.Box;

public interface BoxRepository extends JpaRepository <Box, Integer> {
}
