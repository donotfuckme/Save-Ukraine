package global.saveukraine.volunteer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;

@Controller
@RequestMapping("language")
public class LanguageController {

  @GetMapping
  public String changeLanguage(@RequestParam String lang,
                               HttpServletRequest request) {
    return format("redirect:%s?lang=%s", request, lang);
  }
}
