/**
 * 1.配置DispatcherServlet
       DispatcherServlet是Spring MVC的核心。 在这里请求会第一次接触到框架， 它要负责将请求路由到其他的组件之中。
    2.扩展AbstractAnnotationConfigDispatcherServletInitializer的任意类都会自
        动地配置DispatcherServlet和Spring应用上下文，Spring的应用上下文会位于应用程序的Servlet上下文之中。
    3.在Servlet 3.0环境中， 容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类，如果能发现的
         话， 就会用它来配置Servlet容器。Spring提供了这个接口的实现， 名为SpringServletContainerInitializer， 这个类
         反过来又会查找实现WebApplicationInitializer的类并将配置的任务交给它们来完成。Spring 3.2引入了一个便利的
         WebApplicationInitializer基础实现， 也就是AbstractAnnotationConfigDispatcherServletInitializer。 因为我们的
         SpittrWebAppInitializer扩展了AbstractAnnotationConfigDispatcherServletInitializer（同时也就实现了
          WebApplicationInitializer） ， 因此当部署到Servlet 3.0容器中的时候， 容器会自动
         发现它， 并用它来配置Servlet上下文。
    4.两个应用上下文之间的故事:当DispatcherServlet启动的时候， 它会创建Spring应用上下文， 并加载配置文件或配置
         类中所声明的bean。 在程序清单的getServletConfigClasses()方法中， 我们要求DispatcherServlet加载应用上下文时，
         使用定义在WebConfig配置类（使用Java配置） 中的bean。但是在Spring Web应用中， 通常还会有另外一个应用上下文。
         另外的这个应用上下文是由ContextLoaderListener创建的。我们希望DispatcherServlet加载包含Web组件的bean， 如控制器、
         视图解析器以及处理器映射， 而ContextLoaderListener要加载应用中的其他bean。 这些bean通常是驱动应
         用后端的中间层和数据层组件。
          实际上， AbstractAnnotationConfigDispatcherServletInitializer会同时创建DispatcherServlet和ContextLoaderListener。
    5.它只能部署到支持Servlet 3.0的服务器中才能正常工作， 如Tomcat 7或更高版本。
 */

package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.web.WebConfig;

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
  @Override
  protected Class<?>[] getRootConfigClasses() { //指定配置类
                                                  //用来配置ContextLoaderListener创建的应用上下文中的bean。
    return new Class<?>[] { RootConfig.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() { //指定配置类 用来定
                                                    // 义DispatcherServlet应用上下文中的bean
    return new Class<?>[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() { //将 DispatcherServlet 映射到 "/"   在本例中， 它映射的是“/”， 这表示它会是应用的默认
    //Servlet。 它会处理进入应用的所有请求。
    return new String[] { "/" };
  }

}