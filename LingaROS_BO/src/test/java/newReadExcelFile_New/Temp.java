package newReadExcelFile_New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Temp {
	
	
	ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");

	int i;
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("SaleUsingExcelFile");
	
	@SuppressWarnings("unlikely-arg-type")
	public void test()
	{

		
		
		if(excel.getData(0, i, 3).equals("Not Available") && excel.getData(0, i, 5).equals("Not Available") && !excel.getData(0, i, 20).equals("Not Available"))
		{
			System.out.println("There is only Mandatory Modifier is available");
			
			test.log(LogStatus.INFO, "There is only Mandatory Modifier is available");
			
			String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
			
			mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
			
			int modCount = Integer.parseInt(mandatoryModifierCount);
			
			//Get the maximum modifier count
			System.out.println("Maximum modifier Count is : "+modCount);
			
			
			
			//Click the mandatory modifier group
			driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 20)+"']")).click();
			
			
			if(excel.getData(0, i, 21).equals("Not Available"))
			{}else{
			//Click the modifier
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
			}
			
			if(excel.getData(0, i, 22).equals("Not Available"))
			{}else{
			//Click the prefix
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
			}
			
			//Check whether the minimum modifier count is reached or not							
			if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
			{
				/*//Click the done button
				driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
				
				if(excel.getData(0, i, 31).equals("Not Available"))
				{
					System.out.println("There is no more Mandatory Modifiers are not available");
					
					test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
				
					
				}
				
				else
				{
					String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
					
					int modCount1 = Integer.parseInt(mandatoryModifierCount1);
					
					//Get the maximum modifier count
					System.out.println("Maximum modifier Count is : "+modCount1);

					
					if(excel.getData(0, i, 32).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
					}
					
					if(excel.getData(0, i, 33).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
					}
					
					if(excel.getData(0, i, 34).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
					}
												
					if(excel.getData(0, i, 35).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
					}

					if(excel.getData(0, i, 36).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
					}
					
					if(excel.getData(0, i, 37).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
					}

					if(excel.getData(0, i, 38).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
					}
					
					if(excel.getData(0, i, 39).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
					}

					if(excel.getData(0, i, 40).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
					}
					
					if(excel.getData(0, i, 41).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
					}
				}
				
				
			}
			
			
			if(excel.getData(0, i, 23).equals("Not Available"))
			{}else{
			//Click the modifier
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
			}
										
			if(excel.getData(0, i, 24).equals("Not Available"))
			{}else{
			//Click the prefix
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
			}
			
			//Check whether the minimum modifier count is reached or not							
			if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
			{
				/*//Click the done button
				driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
				
				if(excel.getData(0, i, 31).equals("Not Available"))
				{
					System.out.println("There is no more Mandatory Modifiers are not available");
					
					test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
				
					
				}
				
				else
				{
					String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
					
					int modCount1 = Integer.parseInt(mandatoryModifierCount1);
					
					//Get the maximum modifier count
					System.out.println("Maximum modifier Count is : "+modCount1);

					
					if(excel.getData(0, i, 32).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
					}
					
					if(excel.getData(0, i, 33).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
					}
					
					if(excel.getData(0, i, 34).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
					}
												
					if(excel.getData(0, i, 35).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
					}

					if(excel.getData(0, i, 36).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
					}
					
					if(excel.getData(0, i, 37).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
					}

					if(excel.getData(0, i, 38).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
					}
					
					if(excel.getData(0, i, 39).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
					}

					if(excel.getData(0, i, 40).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
					}
					
					if(excel.getData(0, i, 41).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
					}
				}
				
				
			}
			
			
			if(excel.getData(0, i, 25).equals("Not Available"))
			{}else{
			//Click the modifier
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
			}
			
			if(excel.getData(0, i, 26).equals("Not Available"))
			{}else{
			//Click the prefix
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
			}
			
			//Check whether the minimum modifier count is reached or not							
			if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
			{
				/*//Click the done button
				driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
				
				if(excel.getData(0, i, 31).equals("Not Available"))
				{
					System.out.println("There is no more Mandatory Modifiers are not available");
					
					test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
				
					
				}
				
				else
				{
					String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
					
					int modCount1 = Integer.parseInt(mandatoryModifierCount1);
					
					//Get the maximum modifier count
					System.out.println("Maximum modifier Count is : "+modCount1);

					
					if(excel.getData(0, i, 32).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
					}
					
					if(excel.getData(0, i, 33).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
					}
					
					if(excel.getData(0, i, 34).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
					}
												
					if(excel.getData(0, i, 35).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
					}

					if(excel.getData(0, i, 36).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
					}
					
					if(excel.getData(0, i, 37).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
					}

					if(excel.getData(0, i, 38).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
					}
					
					if(excel.getData(0, i, 39).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
					}

					if(excel.getData(0, i, 40).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
					}
					
					if(excel.getData(0, i, 41).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
					}
				}
				
				
			}
			
			
			if(excel.getData(0, i, 27).equals("Not Available"))
			{}else{
			//Click the modifier
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
			}
			
			if(excel.getData(0, i, 28).equals("Not Available"))
			{}else{
			//Click the prefix
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
			}
			
			//Check whether the minimum modifier count is reached or not							
			if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
			{
				/*//Click the done button
				driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
				
				if(excel.getData(0, i, 31).equals("Not Available"))
				{
					System.out.println("There is no more Mandatory Modifiers are not available");
					
					test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
				
					
				}
				
				else
				{
					String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
					
					int modCount1 = Integer.parseInt(mandatoryModifierCount1);
					
					//Get the maximum modifier count
					System.out.println("Maximum modifier Count is : "+modCount1);

					
					if(excel.getData(0, i, 32).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
					}
					
					if(excel.getData(0, i, 33).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
					}
					
					if(excel.getData(0, i, 34).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
					}
												
					if(excel.getData(0, i, 35).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
					}

					if(excel.getData(0, i, 36).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
					}
					
					if(excel.getData(0, i, 37).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
					}

					if(excel.getData(0, i, 38).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
					}
					
					if(excel.getData(0, i, 39).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
					}

					if(excel.getData(0, i, 40).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
					}
					
					if(excel.getData(0, i, 41).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
					}
				}
				
				
			}
			
			
			if(excel.getData(0, i, 29).equals("Not Available"))
			{}else{
			//Click the modifier
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
			}
			
			if(excel.getData(0, i, 30).equals("Not Available"))
			{}else{
			//Click the prefix
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
			}

			//Check whether the minimum modifier count is reached or not							
			if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
			{
				/*//Click the done button
				driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
				
				if(excel.getData(0, i, 31).equals("Not Available"))
				{
					System.out.println("There is no more Mandatory Modifiers are not available");
					
					test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
				
					
				}
				
				else
				{
					String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
					
					int modCount1 = Integer.parseInt(mandatoryModifierCount1);
					
					//Get the maximum modifier count
					System.out.println("Maximum modifier Count is : "+modCount1);

					
					if(excel.getData(0, i, 32).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
					}
					
					if(excel.getData(0, i, 33).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
					}
					
					if(excel.getData(0, i, 34).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
					}
												
					if(excel.getData(0, i, 35).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
					}

					if(excel.getData(0, i, 36).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
					}
					
					if(excel.getData(0, i, 37).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
					}

					if(excel.getData(0, i, 38).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
					}
					
					if(excel.getData(0, i, 39).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
					}

					if(excel.getData(0, i, 40).equals("Not Available"))
					{}else{
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
					}
					
					if(excel.getData(0, i, 41).equals("Not Available"))
					{}else{
					//Click the prefix
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
					}
				}
				
				
			}
		}

	}
}
