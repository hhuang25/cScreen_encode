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
		}
		catch(Exception ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertTrue(true);
	}
}
