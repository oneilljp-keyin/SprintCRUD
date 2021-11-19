package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path="/bio")

public class MainController {
  @Autowired
  private BioRepository bioRepository;
  private AliasRepository aliasRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewBio (@RequestParam String real_name, Double height, Double weight, String dob, String dod, String status) {
    Bio n = new Bio();
    n.setReal_name(real_name);
    n.setDob(dob);
    n.setDod(dod);
    n.setHeight_cm(height);
    n.setWeight_kg(weight);
    n.setStatus(status);
    bioRepository.save(n);
    return "Saved" + n;
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Bio> getAllBios() {
    return bioRepository.findAll();
  }

  @GetMapping(path="")
  public @ResponseBody
  Optional<Bio> getBioById(@RequestParam int wid) {
    System.out.println("Wrestler ID: " + wid);
    return bioRepository.findById(wid);
  }

  @GetMapping(path="/alias")
  public @ResponseBody
  Optional<Alias> getAliasById(@RequestParam int wid) {
    return aliasRepository.findById(wid);
  }
}
