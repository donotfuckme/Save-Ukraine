package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.entity.Report;
import global.saveukraine.volunteer.repo.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

  private final ReportRepository reportRepository;

  @GetMapping
  public String index(Model model) {
    List<Report> latestReports = reportRepository.findByOrderByDateTimeOfAsc()
      .stream()
      .limit(2)
      .collect(Collectors.toList());

    model.addAttribute("reports", latestReports);
    return "index";
  }

  @GetMapping("index")
  public String indexPage() {
    return "forward:/";
  }

  @GetMapping("about-us")
  public String aboutUs() {
    return "about-us";
  }

  @GetMapping("about-founder")
  public String aboutFounder() {
    return "about-founder";
  }

  @GetMapping("about-kharkiv")
  public String aboutKharkiv() {
    return "about-kharkiv";
  }

  @GetMapping("contacts")
  public String contacts() {
    return "contacts";
  }
}
