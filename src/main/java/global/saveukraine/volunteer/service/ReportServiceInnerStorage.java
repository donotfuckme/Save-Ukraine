package global.saveukraine.volunteer.service;

import global.saveukraine.volunteer.model.Report;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

@Service
public class ReportServiceInnerStorage implements ReportService {

  private final List<Report> reports = new ArrayList<>();

  private long increment = 0;

  public ReportServiceInnerStorage() {
    Report testReport = new Report();
    long id = ++increment;
    testReport.setId(id);
    testReport.setTitle("Заголовок");
    testReport.setDescription("Ну не очень длинное описание, однако оно есть.");
    testReport.setDateTimeOf(LocalDateTime.now());
    reports.add(testReport);
  }

  @Override
  public void save(Report report) {
    if (report.getId() > 0) {
      reports.stream()
        .filter(reportInStorage -> reportInStorage.getId() == report.getId())
        .forEach(reportInStorage -> copyReportData(report, reportInStorage));
    } else {
      long id = ++increment;
      report.setId(id);
      reports.add(report);
    }
  }

  private void copyReportData(Report from, Report to) {
    to.setTitle(from.getTitle());
    to.setDateTimeOf(from.getDateTimeOf());
    to.setDescription(from.getDescription());
    to.setImages(from.getImages());
  }

  @Override
  public Optional<Report> findById(long id) {
    return reports.stream()
      .filter(report -> report.getId() == id)
      .findFirst()
      .filter(Objects::nonNull)
      .map(report -> {
        Report returned = new Report();
        returned.setId(report.getId());
        copyReportData(report, returned);
        return returned;
      });
  }

  @Override
  public boolean remove(Report report) {
    return reports.removeIf(reportInStorage -> reportInStorage.getId() == report.getId());
  }

  @Override
  public List<Report> getAll() {
    return unmodifiableList(reports);
  }
}
