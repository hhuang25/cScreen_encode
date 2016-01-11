package com.cScreen.encode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Encoder {
	public BufferedImage getImageFromPath(String path) throws IOException
	{
		File image = new File(path);
		return ImageIO.read(image);
	}
	
	public byte[] convertBufferedImageToBytes(BufferedImage image) throws IOException
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos );
		return baos.toByteArray();
	}
}
