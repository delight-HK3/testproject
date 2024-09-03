package com.toypro.test.toypro.repository.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardSaveEntity;

@Repository
public interface DashboardSaveRepository extends JpaRepository<DashboardSaveEntity, Integer>{

    
} 