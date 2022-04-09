package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.model.VolunteerUserOrderDto;
import global.saveukraine.volunteer.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("help")
@RequiredArgsConstructor
public class HelpController {

  private final EmailService emailService;

  @GetMapping
  public String howCanIHelp() {
    return "help";
  }

  @GetMapping("become-volunteer")
  public String becomeVolunteer() {
    return "become-volunteer";
  }

  @PostMapping("become-volunteer")
  public String becomeVolunteerUserOrder(@ModelAttribute VolunteerUserOrderDto volunteerUserOrder) {
    emailService.sendMessage(volunteerUserOrder);
    return "become-volunteer";
  }

  @GetMapping("donation")
  public String donation() {
    return "donation";
  }
}
