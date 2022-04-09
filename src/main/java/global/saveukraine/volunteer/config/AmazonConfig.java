package global.saveukraine.volunteer.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

  @Bean
  public AmazonS3 s3Client(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                           @Value("${cloud.aws.credentials.secret-key}") String secretKey,
                           @Value("${cloud.aws.region.static}") String region) {
    AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    return AmazonS3ClientBuilder
      .standard()
      .withRegion(region)
      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
      .build();
  }
}
