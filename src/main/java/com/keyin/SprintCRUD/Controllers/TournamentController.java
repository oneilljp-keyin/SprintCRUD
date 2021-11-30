package com.keyin.SprintCRUD.Controllers;

import com.keyin.SprintCRUD.AccessingDataMySQL.Tournament;
import com.keyin.SprintCRUD.AccessingDataMySQL.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/tournament")
public class TournamentController {
  private final TournamentRepository tournamentRepository;
  @Autowired
  public TournamentController(TournamentRepository tournamentRepository) {
    this.tournamentRepository = tournamentRepository;
  }

  @PostMapping(path="/add")
  public @ResponseBody String addNewTournament (@RequestBody Tournament newTournament) {
    Tournament n = new Tournament();
    n.setLocation(newTournament.getLocation());
    n.setStart_date(newTournament.getStart_date());
    n.setEnd_date(newTournament.getEnd_date());
    n.setEntry_fee(newTournament.getEntry_fee());
    n.setTotal_cash_prize(newTournament.getTotal_cash_prize());
    tournamentRepository.save(n);
    return ("Tournament at \"" + n.getLocation() + "\" on " + n.getStart_date() + "saved to database");
  }

  @PutMapping (path="/update/{id}")
  String UpdateTournamentById(@RequestBody Tournament updateTournament, @PathVariable Integer id) {
    return tournamentRepository.findById(id)
    .map(tournament -> {
      tournament.setLocation(updateTournament.getLocation());
      tournament.setStart_date(updateTournament.getStart_date());
      tournament.setEnd_date(updateTournament.getEnd_date());
      tournament.setEntry_fee(updateTournament.getEntry_fee());
      tournament.setTotal_cash_prize(updateTournament.getTotal_cash_prize());
      tournamentRepository.save(updateTournament);
      return ("Tournament at \"" + tournament.getLocation() +"\" starting on " + tournament.getStart_date() + " has been updated");
    }).orElseGet(() -> {
      tournamentRepository.save(updateTournament);
      return ("Tournament at \"" + updateTournament.getLocation() +"\" starting on " + updateTournament.getStart_date() + " has been updated");
    });
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
