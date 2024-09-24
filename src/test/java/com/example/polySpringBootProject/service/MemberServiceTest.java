package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.dto.MemberDto;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
//@Transactional
class MemberServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;
    @Autowired
    BoardService service;

    @Test
    void join() {
        BindingResult bindingResult = new BindingResult() {
            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Map<String, Object> getModel() {
                return null;
            }

            @Override
            public Object getRawFieldValue(String field) {
                return null;
            }

            @Override
            public PropertyEditor findEditor(String field, Class<?> valueType) {
                return null;
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String errorCode) {
                return new String[0];
            }

            @Override
            public String[] resolveMessageCodes(String errorCode, String field) {
                return new String[0];
            }

            @Override
            public void addError(ObjectError error) {

            }

            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

            }

            @Override
            public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                return null;
            }

            @Override
            public Object getFieldValue(String field) {
                return null;
            }
        };
        for(int i = 6;i<20;i++){
            MemberDto member = MemberDto.builder()
                    .id("user" + i )
                    .accumulatedMoney(0)
                    .pw("A1234")
                    .pwCheck("A1234")
                    .name("사용자" + i)
                    .post("00000")
                    .addr("주소"+i)
                    .addrDetail("상세주소"+i)
                    .email("user"+i + "@test.org")
                    .phone("01000000000")
                    .build();
            memberService.join(member, bindingResult);

        }
    }

//    @Test
//    void myBoardList() {
//        Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "num"));
//        Page<BoardEntity> boardEntities = boardRepository.findByMemberId(pageable,"testuser20");
//        System.out.println("테스트 내 게시글 : " + boardEntities);
//        assertEquals(29, boardEntities.getTotalElements());
//
//    }
//
//    @Test
//    void entityTest(){
//        MemberEntity member = memberRepository.findById(1L).get();
//        System.out.println("******************************");
//        //System.out.println(member);
//        System.out.println("******************************");
//        System.out.println(member.getBoardDatas());
//    }
}