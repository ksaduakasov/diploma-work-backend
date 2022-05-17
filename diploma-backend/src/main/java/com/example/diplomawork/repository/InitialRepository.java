package com.example.diplomawork.repository;

import com.example.diplomawork.model.Initial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialRepository extends JpaRepository<Initial, Long> {
    Initial findByInitial(String initial);
}
