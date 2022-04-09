package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.exception.ReportNotFoundException;
import global.saveukraine.volunteer.service.ReportService;
import global.saveukraine.volunteer.service.S3ImageStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

  private final ReportService reportService;

  private final S3ImageStorage imageStorage;

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

  @GetMapping
  public String createPage() {
    return "create-report";
  }

  @PostMapping
  public String create(@RequestParam("images") MultipartFile[] images) {
    Arrays.stream(images).sequential()
      .map(imageStorage::save)
      .collect(Collectors.toList());

    return "redirect:reports/1";
  }
}
