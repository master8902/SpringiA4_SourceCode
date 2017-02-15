package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //声明为一个控制器
@RequestMapping("/") //处理对"/"的GET请求
public class HomeController {

  @RequestMapping(method = GET)
  public String home(Model model) {
    return "home"; //DispatcherServlet会要求视图解析器将这个逻辑名称解析为实际的视图.
                     //鉴于我们配置InternalResourceViewResolver的方式， 视图名“home”将会解析
                    // 为“WEB-INFviews/home.jsp”路径的JSP。
  }

}
