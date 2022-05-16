package com.example.diplomawork.repository;

import com.example.diplomawork.model.DefenceCommission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DefenceCommissionRepository extends JpaRepository<DefenceCommission, Long> {
    List<DefenceCommission> findDefenceCommissionsByCommissionId(Long commissionId);
}
