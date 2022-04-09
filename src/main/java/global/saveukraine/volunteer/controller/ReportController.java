package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.exception.ReportNotFoundException;
import global.saveukraine.volunteer.model.Report;
import global.saveukraine.volunteer.service.ReportService;
import global.saveukraine.volunteer.service.S3ImageStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Log4j2
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

  @GetMapping("create")
  public String createPage() {
    return "report-create";
  }

  @PostMapping("create")
  public String create(@RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("images") MultipartFile[] images) {
    log.debug("title -> {}", title);
    log.debug("description -> {}", description);
    log.debug("images -> {}", Arrays.toString(images));

    Report report = new Report();
    report.setTitle(title);
    report.setDescription(description);
    report.setDateTimeOf(LocalDateTime.now());

    List<String> imagePaths = Arrays.stream(images)
      .map(imageStorage::save)
      .collect(Collectors.toList());

    report.setImages(imagePaths.toArray(new String[images.length]));

    reportService.save(report);

    return format("redirect:/reports/%s", report.getId());
  }
}
