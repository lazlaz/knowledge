package com.laz.knowledge.fourteen;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RobotScreenTest {
	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			JFrame jframe = new JFrame();
			// ���ñ���
			jframe.setTitle("wangtianze�����Ļ����");
			JLabel label = new JLabel();
			jframe.add(label);
			jframe.setSize(800, 600);
			// ���ÿɼ�
			jframe.setVisible(true);
			// �����ö�
			jframe.setAlwaysOnTop(true);
			// ����̨�˳�ģʽ
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// ��ȡ��Ļ��С
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension dm = toolkit.getScreenSize();
			while (true) {
				// һ���������
				Rectangle rec = new Rectangle(0, 0, (int) dm.getWidth(), (int) dm.getHeight());
				// ���վ��ν�ȡͼƬ��������
				BufferedImage img = robot.createScreenCapture(rec);
				// ����ͼƬ
				BufferedImage newImg = RobotScreenTest.resize(img, jframe.getWidth(), jframe.getHeight());
				label.setIcon(new ImageIcon(newImg));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		int w = img.getWidth();
		int h = img.getHeight();
		// ����һ�����ź��ͼƬ��
		BufferedImage newImg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = newImg.createGraphics();
		// ����ģʽ
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		// ����������
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return newImg;
	}
}
