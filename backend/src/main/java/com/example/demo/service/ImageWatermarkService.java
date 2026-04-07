package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageWatermarkService {

    @Value("${file.dir}")
    private String fileDir;

    public void addTextWatermark(String storedFileName, String watermarkText) throws IOException {
        File imageFile = new File(fileDir + storedFileName);
        BufferedImage image = ImageIO.read(imageFile);

        if (image == null) {
            throw new IllegalArgumentException("이미지 파일을 읽을 수 없습니다.");
        }

        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g2d.setColor(Color.WHITE);

        int fontSize = Math.max(20, image.getWidth() / 18);
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int x = image.getWidth() - fontMetrics.stringWidth(watermarkText) - 20;
        int y = image.getHeight() - fontMetrics.getHeight();

        g2d.drawString(watermarkText, x, y);
        g2d.dispose();

        String formatName = getFormatName(storedFileName);
        ImageIO.write(image, formatName, imageFile);
    }

    public void addImageWatermark(String storedFileName, String watermarkImagePath) throws IOException {
        File sourceFile = new File(fileDir + storedFileName);
        BufferedImage sourceImage = ImageIO.read(sourceFile);

        if (sourceImage == null) {
            throw new IllegalArgumentException("원본 이미지를 읽을 수 없습니다.");
        }
        BufferedImage watermarkImage = ImageIO.read(new File(watermarkImagePath));

        if (watermarkImage == null) {
            throw new IllegalArgumentException("워터마크 이미지를 읽을 수 없습니다.");
        }

        Graphics2D g2d = sourceImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 워터마크 크기 조절
        int watermarkWidth = sourceImage.getWidth() / 5;
        int watermarkHeight = watermarkImage.getHeight() * watermarkWidth / watermarkImage.getWidth();

        Image scaleWatermark = watermarkImage.getScaledInstance(
                watermarkWidth,
                watermarkHeight,
                Image.SCALE_SMOOTH
        );

        // 투명도
        AlphaComposite alphaComposite =
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        g2d.setComposite(alphaComposite);

        // 오른쪽 아래 배치
        int x = sourceImage.getWidth() - watermarkWidth - 20;
        int y = sourceImage.getHeight() - watermarkHeight - 20;

        g2d.drawImage(scaleWatermark, x, y, null);
        g2d.dispose();

        String formatName = getFormatName(storedFileName);
        ImageIO.write(sourceImage, formatName, sourceFile);
    }

    private String getFormatName(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        if (ext.equals("jpg")) {
            return "jpeg";
        }
        return ext;
    }
}
