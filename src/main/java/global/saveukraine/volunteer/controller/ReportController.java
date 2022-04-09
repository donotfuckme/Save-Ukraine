package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.entity.Image;
import global.saveukraine.volunteer.entity.Report;
import global.saveukraine.volunteer.exception.ReportNotFoundException;
import global.saveukraine.volunteer.repo.ImageRepository;
import global.saveukraine.volunteer.repo.ReportRepository;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Log4j2
@Controller
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

  private final ReportRepository reportRepository;

  private final ImageRepository imageRepository;

  private final S3ImageStorage imageStorage;

  @GetMapping
  public String reports(Model model) {
    model.addAttribute("reports", reportRepository.findAll());
    return "reports";
  }

  @GetMapping("{id}")
  public String report(@PathVariable long id,
                       Model model) {
    reportRepository.findById(id)
      .map(report -> model.addAttribute("report", report))
      .orElseThrow(() -> new ReportNotFoundException("Cannot found report by id"));

    return "report";
  }

  @GetMapping("create")
  public String createPage() {
    return "report-create";
  }

  @Transactional
  @PostMapping("create")
  public String create(@RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("images") MultipartFile[] images) {
    List<String> imageNames = Arrays.stream(images)
      .map(imageStorage::save)
      .collect(Collectors.toList());

    Set<Image> imageSet = imageNames.stream()
      .map(imageName -> {
        Image image = new Image();
        image.setName(imageName);
        return image;
      })
      .map(imageRepository::save)
      .collect(Collectors.toSet());

    Report report = new Report();
    report.setTitle(title);
    report.setDescription(description);
    report.setDateTimeOf(LocalDateTime.now());
    report.setImages(imageSet);
    reportRepository.save(report);

    return format("redirect:/reports/%s", report.getId());
  }
}
