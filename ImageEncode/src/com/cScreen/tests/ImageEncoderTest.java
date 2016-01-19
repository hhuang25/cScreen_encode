package com.cScreen.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cScreen.encode.Encoder;

public class ImageEncoderTest {
	Encoder encoder;
	String resFolder;
	
	@Before
	public void setUp() throws Exception {
		encoder = new Encoder();
		resFolder = System.getProperty("user.dir") + File.separator + "res" + File.separator;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void convertBufferedImageToBytes() {
		try{
			String path = resFolder + "img1.jpg";
			System.out.println(path);
			BufferedImage img = encoder.getImageFromPath(path);	
			byte[] bytes = encoder.convertBufferedImageToBytes(img);
			System.out.println(Arrays.toString(bytes));
			System.out.println(Encoder.bytesToHex(bytes));
		}
		catch(Exception ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testGetImageDifference() {
		try{
			String path = resFolder;
			BufferedImage img1 = encoder.getImageFromPath(path + "img1.jpg");	
			BufferedImage img2 = encoder.getImageFromPath(path + "img2.jpg");
			//TODO: rest of the test
			long startTime = System.currentTimeMillis();
			BufferedImage diff = encoder.getImageDifference(img1, img2);
			long stopTime = System.currentTimeMillis();
			long elapsed = stopTime - startTime;
			System.out.println(String.format("Time elapsed: %d milliseconds", elapsed));
			encoder.writeBufferedImageToFile(diff, path + "out12.jpg");
		}
		catch(Exception ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);
	}

	@Test
	public void testGetBMPDifference() {
		try{
			String path = resFolder;
			BufferedImage img1 = encoder.getImageFromPath(path + "type1.bmp");
			BufferedImage img2 = encoder.getImageFromPath(path + "type2.bmp");
			//TODO: rest of the test
			long startTime = System.currentTimeMillis();
			BufferedImage diff = encoder.getImageDifference(img1, img2);
			long stopTime = System.currentTimeMillis();
			long elapsed = stopTime - startTime;
			System.out.println(String.format("Time elapsed: %d milliseconds", elapsed));
			encoder.writeBufferedImageToFile(diff, path + "typeout12.bmp", "bmp");
		}
		catch(Exception ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testGetBMPDifferenceFast() {
		try{
			String path = resFolder;
			BufferedImage img1 = encoder.getImageFromPath(path + "type1.bmp");
			BufferedImage img2 = encoder.getImageFromPath(path + "type2.bmp");
			//TODO: rest of the test
			long startTime = System.currentTimeMillis();
			BufferedImage diff = encoder.getImageDifferenceFast(img1, img2);
			long stopTime = System.currentTimeMillis();
			long elapsed = stopTime - startTime;
			System.out.println(String.format("Time elapsed: %d milliseconds", elapsed));
			encoder.writeBufferedImageToFile(diff, path + "typeout12.bmp", "bmp");
		}
		catch(Exception ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);	
	}
	
	@Test
	public void testGetBytesDifference() {
		try{
			BufferedImage img1 = encoder.getImageFromPath(resFolder + "type1.bmp");
			byte[] bytes1 = encoder.convertBufferedImageToBytes(img1, "bmp");
			BufferedImage img2 = encoder.getImageFromPath(resFolder + "type2.bmp");
			byte[] bytes2 = encoder.convertBufferedImageToBytes(img2, "bmp");
			long startTime = System.currentTimeMillis();
			byte[] diff = encoder.getByteDifference(bytes1, bytes2);
			long stopTime = System.currentTimeMillis();
			long elapsed = stopTime - startTime;
			System.out.println(String.format("Time elapsed: %d milliseconds", elapsed));
			encoder.writeBufferedImageToFile(encoder.convertBytesToImage(diff), resFolder + "typeout12.bmp", "bmp");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);		
	}
}
