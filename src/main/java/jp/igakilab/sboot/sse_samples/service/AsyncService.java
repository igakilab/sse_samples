package jp.igakilab.sboot.sse_samples.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncService {
  private final Logger logger = LoggerFactory.getLogger(AsyncService.class);

  @Async
  public void pushDateTime(SseEmitter sseEmitter) {
    int counter = 0;
    // 5回日時を送付したらループが終了する
    try {
      while (counter < 5) {
        TimeUnit.SECONDS.sleep(1);
        sseEmitter.send(LocalDateTime.now().toString());
        counter++;
      }

    } catch (IOException e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } catch (InterruptedException e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      sseEmitter.complete();
    }
  }

}
