/*
 *  *************************************************************************|
 *   Copyright (c) 2016. Yaana Technologies. All rights reserved.           *|
 *   Yaana Technologies PROPRIETARY/CONFIDENTIAL                            *|
 *  *************************************************************************|
 */
package com.harshit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class AutowireBean implements ApplicationContextAware {
  private static final Logger LOGGER = LoggerFactory.getLogger(AutowireBean.class);

  private static final AutowireBean INSTANCE = new AutowireBean();
  private static ApplicationContext applicationContext;


  /**
   * Tries to autowire the specified instance of the class if one of the specified beans which need to be autowired
   * are null.
   *
   * @param classToAutowire the instance of the class which holds @Autowire annotations
   * @param beansToAutowireInClass the beans which have the @Autowire annotation in the specified {#classToAutowire}
   */
  public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
    for (Object bean : beansToAutowireInClass) {
      if (bean == null) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
        return;
      }
    }
  }

  @Override
  public void setApplicationContext(final ApplicationContext applicationContext) {
    LOGGER.info("Application context aware executed at rms data repository");
    AutowireBean.applicationContext = applicationContext;
  }

  /**
   * @return the singleton instance.
   */
  public static AutowireBean getInstance() {
    return INSTANCE;
  }
}
