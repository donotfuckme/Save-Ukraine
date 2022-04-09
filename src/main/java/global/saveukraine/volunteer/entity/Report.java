package global.saveukraine.volunteer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_report", nullable = false)
  private Long id;

  @NotBlank
  @Column(name = "title", nullable = false)
  private String title;

  @NotBlank
  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "datetimeof", nullable = false)
  private LocalDateTime dateTimeOf;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "report_images",
    joinColumns = @JoinColumn(name = "fk_report"),
    inverseJoinColumns = @JoinColumn(name = "fk_image")
  )
  private Set<Image> images = new HashSet<>();
}
