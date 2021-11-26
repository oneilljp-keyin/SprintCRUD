package com.keyin.SprintCRUD.Controllers;

import com.keyin.SprintCRUD.AccessingDataMySQL.Tournament;
import com.keyin.SprintCRUD.AccessingDataMySQL.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(path="/tournament")
public class TournamentController {
  @Autowired
  private TournamentRepository tournamentRepository;

//  @GetMapping
//  public @ResponseBody Optional<Tournament> getTournamentById(int id) {
//    return tournamentRepository.findById(id);
//  }

  @PostMapping(path="/add")
  public @ResponseBody String addNewTournament (String location, LocalDate startDate, LocalDate endDate,
                                                Integer entryFee, Integer totalCash) {
    Tournament n = new Tournament();
    n.setLocation(location);
    n.setStart_date(startDate);
    n.setEnd_date(endDate);
    n.setEntry_fee(entryFee);
    n.setTotal_cash_price(totalCash);
    tournamentRepository.save(n);
    return "Saved " + n;
  }

  @PatchMapping(path="/update")
  public @ResponseBody String UpdateTournamentById(int id, String location, LocalDate startDate, LocalDate endDate,
                                               int entryFee, int totalCash) {
    Optional<Tournament> u = tournamentRepository.findById(id);
    if(u.isPresent()) {
      Tournament updateTournament = u.get();
      if(location != null) {updateTournament.setLocation(location);}
      if(startDate != null) {updateTournament.setStart_date(startDate);}
      if(endDate != null) {updateTournament.setEnd_date(endDate);}
      if(entryFee != 0) {updateTournament.setEntry_fee(entryFee);}
      if(totalCash != 0) {updateTournament.setTotal_cash_price(totalCash);}
      tournamentRepository.save(updateTournament);
    }
    return "Tournament Updated";
  }

  @DeleteMapping(path="/delete")
  public @ResponseBody String deleteTournamentById(int id) {
    tournamentRepository.deleteById(id);
    return "User Deleted";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Tournament> getAllTournaments() {
    return tournamentRepository.findAll();
  }

}
