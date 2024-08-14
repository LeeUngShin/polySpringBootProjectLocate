package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.entity.GoodsCategoryEntity;
import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsImageEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.GoodsCategoryRepository;
import com.example.polySpringBootProject.repository.GoodsImageRepository;
import com.example.polySpringBootProject.repository.GoodsRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    GoodsImageRepository goodsImageRepository;

    @Autowired
    FileUploadService fileUploadService;

    public boolean logout(HttpSession session) {

        String id = (String) session.getAttribute("loginId");
        if(id != null && !id.isEmpty()) {
            System.out.println("로그아웃 할려고 했더닌 세션이 널이 아니다");
            session.invalidate();
            return true;
        } else {
            System.out.println("로그아웃 할려고 했더닌 세션이 널이당");
            return false;
        }
    }

    public Page<MemberDto> getNotApprovalMember(Pageable pageable){
        
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;  // 한페이지에 보여줄 회원 수
        Page<MemberEntity> memberEntityPage = memberRepository.findByApproval(PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "num")), "N");
        Page<MemberDto> memberDtoPage = memberEntityPage.map
                (member -> new MemberDto(member.getNum(), member.getId()));
        return memberDtoPage;
    }

    public boolean memberApproval(String id){
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if(memberEntity.isEmpty()){
            return false;
        }
        MemberEntity member = memberEntity.get();
        member.setApproval("Y");
        memberRepository.save(member);
        return true;
    }

    public Page<MemberDto> getMemberList(Pageable pageable) {
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;  // 한페이지에 보여줄 회원 수
        Page<MemberEntity> memberEntityPage = memberRepository.findAll(PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "num")));
        Page<MemberDto> memberDtoPage = memberEntityPage.map
                (member -> new MemberDto(member.getNum(), member.getId(), member.getPost(), member.getAddr(), member.getAddrDetail(), member.getEmail(), member.getApproval(), member.getCreatedTime()));
        return memberDtoPage;
    }


    public String goodsRegister(GoodsDto goodsDto){

        Optional<GoodsCategoryEntity> goodsCategoryEntity = goodsCategoryRepository.findByCategoryName(goodsDto.getGoodsCategory());
        if(goodsCategoryEntity.isEmpty()){
            return "notFoundCategory";
        }
        GoodsCategoryEntity goodsCategory = goodsCategoryEntity.get();

        int duplicateNameCnt = goodsRepository.countByName(goodsDto.getGoodsName());


        try{
            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name(goodsDto.getGoodsName())
                    .price(goodsDto.getPrice())
                    .stock(goodsDto.getStock())
                    .explanation(goodsDto.getGoodsExplanation())
                    .del("N")
                    .goodsCategory(goodsCategory)
                    .build();
            GoodsEntity savedGoodsEntity = goodsRepository.save(goodsEntity);

            boolean goodsImageUpload = fileUploadService.GoodsImageUpload(goodsDto.getGoodsImageFile());
            if(goodsImageUpload==false){
                return "imageUploadFail";
            }

            GoodsImageEntity goodsImageEntity = GoodsImageEntity.builder()
                    .originalFileName(fileUploadService.getOriginalFileName())
                    .storedFileName(fileUploadService.getStoredFileName())
                    .uploadPath(fileUploadService.getUploadPath())
                    .storedFileNameWithExtension(fileUploadService.getStoredFileNameWithExtension())
                    .goodsEntity(savedGoodsEntity)
                    .build();

            goodsImageRepository.save(goodsImageEntity);
            return "success";
        }catch (DataAccessException e){
            System.out.println("상품등록 중 DB 관련 예외 발생");
            e.printStackTrace();
            if(duplicateNameCnt>=1){
                return "duplicateName";
            }
            return "DBFail";
        }catch (Exception e){
            System.out.println("상품등록 중 DB 관련 아닌 예외 발생");
            e.printStackTrace();
            return "fail";
        }
    }
};