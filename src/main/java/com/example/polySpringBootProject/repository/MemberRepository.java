package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Long countByIdAndPw(String id, String pw);
    Long countById(String id);


    Optional<MemberEntity> findById(String id);

    @Transactional
    Long deleteById(String id);

    Optional<MemberEntity> findByName(String name);
    Optional<MemberEntity> findByNameAndEmail(String name, String email);

    boolean existsById(String id);
}

