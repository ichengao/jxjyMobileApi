package cn.gc80.base.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ValideCodeUtil {
	private int width = 60;
	private int heigth = 20;
	private static Random ra = new Random();

	@RequestMapping("/test.do")
	public ModelAndView doTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		System.out.println("开始验证码");

		BufferedImage buffer = new BufferedImage(width, heigth,
				BufferedImage.TYPE_INT_BGR);
		Graphics2D graphice = buffer.createGraphics();
		// graphice.setColor(getRandColor(200, 250));
		graphice.fillRect(0, 0, width, heigth);

		/*
		 * for(int i=0;i<160;i++){ int x=ra.nextInt(width); int
		 * y=ra.nextInt(heigth); int x1=ra.nextInt(20); int y1=ra.nextInt(20);
		 * graphice.setColor(getRandColor(200, 250)); graphice.drawLine(x, y,
		 * x1, y1); }
		 */

		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String str = String.valueOf(this.getRandomChar1());
			graphice.setColor(new Color(20 + ra.nextInt(110), 20 + ra
					.nextInt(110), 20 + ra.nextInt(110)));
			graphice.setFont(new Font("Consolas", Font.PLAIN, 18));
			// int radian = ra.nextInt(61) - 40;
			// graphice.rotate(radian*Math.PI/180, (i+1)*10, 8);
			graphice.drawString(str, 13 * i + 6, 16);
			// graphice.rotate(-radian*Math.PI/180, (i+1)*10, 8);
			graphice.drawLine((i + 1) * 10, ra.nextInt(heigth),
					ra.nextInt(width), ra.nextInt(heigth));
			strBuffer.append(str);
		}

		HttpSession session = request.getSession();
		session.setAttribute("valideCode", strBuffer.toString());
		response.setContentType("image/*");
		response.addHeader("Cache-Control", "no-cache");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(buffer, "GIF", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		System.out.println("验证码显示完毕");
		return null;
	}

	//生成验证码
	@RequestMapping("/getValideCode.html")
	public void getValideCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uuid=request.getParameter("uuid");
		BufferedImage buffer = new BufferedImage(width, heigth,BufferedImage.TYPE_INT_BGR);
		Graphics2D graphice = buffer.createGraphics();
		// graphice.setColor(getRandColor(200, 250));
		graphice.fillRect(0, 0, width, heigth);

		/*
		 * for(int i=0;i<160;i++){ int x=ra.nextInt(width); int
		 * y=ra.nextInt(heigth); int x1=ra.nextInt(20); int y1=ra.nextInt(20);
		 * graphice.setColor(getRandColor(200, 250)); graphice.drawLine(x, y,
		 * x1, y1); }
		 */

		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String str = String.valueOf(this.getRandomChar1());
			graphice.setColor(new Color(20 + ra.nextInt(110), 20 + ra
					.nextInt(110), 20 + ra.nextInt(110)));
			graphice.setFont(new Font("Consolas", Font.PLAIN, 18));
			// int radian = ra.nextInt(61) - 40;
			// graphice.rotate(radian*Math.PI/180, (i+1)*10, 8);
			graphice.drawString(str, 13 * i + 6, 16);
			// graphice.rotate(-radian*Math.PI/180, (i+1)*10, 8);
			graphice.drawLine((i + 1) * 10, ra.nextInt(heigth),
					ra.nextInt(width), ra.nextInt(heigth));
			strBuffer.append(str);
		}
		//保存验证码到memCached中
		if(uuid!=null&&!"".equals(uuid)){
			MemCached.getInstance().addOrReplace(uuid,strBuffer.toString());
		}else{
			MemCached.getInstance().addOrReplace("valideCode",strBuffer.toString());
		}
		response.setContentType("image/*");
		response.addHeader("Cache-Control", "no-cache");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(buffer, "GIF", out);
		out.close();
	}

	public char getRandomChar() {
		return (char) (65 + ra.nextInt(26));
	}

	public int getRandom() {
		return ra.nextInt(10);
	}

	public char getRandomChar1() {
		String ValidateStr = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		return ValidateStr.charAt(ra.nextInt(ValidateStr.length()));
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
