package com.CpyBAgy.javarush.Repositories;

import com.CpyBAgy.javarush.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>, JpaSpecificationExecutor<Owner> {
}
