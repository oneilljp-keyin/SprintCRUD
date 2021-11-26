package com.keyin.SprintCRUD.Controllers;

import com.keyin.SprintCRUD.AccessingDataMySQL.Member;
import com.keyin.SprintCRUD.AccessingDataMySQL.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(path="/member")
public class MemberController {
  @Autowired
  private MemberRepository memberRepository;

//  @GetMapping(path="/{id}")
//  public @ResponseBody Optional<Member> getMemberById(@PathParam("id") Integer id) {
//    return memberRepository.findById(id);
//  }
//
  @PostMapping(path="/add")
  public @ResponseBody String addNewMember (String name, String address, String email,
                                            String phone, LocalDate startDate, Integer lengthInMonths,
                                            String membershipType) {
    Member n = new Member();
    n.setName(name);
    n.setAddress(address);
    n.setEmail(email);
    n.setPhone(phone);
    n.setMembershipStartDate(startDate);
    n.setMembershipLength(lengthInMonths);
    n.setMembershipType(membershipType);
    memberRepository.save(n);
    return "Member Saved";
  }

  @PatchMapping(path="/update")
  public @ResponseBody String UpdateMemberById(Integer id, String name, String address, String email,
                                               String phone, LocalDate startDate, Integer lengthInMonths,
                                               String membershipType) {
    Optional<Member> u = memberRepository.findById(id);
    if(u.isPresent()) {
      Member updatedMember = u.get();
      if(name != null) {updatedMember.setName(name);}
      if(address != null) {updatedMember.setAddress(address);}
      if(email != null) {updatedMember.setEmail(email);}
      if(phone != null) {updatedMember.setPhone(phone);}
      if(startDate != null) {updatedMember.setMembershipStartDate(startDate);}
      if(lengthInMonths != 0) {updatedMember.setMembershipLength(lengthInMonths);}
      if(membershipType != null) {updatedMember.setMembershipType(membershipType);}
      memberRepository.save(updatedMember);
    }
    return "Member Updated";
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
