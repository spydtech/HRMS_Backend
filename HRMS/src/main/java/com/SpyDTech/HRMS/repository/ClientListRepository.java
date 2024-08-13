package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.ClientList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientListRepository extends JpaRepository<ClientList,Long> {
    Optional<ClientList> findByCompanyNameAndRole(String companyName, String role);



    List<ClientList> findByCompanyName(String client);
}
