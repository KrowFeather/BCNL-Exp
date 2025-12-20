package com.krowfeather.dao;

import com.krowfeather.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long> {
}
