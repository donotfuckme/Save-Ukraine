package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.entity.Report;
import global.saveukraine.volunteer.exception.ReportNotFoundException;
import global.saveukraine.volunteer.repo.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

  private final ReportRepository reportRepository;

  @GetMapping
  public String reports(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "8") int size,
                        Model model) {
    Pageable paging = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "dateTimeOf"));

    Page<Report> reportPage = reportRepository.findByOrderByDateTimeOfDesc(paging);
    model.addAttribute("reportPage", reportPage);

    int totalPages = reportPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
        .boxed()
        .collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "reports";
  }

  @GetMapping("{id}")
  public String report(@PathVariable long id,
                       Model model) {
    reportRepository.findById(id)
      .map(report -> {
        model.addAttribute("report", report);
        LocalDateTime dateTimeOf = report.getDateTimeOf();
        reportRepository.findFirstWithDateTimeOfBeforeOrderByDateTimeOfDesc(dateTimeOf)
          .ifPresent(reportBefore -> model.addAttribute("reportBefore", reportBefore));

        reportRepository.findFirstWithDateTimeOfAfterOrderByDateTimeOfAsc(dateTimeOf)
          .filter(reportAfter -> !Objects.equals(reportAfter.getId(), report.getId()))
          .ifPresent(reportAfter -> model.addAttribute("reportAfter", reportAfter));

        return report;
      })
      .orElseThrow(() -> new ReportNotFoundException("Cannot found report by id"));

    return "report";
  }
}
