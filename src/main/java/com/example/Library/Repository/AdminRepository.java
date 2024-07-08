package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library.Entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    
}
