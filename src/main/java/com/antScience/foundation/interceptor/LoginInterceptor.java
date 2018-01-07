//package cn.rayest.foundation.interceptor;
//
//import cn.rayest.foundation.common.TokenService;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by lirui on 2017/4/27.
// */
////@Component
//public class LoginInterceptor extends HandlerInterceptorAdapter {
//
//    private static final String PERMITTED_PATH = "/auth/";
//    private static final String AUTHORIZATION = "authorization";
//    private static final String USER_NAME = "userName";
//
//    @Resource
//    private TokenService tokenService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getServletPath();
//        if (!url.contains(PERMITTED_PATH)) {
//            System.out.println(url);
//            String token = request.getHeader(AUTHORIZATION);
//            String userName = request.getHeader(USER_NAME);
//            String newToken = tokenService.identify(token, userName);
//            response.setHeader(AUTHORIZATION, newToken);
//        }
//        return true;
//    }
//}
