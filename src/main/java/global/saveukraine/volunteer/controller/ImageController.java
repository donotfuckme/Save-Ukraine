package global.saveukraine.volunteer.controller;

import global.saveukraine.volunteer.service.S3ImageStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@Controller
@RequestMapping("storage")
@AllArgsConstructor
public class ImageController {

  private final S3ImageStorage imageStorage;

  @ResponseBody
  @GetMapping("{imageName}")
  public byte[] getImage(@PathVariable(value = "imageName") String imageName,
                         HttpServletResponse response) {
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    return imageStorage.get(imageName);
  }
}
