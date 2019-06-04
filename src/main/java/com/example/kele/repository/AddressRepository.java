package com.example.kele.repository;

import com.example.kele.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author X1 Carbon
 * @TIME 2019/6/4 10:46
 * kele-practise & com.example.kele.repository
 */
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}
