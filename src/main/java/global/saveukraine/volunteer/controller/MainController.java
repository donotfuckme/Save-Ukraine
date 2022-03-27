package global.saveukraine.volunteer.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/about-us")
  public String aboutUs() {
    return "about-us";
  }

  @GetMapping("/contacts")
  public String contacts() {
    return "contacts";
  }

  @GetMapping("/reports")
  public String reports() {
    return "reports";
  }

  @GetMapping("/reports/{id}")
  public String report(@PathVariable String id) {
    return "report";
  }

  @GetMapping("/help")
  public String howCanIHelp() {
    return "help";
  }

  @GetMapping("/help/become-volunteer")
  public String becomeVolunteer() {
    return "become-volunteer";
  }

  @GetMapping("/help/donation")
  public String donation() {
    return "donation";
  }
}
