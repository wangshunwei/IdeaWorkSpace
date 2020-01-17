package cn.itcast.rabbit.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 自定义对象工厂
 *
 */
public class MultiRabbitTemplateFactory implements BeanFactoryAware,ApplicationContextAware,InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(MultiRabbitTemplateFactory.class);
    private DefaultListableBeanFactory factory;
    private static ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {

        // 可以处理其他的业务，比如进行重试
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MultiRabbitTemplateFactory.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = (DefaultListableBeanFactory) beanFactory;
    }

    public static <T> T getBeanByName(String name) {
        try {
            if (applicationContext.containsBean(name)) {
                return (T) applicationContext.getBean(name);
            }
        } catch (BeansException e) {
            logger.error("not found bean name spring:" + name);
        }
        return null;
    }

    public static <T> T getBeanByType(Class<T> classType) {
        return applicationContext.getBean(classType);
    }
}
