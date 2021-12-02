package com.keyin.SprintCRUD.Controllers;

import com.keyin.SprintCRUD.AccessingDataMySQL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/results")
public class ResultsController {
  private final ResultRepository resultRepository;
  private final MemberRepository memberRepository;
  private final TournamentRepository tournamentRepository;
  @Autowired
  public ResultsController(ResultRepository resultRepository, MemberRepository memberRepository, TournamentRepository tournamentRepository) {
    this.resultRepository = resultRepository;
    this.memberRepository = memberRepository;
    this.tournamentRepository = tournamentRepository;
  }

  @PostMapping(path="/add")
  public @ResponseBody String addNewResult (@RequestBody Result newResult) {
    Result n = new Result();
    n.setResult(newResult.getResult());
    n.setMember(newResult.getMember());
    n.setTournament(newResult.getTournament());
    resultRepository.save(n);
    return ("Member results saved");
  }

  @PutMapping (path="/update/{id}")
  String UpdateResultById(@RequestBody Result updateResult, @PathVariable Integer id) {
    return resultRepository.findById(id)
    .map(result -> {
      result.setResult(updateResult.getResult());
      result.setMember(updateResult.getMember());
      result.setTournament(updateResult.getTournament());
      resultRepository.save(updateResult);
      return ("Result for member has been updated");
    }).orElseGet(() -> {
      resultRepository.save(updateResult);
      return ("Result for member has been updated");
    });
  }

  @DeleteMapping(path="/delete")
  public @ResponseBody String deleteResultById(int id) {
    resultRepository.deleteById(id);
    return "Result Deleted";
  }

  @GetMapping(path="/member/{id}")
  public @ResponseBody Iterable<Result> getAllResults(@PathVariable int id) {
    Optional<Member> searchMember = memberRepository.findById(id);
    return resultRepository.findByMember(searchMember);
  }


  @GetMapping(path="/tournament/{id}")
  public @ResponseBody
  Iterable<Result> getAllResultsByTournamentId(@PathVariable int id) {
    Optional<Tournament> searchTournament = tournamentRepository.findById(id);
    return resultRepository.findByTournamentOrderByResult(searchTournament);
  }

}
