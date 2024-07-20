package com.example.polySpringBootProject.dto;

import com.example.polySpringBootProject.entity.MemberEntity;
import lombok.*;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
//  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
public class JoinDto {

    private String id;
    private String pw;
    private String name;
    private String post;
    private String addr;
    private String addrDetail;
    private int age;
    private String email;

    public static JoinDto entityToDto(MemberEntity member) {

        JoinDto joinForm = new JoinDto();
        joinForm.setId(member.getId());
        joinForm.setPw(member.getPw());
        joinForm.setName(member.getName());
        joinForm.setAddr(member.getAddr());
        joinForm.setAge(member.getAge());
        joinForm.setEmail(member.getEmail());

        return joinForm;
    }
}
