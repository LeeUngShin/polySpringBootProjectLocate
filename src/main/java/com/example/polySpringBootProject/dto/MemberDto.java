package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.MemberEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
public class MemberDto {

    private Long num;

    @NotBlank(message = "아이디를 입력하세요")
    private String id;
    
    @NotBlank(message = "패스워드를 입력하세요")
    private String pw;
    
    private String pwCheck;
    
    @NotBlank(message = "이름을 입력하세요")
    private String name;
    
    @NotBlank(message = "주소 입력하세요")
    private String post;
    
    @NotBlank(message = "주소를 입력하세요")
    private String addr;
    
    @NotBlank(message = "상세주소를 입력하세요")
    private String addrDetail;
    
    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    private String approval;

    private String regTime;

    public MemberDto(Long num, String id){
        this.num = num;
        this.id = id;
    }

    public MemberDto(Long num, String id, String post, String addr, String addrDetail, String email, String approval, LocalDateTime regTime){
        this.num = num;
        this.id = id;
        this.post = post;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.email = email;
        this.approval = approval;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static MemberDto entityToDto(MemberEntity member) {

        MemberDto joinForm = new MemberDto();
        joinForm.setId(member.getId());
        joinForm.setPw(member.getPw());
        joinForm.setName(member.getName());
        joinForm.setAddr(member.getAddr());
        joinForm.setAddrDetail(member.getAddrDetail());
        joinForm.setPost(member.getPost());
        joinForm.setEmail(member.getEmail());
        joinForm.setApproval(member.getApproval());
        return joinForm;
    }
}
