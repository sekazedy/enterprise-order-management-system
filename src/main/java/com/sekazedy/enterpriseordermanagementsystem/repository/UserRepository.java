package com.sekazedy.enterpriseordermanagementsystem.repository;

import com.sekazedy.enterpriseordermanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
