package global.saveukraine.volunteer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@RequiredArgsConstructor
public class ReportEditDto {

  @NotNull
  private final Long id;

  @NotBlank
  private final String title;

  @NotBlank
  private final String description;
}
