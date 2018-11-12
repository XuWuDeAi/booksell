package controller;


import entity.JsonRespBean;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unit.MainUnit;
import unit.Redis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(value = "/load", produces = "text/plain; charset=utf-8")
    public String load(String password, String rand, HttpServletRequest request) {
        try {
            if (!password.equals("www388598"))
                throw new Exception("密码错误");
            HttpSession session = request.getSession(true);
            String loactRand;
            loactRand = MainUnit.getAttribute(session, "rand");
            session.setAttribute("rand", "");
            MainUnit.print(loactRand);
            MainUnit.print(rand);
            if (!loactRand.equalsIgnoreCase(rand))
                throw new Exception("验证码输入错误");
            session.setAttribute("user", "admin");
            return JsonRespBean.success();
        } catch (Exception e) {
            return JsonRespBean.erro(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/setAnnouncement", produces = "text/plain; charset=utf-8")
    public String setAnnouncement(String context, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(true);
            String user = MainUnit.getAttribute(session, "user");
            if(!user.equals("admin"))
                throw new Exception("你没有权限");
            Redis.setValue("announcement", context);
            return JsonRespBean.success();
        } catch (Exception e) {
            return JsonRespBean.erro(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getAnnouncement", produces = "text/plain; charset=utf-8")
    public String getAnnouncement() {
        try {
            return JsonRespBean.success(Redis.getValue("announcement"));
        } catch (Exception e) {
            return JsonRespBean.erro(e);
        }
    }


}
