package global.saveukraine.volunteer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class VolunteerUserOrderDto {

  private final String fullName;

  private final String male;

  private final String female;

  private final String city;

  private final String phone;
}
