package com.example.diplomawork.repository;

import com.example.diplomawork.model.DefenceCommission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefenceCommissionRepository extends JpaRepository<DefenceCommission, Long> {
    List<DefenceCommission> findDefenceCommissionsByCommissionId(Long commissionId);
}
