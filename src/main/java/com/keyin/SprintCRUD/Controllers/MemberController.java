package com.keyin.SprintCRUD.Controllers;

import com.keyin.SprintCRUD.AccessingDataMySQL.Member;
import com.keyin.SprintCRUD.AccessingDataMySQL.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/member")
public class MemberController {
  private final MemberRepository memberRepository;
  @Autowired
  public MemberController(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @PostMapping(path="/add")
  public @ResponseBody String addNewMember (@RequestBody Member newMember) {
    Member n = new Member();
    n.setName(newMember.getName());
    n.setAddress(newMember.getAddress());
    n.setEmail(newMember.getEmail());
    n.setPhone(newMember.getPhone());
    n.setMembershipStartDate(newMember.getMembershipStartDate());
    n.setMembershipLength(newMember.getMembershipLength());
    n.setMembershipType(newMember.getMembershipType());
    memberRepository.save(n);
    return ("Member \""+ n.getName() +"\" Saved To Database");
  }

  @PutMapping(path="/update/{id}")
  String UpdateMemberById(@RequestBody Member updateMember, @PathVariable Integer id) {
    return memberRepository.findById(id)
        .map(member -> {
          member.setName(updateMember.getName());
          member.setAddress(updateMember.getAddress());
          member.setPhone(updateMember.getPhone());
          member.setEmail(updateMember.getEmail());
          member.setMembershipStartDate(updateMember.getMembershipStartDate());
          member.setMembershipLength(updateMember.getMembershipLength());
          member.setMembershipType(updateMember.getMembershipType());
          memberRepository.save(member);
          return "Member #" + id + ": " +member.getName() + " Updated";
        }).orElseGet(() -> {
          memberRepository.save(updateMember);
          return "Member #" + id + ": " + updateMember.getName() + " Updated";
        });
  }

  @DeleteMapping(path="/delete")
  public @ResponseBody String deleteMemberById(int id) {
    memberRepository.deleteById(id);
    return "Member Deleted";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Member> getAllMembers() {
    return memberRepository.findAll();
  }

}
