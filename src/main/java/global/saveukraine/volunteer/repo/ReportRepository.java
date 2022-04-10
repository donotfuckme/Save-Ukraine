package global.saveukraine.volunteer.repo;

import global.saveukraine.volunteer.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

  List<Report> findByOrderByDateTimeOfAsc();
}
