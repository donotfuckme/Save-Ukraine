package global.saveukraine.volunteer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Report {

  private long id;

  private String title;

  private LocalDateTime dateTimeOf;

  private String city;

  private String description;

  private String[] images;
}
