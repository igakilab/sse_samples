package jp.igakilab.sboot.sse_samples.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jp.igakilab.sboot.sse_samples.service.AsyncService;

@Controller
@RequestMapping("/sample5a")
public class Sample5aController {

  private ArrayList<SseEmitter> emittersList = new ArrayList<>();
  private final Logger logger = LoggerFactory.getLogger(Sample5aController.class);

  @Autowired
  AsyncService as;

  /**
   * 非同期にサーバからそのときの日時を送信する
   *
   * @return
   */
  @GetMapping("step1")
  public SseEmitter asyncDateTime() {
    logger.info("asyncDateTime Start");
    // 接続された数だけSseEmitterを作成してリストに追加する
    final SseEmitter sseEmitter = new SseEmitter();
    emittersList.add(sseEmitter);

    this.emittersList.forEach((SseEmitter emitter) -> {
      as.pushDateTime(emitter);
    });
    // 終了したSseEmitterをリストから削除する
    sseEmitter.onCompletion(() -> {
      emittersList.remove(sseEmitter);
      logger.info("sseEmitter.remove");
    });
    sseEmitter.onTimeout(() -> {
      sseEmitter.complete();
    });

    return sseEmitter;
  }
}
