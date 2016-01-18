package com.cScreen.encode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Encoder {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public BufferedImage getImageFromPath(String path) throws IOException
	{
		File image = new File(path);
		return ImageIO.read(image);
	}
	
	public byte[] convertBufferedImageToBytes(BufferedImage image) throws IOException
	{
		return convertBufferedImageToBytes(image, "jpg");
	}
	
	public byte[] convertBufferedImageToBytes(BufferedImage image, String filetype) throws IOException
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(image, filetype, baos);
		return baos.toByteArray();
	}
	
	public BufferedImage convertBytesToImage(byte[] bytes) throws IOException
	{
		InputStream in = new ByteArrayInputStream(bytes);
		return ImageIO.read(in);
	}
	
	public void writeBufferedImageToFile(BufferedImage image, String path) throws IOException
	{
		writeBufferedImageToFile(image, path, "jpg");
	}
	
	public void writeBufferedImageToFile(BufferedImage image, String path, String filetype) throws IOException
	{
		File file = new File(path);
		ImageIO.write(image, filetype, file);
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public BufferedImage getImageDifference(BufferedImage img1, BufferedImage img2)
	{
		int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
	    int width2 = img2.getWidth(); // take no arguments
	    int height1 = img1.getHeight();
	    int height2 = img2.getHeight();
	    if ((width1 != width2) || (height1 != height2)) {
	        System.err.println("Error: Images dimensions mismatch");
	        System.exit(1);
	    }

	    // NEW - Create output Buffered image of type RGB
	    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

	    // Modified - Changed to int as pixels are ints
	    int diff;
	    
	    long startTime = System.currentTimeMillis();
	    
	    int result; // Stores output pixel
	    for (int i = 0; i < height1; i++) {
	        for (int j = 0; j < width1; j++) {
	            int rgb1 = img1.getRGB(j, i);
	            int rgb2 = img2.getRGB(j, i);
	            if(rgb1 == rgb2)
	            {
	            	// i.e. nothing changed
	            	result = 0;
	            }else{
	            	int r2 = (rgb2 >> 16) & 0xff;
		            int g2 = (rgb2 >> 8) & 0xff;
		            int b2 = (rgb2) & 0xff;
		            result = (r2 << 16) | (g2 << 8) | b2;
	            }
//	            int r1 = (rgb1 >> 16) & 0xff;
//	            int g1 = (rgb1 >> 8) & 0xff;
//	            int b1 = (rgb1) & 0xff;
//	            int r2 = (rgb2 >> 16) & 0xff;
//	            int g2 = (rgb2 >> 8) & 0xff;
//	            int b2 = (rgb2) & 0xff;
//	            diff = (r2 - r1); // Change
//	            result = 0x00000000 | (diff << 16);
//	            diff = (g2 - g1);
//	            result = result | (0x00000000 | (diff << 8));
//	            diff = (b2 - b1);
//	            result = result | (0x00000000 | (diff));
	            //diff /= 3; // Change - Ensure result is between 0 - 255
	            // Make the difference image gray scale
	            // The RGB components are all the same
	            //result = (diff << 16) | (diff << 8) | diff;
	            outImg.setRGB(j, i, result); // Set result
	        }
	    }
	    
	    long stopTime = System.currentTimeMillis();
		long elapsed = stopTime - startTime;
		System.out.println(String.format("Time spent in loop: %d milliseconds", elapsed));
		
	    return outImg;
	}
	
	public BufferedImage getImageDifferenceFast(BufferedImage img1, BufferedImage img2)
	{
		int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
	    int width2 = img2.getWidth(); // take no arguments
	    int height1 = img1.getHeight();
	    int height2 = img2.getHeight();
	    if ((width1 != width2) || (height1 != height2)) {
	        System.err.println("Error: Images dimensions mismatch");
	        System.exit(1);
	    }

	    // NEW - Create output Buffered image of type RGB
	    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
	    int diff;
	    
	    long startTime = System.currentTimeMillis();
	    
	    int result[] = new int[width1]; // Stores output pixel
	    for (int i = 0; i < height1; i++) {
	    	int[] rgb1 = img1.getRGB(0, i, width1, 1, null, 0, width1);
	    	int[] rgb2 = img2.getRGB(0, i, width2, 1, null, 0, width2);
	    	// no changes initially
	    	result = new int[width1];
	    	for(int j = 0; j < width1; j++){
				if(rgb1[j] != rgb2[j]){
    				int r2 = (rgb2[j] >> 16) & 0xff;
		            int g2 = (rgb2[j] >> 8) & 0xff;
		            int b2 = (rgb2[j]) & 0xff;
		            result[j] = (r2 << 16) | (g2 << 8) | b2;
    			}
    		}
	    	outImg.setRGB(0, i, width1, 1, result, 0, width1);
	    }
	    long stopTime = System.currentTimeMillis();
		long elapsed = stopTime - startTime;
		System.out.println(String.format("Time spent in loop: %d milliseconds", elapsed));
		
	    return outImg;
	}
}
