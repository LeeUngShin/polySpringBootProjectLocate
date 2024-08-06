package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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


//    @Modifying
//    @Transactional
//    @Query("update m from MemberEntity m set m.pw=:pw, m.name=:name, m.post=:post, m.addr=:addr, m.addrDetail=:addrDetail, m.email=:email" +
//            "where m.id=:id")
//
//    Optional<MemberEntity> updateById(@Param("id")String id,
//                                      @Param("pw")String pw, @Param("name") String name,
//                                      @Param("post") String post, @Param("addr") String addr,
//                                      @Param("addrDetail") String addrDetail, @Param("email") String email);

}

