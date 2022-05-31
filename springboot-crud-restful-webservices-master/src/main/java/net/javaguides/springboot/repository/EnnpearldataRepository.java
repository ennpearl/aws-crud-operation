package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.entity.Ennpearldata;

@Repository
public interface EnnpearldataRepository extends JpaRepository<Ennpearldata, Long>{

}
