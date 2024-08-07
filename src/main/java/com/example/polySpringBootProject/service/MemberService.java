package com.example.polySpringBootProject.service;

import com.example.polySpringBootProject.RoleType;
import com.example.polySpringBootProject.dto.BoardDto;
import com.example.polySpringBootProject.dto.JoinDto;
import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.MemberEntity;
import com.example.polySpringBootProject.repository.BoardRepository;
import com.example.polySpringBootProject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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

    @Autowired
    private final BoardRepository boardRepository;

    public boolean join(JoinDto joinDto, BindingResult bindingResult) {

        String encodePw = passwordEncoder.encode(joinDto.getPw());

        boolean duplicatedId = memberRepository.existsById(joinDto.getId());
        System.out.println("중복아이디 존재 " + duplicatedId);
        if(duplicatedId==true){ // 중복 아이디 존재하면
            // field 매개변수는 커맨드 객체의 매개변수와 동일해야 함
            bindingResult.rejectValue("id", "duplicatedId", "중복된 아이디입니다.");
            return false;
        }else if (!joinDto.getPw().equals(joinDto.getPwCheck())){
            bindingResult.rejectValue("pwCheck", "pwInCorrect", "비밀번호가 불일치합니다.");
            return  false;
        }

        MemberEntity member = MemberEntity.builder()
                .id(joinDto.getId())
                .pw(encodePw)
                .name(joinDto.getName())
                .post(joinDto.getPost())
                .addr(joinDto.getAddr())
                .addrDetail(joinDto.getAddrDetail())
                .email(joinDto.getEmail())
                .role(RoleType.ROLE_USER)
                .approval("N")
                .build();

        if (memberRepository.save(member) != null) {
            return true;
        }
        return false;

    }

    public String login(String id, String pw, HttpSession session) {
        long cnt = memberRepository.countById(id);
        Optional<MemberEntity> user = memberRepository.findById(id);
        if (cnt == 1) {
            if (user.isPresent()) { // 입력한 id 조회결과 있음
                MemberEntity member = user.get();
                System.out.println("로그인한 회원정보 : " + member);
                boolean login = passwordEncoder.matches(pw, member.getPw()); // 입력한 pw와 암호화된 pw 일치 여부
                if (login && member.getApproval().equals("Y")) {
                    session.setAttribute("loginId", member.getId());
                    // session.setMaxInactiveInterval(600);
                    // JoinDto joinForm = JoinDto.entityToDto(member);
                    return "success";
                }
                else if(login && member.getApproval().equals("N")){
                    return "notApproval";
                }
            } else {
                return "fail";
            }
        }
        return "fail";
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

    @Transactional
    public boolean modify(JoinDto modifyForm) {

        Optional<MemberEntity> member = memberRepository.findById(modifyForm.getId());
        if(!member.isPresent()) {
            return false;
        }

        MemberEntity mem = member.get();
        System.out.println("멤버 : " + mem);

        String encodePw = passwordEncoder.encode(modifyForm.getPw());


        /*if(joinForm.getPw() == null || joinForm.getPw().equals("")) { // 비번 미입력 시 원래비번 유지
            joinForm.setPw(encodePw);  // 암호화된 기존 비밀번호
        } else {
            encodePw = passwordEncoder.encode(joinForm.getPw());  // 새로운 아이디 암호화
        }*/

        mem.setPw(encodePw);
        mem.setAddr(modifyForm.getAddr());
        mem.setAddrDetail(modifyForm.getAddrDetail());
        mem.setEmail(modifyForm.getEmail());
        mem.setName(modifyForm.getName());
        mem.setPost(modifyForm.getPost());

        try {
            memberRepository.save(mem); // 엔티티를 직접 저장
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로깅
            return false;
        }
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
            return "비밀번호 찾기 실패";
        }
    }

    public Page<BoardDto> myBoardList(Pageable pageable, String myId){

        int page = pageable.getPageNumber()-1;
        int pageLimit = 7;

        Page<BoardEntity> myboardEntities = boardRepository.findByMemberId(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), myId);
        Page<BoardDto> myboardDtos = myboardEntities.map
                (myboard -> new BoardDto(myboard.getNum(), myboard.getTitle(), myboard.getContent(),
                        myboard.getCreatedTime(), myboard.getMember().getId(), myboard.getNotice(),
                        myboard.getSecret(), myboard.getDel()));
        return myboardDtos;
    }

    public Page<BoardDto> myBoardSearch(Pageable pageable, String myId, String category, String keyword){
        int page = pageable.getPageNumber()-1;
        int pageLimit = 3;

        Page<BoardEntity> myBoardSearchEntities = null;
        if(category.equals("title")){
            myBoardSearchEntities = boardRepository.findByMemberIdAndTitleContaining(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")),myId, keyword);
        }else if(category.equals("content")){
            myBoardSearchEntities = boardRepository.findByMemberIdAndTitleContaining(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")),myId, keyword);
        }else{
            return null;
        }

        Page<BoardDto> myBoardSearchList = myBoardSearchEntities.map
                (myboard -> new BoardDto(myboard.getNum(), myboard.getTitle(), myboard.getContent(),
                        myboard.getCreatedTime(), myboard.getMember().getId(), myboard.getNotice(),
                        myboard.getSecret(), myboard.getDel()));
        return myBoardSearchList;
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