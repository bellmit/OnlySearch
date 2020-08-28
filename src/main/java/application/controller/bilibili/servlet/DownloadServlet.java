package application.controller.bilibili.servlet;

import application.service.bilibili.BiliBiliIndexService;
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
@WebServlet(name = "DownloadServlet",urlPatterns = "/bilibili/getVideoStream")
public class DownloadServlet extends HttpServlet {

    @Resource
    private BiliBiliIndexService biliBiliIndexService;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String cid = httpServletRequest.getParameter("cid");
        String bVid = httpServletRequest.getParameter("bVid");
        biliBiliIndexService.getVideoStream(cid,bVid,httpServletRequest,httpServletResponse);
    }
}
