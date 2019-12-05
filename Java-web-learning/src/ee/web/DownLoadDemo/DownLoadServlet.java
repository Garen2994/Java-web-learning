package ee.web.DownLoadDemo;

import ee.web.Utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Title : 文件下载需求：
 * 1. 页面显示超链接
 * 2. 点击超链接后弹出下载提示框
 * 3. 完成图片文件下载
 * @Author : Garen
 * @Date : 2019/12/4 14:07
 */
@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1.获取参数-文件名
        String filename = request.getParameter("filename");

        //2.使用字节输入流加载文件进内存
        //2.1 获取真实路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/image/" + filename);

        //2.2 关联输入流
        FileInputStream fis = new FileInputStream(realPath);
        //3.将输入流中的内容写入到输出流当中
        //3.1 设置响应头
        String mimeType = context.getMimeType(filename);
        response.setHeader("content-type", mimeType);
        //3.2 解决中文文件名问题
        String agent = request.getHeader("user-agent");
        String name = DownLoadUtils.getFileName(agent, filename);
        //3.4 name为弹出的提示框的名(重新编码后的)
        response.setHeader("content-disposition", "attachment;filename=" + name);
        //3.5 写到字节输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            sos.write(buff, 0, len);
        }
        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
