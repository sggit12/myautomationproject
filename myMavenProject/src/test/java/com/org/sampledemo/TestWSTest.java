package com.org.sampledemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestWSTest {
	
	@Test
	public void Loading() {
		
		System.out.println("Loading the site Webstaurant Store");
		
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\sunil\\Downloads\\chrome107\\chromedriver.exe");
	    WebDriver driver=new ChromeDriver();
	    
		driver.manage().window().maximize(); //maximize the browser
		
		driver.get("http://webstaurant.com"); //load the page
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.findElement(By.id("searchval")).sendKeys("stainless work table" + Keys.ENTER); //search and click
		
		String lastpage = driver.findElement(By.xpath("//*[@id=\"paging\"]/nav/ul/li[7]/a")).getText(); //finding the last page 
		int lastpageint = Integer.valueOf(lastpage);
		for(int j=0; j<lastpageint; j++){
		List<WebElement> item = driver.findElements(By.xpath("//*[@id=\"details\"]/a")); // storing item list
        WebElement q;
			for(int i=0;i<item.size();i++) {
        	q = item.get(i);
           	System.out.println("value = " + q.getText());
        	if ((!q.getText().contains("Table")) && (!q.getText().trim().contains(""))) {  //searching for table
        			System.out.println("Table is not present");
        	}
    		System.out.println("j= " + j);
    		System.out.println("i= " + i);
    		//Check for the last page and the last item
	        	if ((j+1==lastpageint) && (i+1==item.size())) {
	        		System.out.println("last page and last item");
	        		System.out.println("adding last item to the cart: " + q.getText());
	        		driver.findElement(By.xpath("//*[@id=\"ProductBoxContainer\"]/div[4]/form/div/div/input[2]")).click(); //add to cart
	        		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	        		driver.findElement(By.xpath("//*[@id=\"watnotif-wrapper\"]/div/p/div[2]/div[2]/a[1]")).click(); //click on popup view cart
	        		driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div/button")).click(); //empty cart
	        		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	        		driver.findElement(By.xpath("/html[1]/body[1]/div[11]/div[1]/div[1]/div[1]/footer[1]/button[1]")).click(); //empty cart click
	        		driver.findElement(By.xpath("//*[contains(text(),'Your cart is empty.')]"));  //verification of empty cart
	        		driver.close() ; //close browser
	    		}
			}
		}
	}
}
