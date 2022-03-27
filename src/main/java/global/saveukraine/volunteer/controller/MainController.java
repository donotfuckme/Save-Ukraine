package global.saveukraine.volunteer.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/how-can-i-help")
  public String howCanIHelp() {
    return "how-can-i-help";
  }
}
