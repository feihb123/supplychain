package xzit.supplychain.configration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xzit.supplychain.mapper.UserMapper;
import xzit.supplychain.pojo.User;
import xzit.supplychain.service.InterceptorService;
import xzit.supplychain.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    InterceptorService interceptorService;

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //预处理，为完成请求之前进行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //处理请求完成即Controller完成，视图渲染之前进行
        String url = httpServletRequest.getRequestURL().toString();

        /*HttpSession session=httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        int id = Integer.parseInt(session.getAttribute("id").toString());
        user.setId(id);
        if(interceptorService.intercept(user) != 1){
            System.out.println(interceptorService.intercept(user));
            httpServletResponse.sendRedirect("/");
        }*/

        HttpSession session=httpServletRequest.getSession();
        if(session.getAttribute("username")==null)
        {
            httpServletResponse.sendRedirect("/");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //视图渲染完成之后进行

    }

}