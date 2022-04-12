package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.model.VolunteerUserOrderDto;
import global.saveukraine.volunteer.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.String.format;

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
  public String donation(@RequestParam(required = false) String project,
                         Model model) {
    model.addAttribute("selectedProject", project);
    return "donation";
  }

  @GetMapping("donation/redirect")
  public String donationRedirect(@RequestParam(required = false) String project) {
    final String redirectUrl;
    switch (project) {
      case "aid all":
        redirectUrl = "sd948bd9510bf";
        break;
      case "aid children":
        redirectUrl = "s22ec69b4128f";
        break;
      case "aid animals":
        redirectUrl = "s32b628afc8e9";
        break;
      case "aid kharkiv":
        redirectUrl = "s3a4c4e8b66a3";
        break;
      case "aid aged":
        redirectUrl = "s75b9292b25d3";
        break;
      default:
        redirectUrl = "/";
    }

    return format("redirect:https://secure.wayforpay.com/payment/%s", redirectUrl);
  }
}
