package global.saveukraine.volunteer.service;

import global.saveukraine.volunteer.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {

  void save(Report report);

  Optional<Report> findById(long id);

  boolean remove(Report report);

  List<Report> getAll();
}
