package ee.demo.Web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/RandomCodeServlet")

public class RandomCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int width = 200;
        int height = 80;
        //创建图片流对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //绘制图像
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, width, height);
        //
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, width - 1, height - 1);



        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        graphics.setColor(Color.BLACK);
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int i1 = ran.nextInt(61);
            char c = str.charAt(i1);
            sb.append(c);
            graphics.drawString(c + " ", width / 4 * i, height / 2);
        }
        String checkCode = sb.toString();
        System.out.println(checkCode);
        request.getSession().setAttribute("checkCode_Session",checkCode);

        graphics.setColor(Color.RED);
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            graphics.drawLine(x1,x2,y1,y2);
        }
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        this.doPost(request, response);
    }
}
