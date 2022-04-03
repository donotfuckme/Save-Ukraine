package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.exception.ReportNotFoundException;
import global.saveukraine.volunteer.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @GetMapping
  public String reports(Model model) {
    model.addAttribute("reports", reportService.getAll());
    return "reports";
  }

  @GetMapping("{id}")
  public String report(@PathVariable long id,
                       Model model) {
    reportService.findById(id)
      .map(report -> model.addAttribute("report", report))
      .orElseThrow(() -> new ReportNotFoundException("Cannot found report by id"));

    return "report";
  }
}
