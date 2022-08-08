package com.swi.remotecontrol;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liangzi on 2016/3/17.
 */
public class ZOThreadManager {
  //线程池，存放一直在工作的线程
  private ThreadPoolExecutor mThreadService;

  public ZOThreadManager() {
    mThreadService = newWorkStealingPool();
  }

  public static ZOThreadManager getInstance() {
    return ThreadManagerHolder.INSTANCE;
  }

  /**
   * 创建线程池，存放一直在工作的线程
   */
  private ThreadPoolExecutor newWorkStealingPool() {
    return new ThreadPoolExecutor(10, 10,
      0L, TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<Runnable>());
  }

  /**
   * 添加线程到线程池，一直在工作的线程
   */
  public void addWorkStealingPool(Runnable run) {
    if (mThreadService == null) {
      mThreadService = newWorkStealingPool();
    }
    mThreadService.execute(run);
  }

  /**
   * 添加线程到线程池，一直在工作的线程
   */
  public void removeWorkStealingPool(Runnable run) {
    if (mThreadService != null) {
      mThreadService.remove(run);
    }
  }

  private static class ThreadManagerHolder {
    private static final ZOThreadManager INSTANCE = new ZOThreadManager();
  }
}
