package global.saveukraine.volunteer.repo;

import global.saveukraine.volunteer.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

  List<Report> findByOrderByDateTimeOfAsc();

  @Query(value = "select * from reports r where r.dateTimeOf < :dateTimeOf order by r.dateTimeOf desc limit 1", nativeQuery = true)
  Optional<Report> findFirstWithDateTimeOfBeforeOrderByDateTimeOfDesc(@Param("dateTimeOf") LocalDateTime dateTimeOf);

  @Query(value = "select * from reports r where r.dateTimeOf > :dateTimeOf order by r.dateTimeOf asc limit 1", nativeQuery = true)
  Optional<Report> findFirstWithDateTimeOfAfterOrderByDateTimeOfAsc(@Param("dateTimeOf") LocalDateTime dateTimeOf);
}
