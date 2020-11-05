package jp.igakilab.sboot.sse_samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SseSamplesApplication {
  public static void main(String[] args) {
    SpringApplication.run(SseSamplesApplication.class, args);
  }

}
