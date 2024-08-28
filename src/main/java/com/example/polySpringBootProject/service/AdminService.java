package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.enumClass.BoardType;
import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.dto.GoodsDto;
import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.entity.*;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import com.example.polySpringBootProject.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardFileRepository boardFileRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsCategoryRepository goodsCategoryRepository;
    @Autowired
    GoodsSubCategoryRepository goodsSubCategoryRepository;
    @Autowired
    GoodsImageRepository goodsImageRepository;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    LikeRepository likeRepository;

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
                (member -> new MemberDto(member.getNum(), member.getId(), member.getPost(), member.getAddr(), member.getAddrDetail(), member.getEmail(), member.getApproval(), member.getCreatedTime(), member.getGrade().toString()));
        return memberDtoPage;
    }
    @Transactional
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
                (member -> new MemberDto(member.getNum(), member.getId(), member.getPost(), member.getAddr(), member.getAddrDetail(), member.getEmail(), member.getApproval(), member.getCreatedTime(), member.getGrade().toString()));
        return memberDtoPage;
    }

    @Transactional
    public boolean noticeBoardWrite(BoardDto boardDto, String adminId){

        Optional<MemberEntity> adminOptional = memberRepository.findById(adminId);
        System.out.println("어드민 정보 : " + adminOptional);
        if(adminOptional.isEmpty()) {
            throw new EntityNotFoundException("관리자 계정을 찾을 수 없습니다.");
        }
        MemberEntity adminMember = adminOptional.get();

        if(boardDto.getBoardFile().isEmpty()){
            BoardEntity board = BoardEntity.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .fileAttached(0)
                    .notice("Y")
                    .noticeTop(boardDto.getNoticeTop())
                    .secret(boardDto.getSecret())
                    .del(boardDto.getDelete())
                    .boardType(BoardType.NOTICE_BOARD)
                    .member(adminMember)
                    .build();
            boardRepository.save(board);
            return true;
        }else {
            boolean upload = fileUploadService.upload(boardDto.getBoardFile());
            if (upload) {
                BoardEntity board = BoardEntity.builder()
                        .title(boardDto.getTitle())
                        .content(boardDto.getContent())
                        .fileAttached(1)
                        .notice("Y")
                        .noticeTop(boardDto.getNoticeTop())
                        .secret(boardDto.getSecret())
                        .del(boardDto.getDelete())
                        .boardType(BoardType.NOTICE_BOARD)
                        .member(adminMember)
                        .build();
                BoardEntity savedBoard = boardRepository.save(board);
                Long noticeBoardNum = savedBoard.getNum();
                BoardFileEntity boardFileEntity = BoardFileEntity.builder()
                        .originalFileName(fileUploadService.getOriginalFileName())
                        .storedFileName(fileUploadService.getStoredFileName())
                        .uploadPath(fileUploadService.getUploadPath())
                        .storedFileNameWithExtension(fileUploadService.getStoredFileNameWithExtension())
                        .boardEntity(savedBoard)
                        .build();
                BoardFileEntity boardFileEntity1 = boardFileRepository.save(boardFileEntity);
                //adminMember.addBoard(savedBoard);
                //savedBoard.addBoardImg(boardFileEntity1);
                return true;
            }
        }
        return false;
    }

//    public MemberDto memberInfo(Long memberNum){
////        Optional<MemberEntity> memberEntity = memberRepository.findById(memberNum);
////        if(memberEntity.isEmpty()) throw new IllegalArgumentException("유효하지 않은 회원번호입니다.");
////        MemberEntity member = memberEntity.get();
//        MemberEntity member = memberRepository.findById(memberNum).orElseThrow(()-> new EntityNotFoundException("회원정보를 찾을 수 없습니다."));
//        MemberDto memberDto = MemberDto.entityToDto(member);
//        return memberDto;
//    }

    public Page<BoardDto> boardList(Pageable pageable){

        int page = pageable.getPageNumber()-1;
        int pageLimit = 7;

        Page<BoardEntity>boardEntityPage = boardRepository.findAllByDel(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), "N");
        Page<BoardDto> boardDtos = boardEntityPage.map
                (board -> new BoardDto(board.getNum(), board.getTitle(), board.getContent(),
                        board.getCreatedTime(), board.getMember().getId(), board.getNotice()
                        , board.getSecret(), board.getDel(),board.getBoardType()));
        return boardDtos;
    }
    
    public BoardDto boardDetail(Long boardNum){
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardNum);
        if(boardEntity.isEmpty() || boardEntity==null){
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        BoardEntity board = boardEntity.get();
        BoardDto boardDto = BoardDto.entityToDto(board);
        return boardDto;
    }
    
    @Transactional
    public boolean deleteBoard(Long boardNum){
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardNum);
        if(boardEntity.isEmpty() || boardEntity==null){
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        BoardEntity board = boardEntity.get();
        board.setDel("Y");

        BoardEntity deleteBoard = boardRepository.save(board);
        if(!deleteBoard.getDel().equals("Y")) {
            throw new RuntimeException("게시글 삭제 실패");
        }
        return true;
    }
    
    // 상품등록
    @Transactional
    public String goodsRegister(GoodsDto goodsDto){

        Optional<GoodsCategoryEntity> goodsCategoryEntity = goodsCategoryRepository.findBycategoryName(goodsDto.getGoodsCategory());
        if(goodsCategoryEntity.isEmpty()){
            throw new EntityNotFoundException("해당 카테고리를 찾을 수 없습니다.");
        }
        GoodsCategoryEntity goodsCategory = goodsCategoryEntity.get();

        Optional<GoodsSubCategoryEntity> goodsSubCategoryEntity = goodsSubCategoryRepository.findByCategoryName(goodsDto.getGoodsSubCategory());
        if(goodsSubCategoryEntity.isEmpty()){
            throw new EntityNotFoundException("해당 카테고리를 찾을 수 없습니다.");
        }
        GoodsSubCategoryEntity goodsSubCategory = goodsSubCategoryEntity.get();

        try{
            duplicateGoodsName(goodsDto.getGoodsName());

            GoodsEntity goodsEntity = GoodsEntity.builder()
                    .name(goodsDto.getGoodsName())
                    .price(goodsDto.getPrice())
                    .stock(goodsDto.getStock())
                    .explanation(goodsDto.getGoodsExplanation())
                    .del("N")
                    .goodsCategory(goodsCategory)
                    .goodsSubCategory(goodsSubCategory)
                    .kcal(goodsDto.getKcal())
                    .protein(goodsDto.getProtein())
                    .fat(goodsDto.getFat())
                    .natrium(goodsDto.getNatrium())
                    .sugar(goodsDto.getSugar())
                    .weight(goodsDto.getWeight())
                    .allergy(goodsDto.getAllergy())
                    .likeCnt(0)
                    .sellCnt(0)
                    .build();
            GoodsEntity saveGoods = goodsRepository.save(goodsEntity);

            boolean goodsImageUpload = fileUploadService.GoodsImageUpload(goodsDto.getGoodsImageFile());
            if(goodsImageUpload==false){
                return "imageUploadFail";
            }

            GoodsImageEntity goodsImageEntity = GoodsImageEntity.builder()
                    .originalFileName(fileUploadService.getOriginalFileName())
                    .storedFileName(fileUploadService.getStoredFileName())
                    .uploadPath(fileUploadService.getUploadPath())
                    .storedFileNameWithExtension(fileUploadService.getStoredFileNameWithExtension())
                    .goodsEntity(saveGoods)
                    .build();

            GoodsImageEntity goodsImage = goodsImageRepository.save(goodsImageEntity);
            System.out.println("상품등록 완료");
            System.out.println(saveGoods);
            System.out.println(saveGoods.getGoodsImageEntity());
            return "success";
        }catch (Exception e){
            System.out.println("상품등록 중 예외 발생");
            e.printStackTrace();
            return "fail";
        }
    }

    // 상품 상세정보
    public GoodsDto goodsDetail(Long num){
        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(num);
        if(goodsEntity.isEmpty() || goodsEntity == null){
            throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다.");
        }
        GoodsEntity goods = goodsEntity.get();
        GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goods);
        return goodsDto;
    }

    // 상품 삭제
    @Transactional
    public boolean goodsDelete(Long num){
        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(num);
        if(goodsEntity.isEmpty() || goodsEntity == null){
            throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다");
        }
        GoodsEntity goods = goodsEntity.get();
        goods.setDel("Y");
        GoodsEntity deleteGoods = goodsRepository.save(goods);
        if(!deleteGoods.getDel().equals("Y")){
            throw new RuntimeException("상품 삭제에 실패했습니다.");
        }
        return true;
    }

    // 현재 보고 있는 상품 정보 반환
    public GoodsDto goodsInfo(Long goodsNum){
        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(goodsNum);
        if(goodsEntity.isEmpty() || goodsEntity == null){
            throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다.");
        }
        GoodsEntity goods = goodsEntity.get();
        GoodsDto goodsDto = GoodsDto.entityToGoodsDto(goods);
        return goodsDto;
    }

    // 상품 수정
    @Transactional
    public GoodsDto goodsModify(Long goodsNum, GoodsDto goodsDto) {
        System.out.println("수정할 디티오의 이미지 : " + goodsDto.getGoodsImageFile().isEmpty());
        System.out.println("서비스에서 수정객체 가격 : " + goodsDto.getPrice());
        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(goodsNum);
        GoodsEntity goods = goodsEntity.get();
        if (goodsEntity.isEmpty() || goodsEntity == null) {
            throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다.");
        }

        if(goodsDto.getGoodsImageFile().isEmpty()) {
            goods.setName(goodsDto.getGoodsName());
            goods.setPrice(goodsDto.getPrice());
            goods.setStock(goodsDto.getStock());
            goods.setExplanation(goodsDto.getGoodsExplanation());
            goods.setKcal(goodsDto.getKcal());
            goods.setProtein(goodsDto.getProtein());
            goods.setFat(goodsDto.getFat());
            goods.setNatrium(goodsDto.getNatrium());
            goods.setSugar(goodsDto.getSugar());
            goods.setWeight(goodsDto.getWeight());
            GoodsEntity modifyGoodsEntity = goodsRepository.save(goods);
            System.out.println("수정 후 db에서 가져온 가격 : " + modifyGoodsEntity.getPrice());
            GoodsDto modifyGoodsDto = GoodsDto.entityToGoodsDto(modifyGoodsEntity);
            System.out.println("수정 후 db에서 가져와서 디티오 저장 가격 : " + modifyGoodsDto.getPrice());
            return modifyGoodsDto;
        }
        else {
            System.out.println("여기 걸리나?");
            goods.setName(goodsDto.getGoodsName());
            goods.setPrice(goodsDto.getPrice());
            goods.setStock(goodsDto.getStock());
            goods.setExplanation(goodsDto.getGoodsExplanation());
            goods.setKcal(goodsDto.getKcal());
            goods.setPrice(goodsDto.getProtein());
            goods.setFat(goodsDto.getFat());
            goods.setNatrium(goodsDto.getNatrium());
            goods.setSugar(goodsDto.getSugar());
            goods.setWeight(goodsDto.getWeight());
            GoodsEntity modifyGoodsEntity = goodsRepository.save(goods);

            GoodsImageEntity goodsImage = goodsImageRepository.findByGoodsEntityNum(goodsNum).orElseThrow(()->new EntityNotFoundException("해당 데이터를 찾을 수 없습니다."));
            boolean modifyGoodsImg = fileUploadService.GoodsImageUpload(goodsDto.getGoodsImageFile());
            if(modifyGoodsImg) {
                goodsImage.setOriginalFileName(fileUploadService.getOriginalFileName());
                goodsImage.setStoredFileName(fileUploadService.getStoredFileName());
                goodsImage.setStoredFileNameWithExtension(fileUploadService.getStoredFileNameWithExtension());
                goodsImage.setUploadPath(fileUploadService.getUploadPath());
            }
            GoodsImageEntity modifyGoodsImage = goodsImageRepository.save(goodsImage);

            GoodsDto modifyGoodsDto = GoodsDto.entityToGoodsDto(modifyGoodsEntity);
            return modifyGoodsDto;
        }
    }

    // 상품 목록 페이징
    public Page<GoodsDto> goodsList(Pageable pageable){
        int currentPage = pageable.getPageNumber() - 1;
        int pageLimit = 10;  // 한페이지에 보여줄 회원 수
        Page<GoodsEntity> goodsEntityPage = goodsRepository.findAllByDel(PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "num")), "N");
        Page<GoodsDto> goodsDtoPage = goodsEntityPage.map
                (goods -> new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), goods.getStock(), goods.getGoodsSubCategory().getCategoryName(), goods.getCreatedTime(), goods.getGoodsImageEntity().get(0).getStoredFileNameWithExtension()));
        return goodsDtoPage;
    }

    // 상품 이름 중복 체크
    private void duplicateGoodsName(String name) {
        int duplicateName = goodsRepository.countByName(name);
        if(duplicateName >= 1){
            throw new DuplicateKeyException("이미 존재하는 상품입니다.");
        }
    }

    // 상품 찜하기
    @Transactional
    public String likeAdd(Long memberNum, Long goodsNum){
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberNum);
        if(memberEntity.isEmpty()) throw new IllegalArgumentException("유효하지 않은 회원번호입니다.");
        MemberEntity member = memberEntity.get();

        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(goodsNum);
        if(goodsEntity.isEmpty()) throw new IllegalArgumentException("유효하지 않은 상품번호입니다.");
        GoodsEntity goods = goodsEntity.get();
        boolean existsLike = likePresent(memberNum, goodsNum);
        try {
            if(existsLike){
                return "alreadyLike";
            }
            LikeEntity likeEntity = LikeEntity.builder()
                    .goods(goods)
                    .member(member)
                    .build();
            // goods.addLike(likeEntity);
            // member.addLike(likeEntity);
            goods.setLikeCnt(goods.getLikeCnt()+1);
            goodsRepository.save(goods);
            LikeEntity saveLikeEntity = likeRepository.save(likeEntity);

            if(saveLikeEntity != null) return "likeComplete";
        }
        catch (Exception e){
            return "likeFail";
        }
        return "likeFail";
    }

    // 상품찜하기 삭제
    public String likeDelete(Long memberNum, Long goodsNum, long likeNum){
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberNum);
        if(memberEntity.isEmpty()) throw new IllegalArgumentException("유효하지 않은 회원번호입니다.");
        MemberEntity member = memberEntity.get();

        Optional<GoodsEntity> goodsEntity = goodsRepository.findById(goodsNum);
        if(goodsEntity.isEmpty()) throw new IllegalArgumentException("유효하지 않은 상품번호입니다.");
        GoodsEntity goods = goodsEntity.get();
        boolean existsLike = likePresent(memberNum, goodsNum);
        try {
            if(!existsLike){  // 찜 안돼어 있으면
                return "notLike";
            }
            likeRepository.deleteById(likeNum);
            return "likeDeleteComplete";

        }
        catch (Exception e){
            return "likeDeleteFail";
        }
    }

    // 내가 찜한 상품만 모아보기
//    public Page<GoodsDto> myLikeGoods(Pageable pageable, String loginId){
//        int page = pageable.getPageNumber() - 1;
//        int pageLimit = 7;
//        Page<GoodsEntity> goodsEntityPage = likeRepository.findByMemberId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), loginId);
//        Page<GoodsDto> goodsDtos = goodsEntityPage.map
//                (goods -> new GoodsDto(goods.getNum(), goods.getName(), goods.getPrice(), goods.getStock(), goods.getGoodsCategory().getCategoryName(),
//                        goods.getCreatedTime(), goods.getGoodsImageEntity().getStoredFileNameWithExtension()));
//        return goodsDtos;
//    }
    
    // 회원번호와 상품번호로 좋아요 있는지 확인 반환값이 true이면 찜한 상태
    public boolean likePresent(Long memberNum, Long goodsNum){
        return likeRepository.existsByMemberNumAndGoodsNum(memberNum, goodsNum);
    }
}