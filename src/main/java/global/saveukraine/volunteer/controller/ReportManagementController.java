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

@Log4j2
@Controller
@RequestMapping("management/reports")
@AllArgsConstructor
public class ReportManagementController {

  private final ReportRepository reportRepository;

  private final ImageRepository imageRepository;

  private final S3ImageStorage imageStorage;

  @GetMapping
  public String reportList(Model model) {
    model.addAttribute("reportList", reportRepository.findByOrderByDateTimeOfDesc());
    return "management/report/report-list";
  }

  @GetMapping("add")
  public String reportAdd() {
    return "management/report/report-add";
  }

  @Transactional
  @PostMapping("add")
  public String reportCreate(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam MultipartFile[] images) {
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

    return "redirect:/management/reports?created=true";
  }

  @GetMapping("edit/{id}")
  public String reportEditPage(@PathVariable Long id,
                               Model model) {
    Report report = reportRepository.findById(id)
      .orElseThrow(() -> new ReportNotFoundException("Report not found"));

    model.addAttribute("report", report);
    return "management/report/report-edit";
  }

  @PostMapping("edit")
  public String reportEdit(@RequestParam Long id,
                           @RequestParam String title,
                           @RequestParam String description) {
    Report report = reportRepository.findById(id)
      .orElseThrow(() -> new ReportNotFoundException("Report not found"));

    report.setTitle(title);
    report.setDescription(description);

    reportRepository.save(report);
    return "redirect:/management/reports?edited=true";
  }

  @PostMapping("delete")
  public String reportDelete(@RequestParam Long id) {
    Report report = reportRepository.findById(id)
      .orElseThrow(() -> new ReportNotFoundException("Report not found"));

    report.getImages().stream()
      .map(Image::getName)
      .forEach(imageStorage::remove);

    reportRepository.delete(report);
    return "redirect:/management/reports?deleted=true";
  }
}
