package global.saveukraine.volunteer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Log4j2
@Service
@RequiredArgsConstructor
public class S3ImageStorage {

  private final AmazonS3 s3Client;

  @Value("${cloud.aws.bucket}")
  private String bucketName;

  public String save(MultipartFile multipartFile) {
    File file = convertMultiPartFileToFile(multipartFile);
    String fileName = UUID.randomUUID() + "-" + System.currentTimeMillis();
    s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    try {
      file.delete();
    } catch (SecurityException e) {
      log.warn("Cannot delete file", e);
    }
    return fileName;
  }

  public byte[] get(String fileName) {
    S3Object s3Object = s3Client.getObject(bucketName, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();
    try {
      return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      log.error("Cannot get byte array of file", e);
    }
    return new byte[0];
  }

  public void remove(String fileName) {
    s3Client.deleteObject(bucketName, fileName);
  }

  private File convertMultiPartFileToFile(MultipartFile file) {
    String originalFilename = requireNonNull(file.getOriginalFilename(), "Original file name is empty");
    File convertedFile = new File(originalFilename);
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.error("Error converting multipartFile to file", e);
    }
    return convertedFile;
  }
}
