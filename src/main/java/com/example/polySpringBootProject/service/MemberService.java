package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.RoleType;
import com.example.polySpringBootProject.dto.JoinDto;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public boolean join(JoinDto joinForm) {

        String encodePw = passwordEncoder.encode(joinForm.getPw());

        MemberEntity member = MemberEntity.builder()
                .id(joinForm.getId())
                .pw(encodePw)
                .name(joinForm.getName())
                .post(joinForm.getPost())
                .addr(joinForm.getAddr())
                .addrDetail(joinForm.getAddrDetail())
                .email(joinForm.getEmail())
                .role(RoleType.ROLE_USER)
                .build();

        if (memberRepository.save(member) != null) {
            return true;
        }
        return false;

    }

    public boolean login(String id, String pw, HttpSession session) {

        long cnt = memberRepository.countById(id);

        Optional<MemberEntity> user = memberRepository.findById(id);

        if (cnt == 1) {
            if (user.isPresent()) { // 입력한 id 조회결과 있음
                MemberEntity member = user.get();
                boolean login = passwordEncoder.matches(pw, member.getPw()); // 입력한 pw와 암호화된 pw 일치 여부
                if (login) {
                    session.setAttribute("loginId", member.getId());
                    session.setMaxInactiveInterval(600);
                    JoinDto joinForm = JoinDto.entityToDto(member);
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

//	public JoinForm login(String id, String pw, HttpSession session) {
//
//		long cnt = memberRepository.countById(id);
//
//		Optional<Member> user = memberRepository.findById(id);
//
//		if (cnt == 1) {
//			if (user.isPresent()) { // 입력한 id 조회결과 있음
//				Member member = user.get();
//				boolean login = passwordEncoder.matches(pw, member.getPw()); // 입력한 pw와 암호화된 pw 일치 여부
//				if (login) {
//					session.setAttribute("loginId", member.getId());
//					session.setMaxInactiveInterval(600);
//					JoinForm joinForm = JoinForm.entityToDto(member);
//					return joinForm;
//				}
//			} else {
//				return null;
//			}
//		}
//		return null;
//	}

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

    public boolean delete(String id, String pw, HttpSession session) {

        Optional<MemberEntity> member = memberRepository.findById(id);
        if(!member.isPresent()) return false;

        MemberEntity mem = member.get();

        if(passwordEncoder.matches(pw, mem.getPw())) {
            memberRepository.deleteById(id);
            session.invalidate();
            return true;
        }
        else {
            return false;
        }

//		if (session.getAttribute("member") != null) {
//			System.out.println("회원탈퇴하려고 했는데 세션이 널이 아니당");
//			Member sessionMember = (Member) session.getAttribute("member");
//			String id = sessionMember.getId(); // 현재 로그인 아이디
//			Optional<Member> member = memberRepository.findById(id);
//			if (passwordEncoder.matches(pw, member.get().getPw())) {
//				memberRepository.delete(member.get());
//				session.invalidate();
//				System.out.println("회원탈퇴시키고, 세션 무효화 했당");
//				return true;
//			}
//			return false;
//		} else {
//			System.out.println("회원탈퇴하려고 했는데 세션이 널이당");
//			return false;
//		}
    }

    public MemberEntity memberInfo(String id) {
        Optional<MemberEntity> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return (MemberEntity) member.get();
        }
        return null;
    }

    public JoinDto modifyForm(String id) {
        Optional<MemberEntity> member = memberRepository.findById(id);
        if(member.isPresent()) {
            MemberEntity mem = member.get();
            JoinDto joinForm = JoinDto.entityToDto(mem);
            return joinForm;
        }else {
            return null;
        }

    }

    public boolean modify(JoinDto joinForm) {

        Optional<MemberEntity> member = memberRepository.findById(joinForm.getId());
        if(!member.isPresent()) {
            return false;
        }

        MemberEntity mem = member.get();


        String encodePw = mem.getPw();

        if (joinForm.getPw() == null) { // 비번 미입력 시 원래비번 유지
            joinForm.setPw(encodePw);  // 암호화된 기존 비밀번호
        } else {
            encodePw = passwordEncoder.encode(joinForm.getPw());  // 새로운 아이디 암호화
        }

        MemberEntity mem2 = MemberEntity.builder().num(mem.getNum()).id(joinForm.getId()).pw(encodePw)
                .name(joinForm.getName()).addr(joinForm.getAddr()).email(joinForm.getEmail())
                .build();

        if (memberRepository.save(mem2) != null) {
            return true;
        }

        return false;
    }

    public String searchId(String name, String email) {
        Optional<MemberEntity> member = memberRepository.findByNameAndEmail(name, email);
        if (member.isPresent()) {
            String id = member.get().getId();
            System.out.println("이름이 있나?");
            return id;
        }
        System.out.println("이름이 없다");
        return "아이디 찾기 실패";
    }

    public String searchPw(String id) {

        Optional<MemberEntity> opMember = memberRepository.findById(id);
        String pw = "";
        if (opMember.isPresent()) {
            MemberEntity member = opMember.get();
            for (int i = 0; i < 5; i++) {
                int n = (int) (Math.random() * 3);
                if (n % 3 == 0) {
                    char c = (char) (((int) (Math.random() * 10)) + 48);
                    pw += c;
                } else if (n % 3 == 1) {
                    char c = (char) (((int) (Math.random() * 26)) + 65);
                    pw += c;
                } else {
                    char c = (char) (((int) (Math.random() * 26)) + 97);
                    pw += c;
                }
            }
            String encodePw = passwordEncoder.encode(pw);
            member.setPw(encodePw);
            memberRepository.save(member);
            return pw;
        } else {
            return "해당하는 아이디가 없습니다";
        }

    }

    public boolean existsId(String id) {
        Optional<MemberEntity> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


    public List<JoinDto> findAll(){
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<JoinDto> memberList = new ArrayList<>();

        for(MemberEntity member : memberEntityList) {
            JoinDto joinForm = JoinDto.entityToDto(member);
            memberList.add(joinForm);
        }
        return memberList;
    }

    public JoinDto findById(String id) {

        Optional<MemberEntity> member = memberRepository.findById(id);
        if(member.isPresent()) {
            MemberEntity mem = member.get();
            JoinDto joinForm = JoinDto.entityToDto(mem);
            return joinForm;
        }
        else {
            return null;
        }
    }
}