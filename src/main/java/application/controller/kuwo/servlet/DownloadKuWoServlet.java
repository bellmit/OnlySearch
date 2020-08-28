package application.controller.kuwo.servlet;

import application.service.kuwo.KuwoService;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wtl
 * @Date: 2020/7/5
 * @Time: 18:48
 * @Description:
 */
@WebServlet(name = "DownloadKuWoServlet",urlPatterns = "/kuwo/getVideoStream")
public class DownloadKuWoServlet extends HttpServlet {

    @Resource
    private KuwoService kuwoService;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String rid = httpServletRequest.getParameter("rid");
        kuwoService.getVideoStream(rid, httpServletRequest, httpServletResponse);
    }
}
