package global.saveukraine.volunteer.service;

import global.saveukraine.volunteer.model.VolunteerUserOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  @Value("${mail.subject}")
  private String mailSubject;

  @Value("${mail.text-placeholder}")
  private String mailTextPlaceholder;

  public void sendMessage(VolunteerUserOrder volunteerUserOrder) {
    log.debug("sender = '{}', mail subject = '{}'", sender, mailSubject);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(sender);
    message.setTo("antoxashow@gmail.com");
    message.setSubject(mailSubject);
    String gender = isEmpty(volunteerUserOrder.getMale()) ? "Мужчина" : isEmpty(volunteerUserOrder.getFemale()) ? "Женщина" : "Не указано";
    String formattedText = format(mailTextPlaceholder, volunteerUserOrder.getFullName(), gender, volunteerUserOrder.getCity(), volunteerUserOrder.getPhone());
    message.setText(formattedText);
    javaMailSender.send(message);
  }
}
