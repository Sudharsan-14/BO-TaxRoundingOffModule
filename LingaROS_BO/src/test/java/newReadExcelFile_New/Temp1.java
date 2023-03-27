package newReadExcelFile_New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Temp1 {
	ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");

	int i;
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("SaleUsingExcelFile");
	public void test()
	{

		
		



		
		if(excel.getData(0, i, 1).equals("Not Available"))
		{
			System.out.println("There Is No Sub Category available under Category");
			
			test.log(LogStatus.INFO, "There Is No Sub Category available under Category");
			

			//Click the Menu Item
			driver.findElement(By.xpath("//p[.='"+excel.getData(0, i, 2)+"']")).click();
			
			if(excel.getData(0, i, 3).equals("Not Available") && excel.getData(0, i, 5).equals("Not Available") && excel.getData(0, i, 20).equals("Not Available"))
			{
				System.out.println("There Is No Serving Size and No Modifiers are available");
				
				test.log(LogStatus.INFO, "There Is No Serving Size and No Modifiers are available");
				
				
			}
			
			if(!excel.getData(0, i, 3).equals("Not Available"))
			{
				//Click the serving size
				driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 3)+"']")).click();
				
				//Click the serving size option
				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 4)+"']")).click();
				
				

					if(!excel.getData(0, i, 20).equals("Not Available"))
					{
						String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
						
						mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
								
						System.out.println("Minimum Modifier count is : "+mandatoryModifierCount);
						
						test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount);
						
						for(int l = 20; l == 30;l++)
						{
							//Click the mandatory modifier group
							driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l)+"']")).click();
							
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+1)+"']")).click();
							
							if(!excel.getData(0, i, l+2).equals("Not Available"))
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+2)+"']")).click();
							}
							else
							{
								
							}
							
							
							
							if(excel.getData(0, i, 75).equals(mandatoryModifierCount))
							{
							
								if(!excel.getData(0, i, 31).equals("Not Available"))
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									System.out.println("Minimum Modifier count is : "+mandatoryModifierCount1);
									
									test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount1);
									
									for(int l1 = 31; l1 == 41;l1++)
									{
										//Click the mandatory modifier group
										driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l1)+"']")).click();
										
										//Click the modifier
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+1)+"']")).click();
										
										if(!excel.getData(0, i, l1+2).equals("Not Available"))
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+2)+"']")).click();
										}
										else
										{
											
										}
										
										
										
										if(excel.getData(0, i, 76).equals(mandatoryModifierCount))
										{
										
											if(!excel.getData(0, i, 42).equals("Not Available"))
											{
												String mandatoryModifierCount2 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
												
												mandatoryModifierCount2 = mandatoryModifierCount2.replace(" ", "");
														
												System.out.println("Minimum Modifier count is : "+mandatoryModifierCount2);
												
												test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount2);
												
												for(int l2 = 42; l2 == 52;l2++)
												{
													//Click the mandatory modifier group
													driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l2)+"']")).click();
													
													//Click the modifier
													driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+1)+"']")).click();
													
													if(!excel.getData(0, i, l2+2).equals("Not Available"))
													{
														//Click the modifier - Prefix
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+2)+"']")).click();
													}
													else
													{
														
													}
													
													
													
													if(excel.getData(0, i, 77).equals(mandatoryModifierCount))
													{
													
														if(!excel.getData(0, i, 53).equals("Not Available"))
														{
															String mandatoryModifierCount3 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
															
															mandatoryModifierCount3 = mandatoryModifierCount3.replace(" ", "");
																	
															System.out.println("Minimum Modifier count is : "+mandatoryModifierCount3);
															
															test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount3);
															
															for(int l3 = 53; l3 == 63;l3++)
															{
																//Click the mandatory modifier group
																driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l3)+"']")).click();
																
																//Click the modifier
																driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+1)+"']")).click();
																
																if(!excel.getData(0, i, l3+2).equals("Not Available"))
																{
																	//Click the modifier - Prefix
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+2)+"']")).click();
																}
																else
																{
																	
																}
																
																
																
																if(excel.getData(0, i, 78).equals(mandatoryModifierCount))
																{
																
																	if(!excel.getData(0, i, 31).equals("Not Available"))
																	{
																		String mandatoryModifierCount4 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
																		
																		mandatoryModifierCount4 = mandatoryModifierCount4.replace(" ", "");
																				
																		System.out.println("Minimum Modifier count is : "+mandatoryModifierCount4);
																		
																		test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount4);
																		
																		for(int l4 = 64; l4 == 74;l4++)
																		{
																			//Click the mandatory modifier group
																			driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l4)+"']")).click();
																			
																			//Click the modifier
																			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+1)+"']")).click();
																			
																			if(!excel.getData(0, i, l4+2).equals("Not Available"))
																			{
																				//Click the modifier - Prefix
																				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+2)+"']")).click();
																			}
																			else
																			{
																				
																			}
																			
																			
																			
																			if(excel.getData(0, i, 79).equals(mandatoryModifierCount))
																			{
																			
																				for (int j = 5; j == 17; j=j+3)
																				{
																					if(!excel.getData(0, i, j).equals("Not Available"))
																					{
																						//Click the optional modifier group
																						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																						
																						//Click the modifier
																						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																						
																						if(!excel.getData(0, i, j+2).equals("Not Available"))
																						{
																							//Click the modifier - Prefix
																							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																						}
																						else
																						{
																							
																						}
																					}
																				
																				}
																				
																			}
																		}
																		
																	}
																	
																}
																
																for (int j = 5; j == 17; j=j+3)
																{
																	if(!excel.getData(0, i, j).equals("Not Available"))
																	{
																		//Click the optional modifier group
																		driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																		
																		//Click the modifier
																		driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																		
																		if(!excel.getData(0, i, j+2).equals("Not Available"))
																		{
																			//Click the modifier - Prefix
																			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																		}
																		else
																		{
																			
																		}
																	}
																
																}
															}
															
														}
														
													}
													
													for (int j = 5; j == 17; j=j+3)
													{
														if(!excel.getData(0, i, j).equals("Not Available"))
														{
															//Click the optional modifier group
															driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
															
															//Click the modifier
															driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
															
															if(!excel.getData(0, i, j+2).equals("Not Available"))
															{
																//Click the modifier - Prefix
																driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
															}
															else
															{
																
															}
														}
													
													}
												}
												
											}
											
										}
										
										for (int j = 5; j == 17; j=j+3)
										{
											if(!excel.getData(0, i, j).equals("Not Available"))
											{
												//Click the optional modifier group
												driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
												
												//Click the modifier
												driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
												
												if(!excel.getData(0, i, j+2).equals("Not Available"))
												{
													//Click the modifier - Prefix
													driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
												}
												else
												{
													
												}
											}
										
										}
									}
									
								}
								
							}
							
							for (int j = 5; j == 17; j=j+3)
							{
								if(!excel.getData(0, i, j).equals("Not Available"))
								{
									//Click the optional modifier group
									driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
									
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
									
									if(!excel.getData(0, i, j+2).equals("Not Available"))
									{
										//Click the modifier - Prefix
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
									}
									else
									{
										
									}
								}
							
							}
						}
						
					}
				

				
				
				
				
				for (int j = 5; j == 17; j=j+3)
				{
					if(!excel.getData(0, i, j).equals("Not Available"))
					{
						//Click the optional modifier group
						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
						
						//Click the modifier
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
						
						if(!excel.getData(0, i, j+2).equals("Not Available"))
						{
							//Click the modifier - Prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
						}
						else
						{
							
						}
					}
				
				}
				
				
				
				
				
			}

			
			if(!excel.getData(0, i, 20).equals("Not Available"))
			{
				String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
				
				mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
						
				System.out.println("Minimum Modifier count is : "+mandatoryModifierCount);
				
				test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount);
				
				for(int l = 20; l == 30;l++)
				{
					//Click the mandatory modifier group
					driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l)+"']")).click();
					
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+1)+"']")).click();
					
					if(!excel.getData(0, i, l+2).equals("Not Available"))
					{
						//Click the modifier - Prefix
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+2)+"']")).click();
					}
					else
					{
						
					}
					
					
					
					if(excel.getData(0, i, 75).equals(mandatoryModifierCount))
					{
					
						if(!excel.getData(0, i, 31).equals("Not Available"))
						{
							String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
							
							mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
							
							System.out.println("Minimum Modifier count is : "+mandatoryModifierCount1);
							
							test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount1);
							
							for(int l1 = 31; l1 == 41;l1++)
							{
								//Click the mandatory modifier group
								driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l1)+"']")).click();
								
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+1)+"']")).click();
								
								if(!excel.getData(0, i, l1+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+2)+"']")).click();
								}
								else
								{
									
								}
								
								
								
								if(excel.getData(0, i, 76).equals(mandatoryModifierCount))
								{
								
									if(!excel.getData(0, i, 42).equals("Not Available"))
									{
										String mandatoryModifierCount2 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
										
										mandatoryModifierCount2 = mandatoryModifierCount2.replace(" ", "");
												
										System.out.println("Minimum Modifier count is : "+mandatoryModifierCount2);
										
										test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount2);
										
										for(int l2 = 42; l2 == 52;l2++)
										{
											//Click the mandatory modifier group
											driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l2)+"']")).click();
											
											//Click the modifier
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+1)+"']")).click();
											
											if(!excel.getData(0, i, l2+2).equals("Not Available"))
											{
												//Click the modifier - Prefix
												driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+2)+"']")).click();
											}
											else
											{
												
											}
											
											
											
											if(excel.getData(0, i, 77).equals(mandatoryModifierCount))
											{
											
												if(!excel.getData(0, i, 53).equals("Not Available"))
												{
													String mandatoryModifierCount3 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
													
													mandatoryModifierCount3 = mandatoryModifierCount3.replace(" ", "");
															
													System.out.println("Minimum Modifier count is : "+mandatoryModifierCount3);
													
													test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount3);
													
													for(int l3 = 53; l3 == 63;l3++)
													{
														//Click the mandatory modifier group
														driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l3)+"']")).click();
														
														//Click the modifier
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+1)+"']")).click();
														
														if(!excel.getData(0, i, l3+2).equals("Not Available"))
														{
															//Click the modifier - Prefix
															driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+2)+"']")).click();
														}
														else
														{
															
														}
														
														
														
														if(excel.getData(0, i, 78).equals(mandatoryModifierCount))
														{
														
															if(!excel.getData(0, i, 31).equals("Not Available"))
															{
																String mandatoryModifierCount4 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
																
																mandatoryModifierCount4 = mandatoryModifierCount4.replace(" ", "");
																		
																System.out.println("Minimum Modifier count is : "+mandatoryModifierCount4);
																
																test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount4);
																
																for(int l4 = 64; l4 == 74;l4++)
																{
																	//Click the mandatory modifier group
																	driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l4)+"']")).click();
																	
																	//Click the modifier
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+1)+"']")).click();
																	
																	if(!excel.getData(0, i, l4+2).equals("Not Available"))
																	{
																		//Click the modifier - Prefix
																		driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+2)+"']")).click();
																	}
																	else
																	{
																		
																	}
																	
																	
																	
																	if(excel.getData(0, i, 79).equals(mandatoryModifierCount))
																	{
																	
																		for (int j = 5; j == 17; j=j+3)
																		{
																			if(!excel.getData(0, i, j).equals("Not Available"))
																			{
																				//Click the optional modifier group
																				driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																				
																				//Click the modifier
																				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																				
																				if(!excel.getData(0, i, j+2).equals("Not Available"))
																				{
																					//Click the modifier - Prefix
																					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																				}
																				else
																				{
																					
																				}
																			}
																		
																		}
																		
																	}
																}
																
															}
															
														}
														
														for (int j = 5; j == 17; j=j+3)
														{
															if(!excel.getData(0, i, j).equals("Not Available"))
															{
																//Click the optional modifier group
																driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																
																//Click the modifier
																driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																
																if(!excel.getData(0, i, j+2).equals("Not Available"))
																{
																	//Click the modifier - Prefix
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																}
																else
																{
																	
																}
															}
														
														}
													}
													
												}
												
											}
											
											for (int j = 5; j == 17; j=j+3)
											{
												if(!excel.getData(0, i, j).equals("Not Available"))
												{
													//Click the optional modifier group
													driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
													
													//Click the modifier
													driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
													
													if(!excel.getData(0, i, j+2).equals("Not Available"))
													{
														//Click the modifier - Prefix
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
													}
													else
													{
														
													}
												}
											
											}
										}
										
									}
									
								}
								
								for (int j = 5; j == 17; j=j+3)
								{
									if(!excel.getData(0, i, j).equals("Not Available"))
									{
										//Click the optional modifier group
										driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
										
										//Click the modifier
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
										
										if(!excel.getData(0, i, j+2).equals("Not Available"))
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
										}
										else
										{
											
										}
									}
								
								}
							}
							
						}
						
					}
					
					for (int j = 5; j == 17; j=j+3)
					{
						if(!excel.getData(0, i, j).equals("Not Available"))
						{
							//Click the optional modifier group
							driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
							
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
							
							if(!excel.getData(0, i, j+2).equals("Not Available"))
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
							}
							else
							{
								
							}
						}
					
					}
				}
				
			}
			
			
			for (int j = 5; j == 17; j=j+3)
			{
				if(!excel.getData(0, i, j).equals("Not Available"))
				{
					//Click the optional modifier group
					driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
					
					//Click the modifier
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
					
					if(!excel.getData(0, i, j+2).equals("Not Available"))
					{
						//Click the modifier - Prefix
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
					}
					else
					{
						
					}
				}
			}
				
		
		}
		else
		{
			//Click the required Sub Category
			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 1)+"']")).click();
			
			if(excel.getData(0, i, 2).equals("Not Available"))
			{
				System.out.println("There Is No Menu Item available under the Sub Category");
				
				test.log(LogStatus.INFO, "There Is No Menu Item available under the Sub Category");
				
				
			}
			
			else
			{
				//Click the Menu Item
				driver.findElement(By.xpath("//p[.='"+excel.getData(0, i, 2)+"']")).click();
				
				if(excel.getData(0, i, 3).equals("Not Available") && excel.getData(0, i, 5).equals("Not Available") && excel.getData(0, i, 20).equals("Not Available"))
				{
					System.out.println("There Is No Serving Size and No Modifiers are available");
					
					test.log(LogStatus.INFO, "There Is No Serving Size and No Modifiers are available");
					
					
				}
				
				if(!excel.getData(0, i, 3).equals("Not Available"))
				{
					//Click the serving size
					driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 3)+"']")).click();
					
					//Click the serving size option
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 4)+"']")).click();
					
					

						if(!excel.getData(0, i, 20).equals("Not Available"))
						{
							String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
							
							mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
									
							System.out.println("Minimum Modifier count is : "+mandatoryModifierCount);
							
							test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount);
							
							for(int l = 20; l == 30;l++)
							{
								//Click the mandatory modifier group
								driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l)+"']")).click();
								
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+1)+"']")).click();
								
								if(!excel.getData(0, i, l+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+2)+"']")).click();
								}
								else
								{
									
								}
								
								
								
								if(excel.getData(0, i, 75).equals(mandatoryModifierCount))
								{
								
									if(!excel.getData(0, i, 31).equals("Not Available"))
									{
										String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
										
										mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
										
										System.out.println("Minimum Modifier count is : "+mandatoryModifierCount1);
										
										test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount1);
										
										for(int l1 = 31; l1 == 41;l1++)
										{
											//Click the mandatory modifier group
											driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l1)+"']")).click();
											
											//Click the modifier
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+1)+"']")).click();
											
											if(!excel.getData(0, i, l1+2).equals("Not Available"))
											{
												//Click the modifier - Prefix
												driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+2)+"']")).click();
											}
											else
											{
												
											}
											
											
											
											if(excel.getData(0, i, 76).equals(mandatoryModifierCount))
											{
											
												if(!excel.getData(0, i, 42).equals("Not Available"))
												{
													String mandatoryModifierCount2 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
													
													mandatoryModifierCount2 = mandatoryModifierCount2.replace(" ", "");
															
													System.out.println("Minimum Modifier count is : "+mandatoryModifierCount2);
													
													test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount2);
													
													for(int l2 = 42; l2 == 52;l2++)
													{
														//Click the mandatory modifier group
														driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l2)+"']")).click();
														
														//Click the modifier
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+1)+"']")).click();
														
														if(!excel.getData(0, i, l2+2).equals("Not Available"))
														{
															//Click the modifier - Prefix
															driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+2)+"']")).click();
														}
														else
														{
															
														}
														
														
														
														if(excel.getData(0, i, 77).equals(mandatoryModifierCount))
														{
														
															if(!excel.getData(0, i, 53).equals("Not Available"))
															{
																String mandatoryModifierCount3 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
																
																mandatoryModifierCount3 = mandatoryModifierCount3.replace(" ", "");
																		
																System.out.println("Minimum Modifier count is : "+mandatoryModifierCount3);
																
																test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount3);
																
																for(int l3 = 53; l3 == 63;l3++)
																{
																	//Click the mandatory modifier group
																	driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l3)+"']")).click();
																	
																	//Click the modifier
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+1)+"']")).click();
																	
																	if(!excel.getData(0, i, l3+2).equals("Not Available"))
																	{
																		//Click the modifier - Prefix
																		driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+2)+"']")).click();
																	}
																	else
																	{
																		
																	}
																	
																	
																	
																	if(excel.getData(0, i, 78).equals(mandatoryModifierCount))
																	{
																	
																		if(!excel.getData(0, i, 31).equals("Not Available"))
																		{
																			String mandatoryModifierCount4 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
																			
																			mandatoryModifierCount4 = mandatoryModifierCount4.replace(" ", "");
																					
																			System.out.println("Minimum Modifier count is : "+mandatoryModifierCount4);
																			
																			test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount4);
																			
																			for(int l4 = 64; l4 == 74;l4++)
																			{
																				//Click the mandatory modifier group
																				driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l4)+"']")).click();
																				
																				//Click the modifier
																				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+1)+"']")).click();
																				
																				if(!excel.getData(0, i, l4+2).equals("Not Available"))
																				{
																					//Click the modifier - Prefix
																					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+2)+"']")).click();
																				}
																				else
																				{
																					
																				}
																				
																				
																				
																				if(excel.getData(0, i, 79).equals(mandatoryModifierCount))
																				{
																				
																					for (int j = 5; j == 17; j=j+3)
																					{
																						if(!excel.getData(0, i, j).equals("Not Available"))
																						{
																							//Click the optional modifier group
																							driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																							
																							//Click the modifier
																							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																							
																							if(!excel.getData(0, i, j+2).equals("Not Available"))
																							{
																								//Click the modifier - Prefix
																								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																							}
																							else
																							{
																								
																							}
																						}
																					
																					}
																					
																				}
																			}
																			
																		}
																		
																	}
																	
																	for (int j = 5; j == 17; j=j+3)
																	{
																		if(!excel.getData(0, i, j).equals("Not Available"))
																		{
																			//Click the optional modifier group
																			driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																			
																			//Click the modifier
																			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																			
																			if(!excel.getData(0, i, j+2).equals("Not Available"))
																			{
																				//Click the modifier - Prefix
																				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																			}
																			else
																			{
																				
																			}
																		}
																	
																	}
																}
																
															}
															
														}
														
														for (int j = 5; j == 17; j=j+3)
														{
															if(!excel.getData(0, i, j).equals("Not Available"))
															{
																//Click the optional modifier group
																driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																
																//Click the modifier
																driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																
																if(!excel.getData(0, i, j+2).equals("Not Available"))
																{
																	//Click the modifier - Prefix
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																}
																else
																{
																	
																}
															}
														
														}
													}
													
												}
												
											}
											
											for (int j = 5; j == 17; j=j+3)
											{
												if(!excel.getData(0, i, j).equals("Not Available"))
												{
													//Click the optional modifier group
													driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
													
													//Click the modifier
													driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
													
													if(!excel.getData(0, i, j+2).equals("Not Available"))
													{
														//Click the modifier - Prefix
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
													}
													else
													{
														
													}
												}
											
											}
										}
										
									}
									
								}
								
								for (int j = 5; j == 17; j=j+3)
								{
									if(!excel.getData(0, i, j).equals("Not Available"))
									{
										//Click the optional modifier group
										driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
										
										//Click the modifier
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
										
										if(!excel.getData(0, i, j+2).equals("Not Available"))
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
										}
										else
										{
											
										}
									}
								
								}
							}
							
						}
					

					
					
					
					
					for (int j = 5; j == 17; j=j+3)
					{
						if(!excel.getData(0, i, j).equals("Not Available"))
						{
							//Click the optional modifier group
							driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
							
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
							
							if(!excel.getData(0, i, j+2).equals("Not Available"))
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
							}
							else
							{
								
							}
						}
					
					}
					
					
					
					
					
				}

				
				if(!excel.getData(0, i, 20).equals("Not Available"))
				{
					String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
					
					mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
							
					System.out.println("Minimum Modifier count is : "+mandatoryModifierCount);
					
					test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount);
					
					for(int l = 20; l == 30;l++)
					{
						//Click the mandatory modifier group
						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l)+"']")).click();
						
						//Click the modifier
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+1)+"']")).click();
						
						if(!excel.getData(0, i, l+2).equals("Not Available"))
						{
							//Click the modifier - Prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l+2)+"']")).click();
						}
						else
						{
							
						}
						
						
						
						if(excel.getData(0, i, 75).equals(mandatoryModifierCount))
						{
						
							if(!excel.getData(0, i, 31).equals("Not Available"))
							{
								String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
								
								mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
								
								System.out.println("Minimum Modifier count is : "+mandatoryModifierCount1);
								
								test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount1);
								
								for(int l1 = 31; l1 == 41;l1++)
								{
									//Click the mandatory modifier group
									driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l1)+"']")).click();
									
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+1)+"']")).click();
									
									if(!excel.getData(0, i, l1+2).equals("Not Available"))
									{
										//Click the modifier - Prefix
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l1+2)+"']")).click();
									}
									else
									{
										
									}
									
									
									
									if(excel.getData(0, i, 76).equals(mandatoryModifierCount))
									{
									
										if(!excel.getData(0, i, 42).equals("Not Available"))
										{
											String mandatoryModifierCount2 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
											
											mandatoryModifierCount2 = mandatoryModifierCount2.replace(" ", "");
													
											System.out.println("Minimum Modifier count is : "+mandatoryModifierCount2);
											
											test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount2);
											
											for(int l2 = 42; l2 == 52;l2++)
											{
												//Click the mandatory modifier group
												driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l2)+"']")).click();
												
												//Click the modifier
												driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+1)+"']")).click();
												
												if(!excel.getData(0, i, l2+2).equals("Not Available"))
												{
													//Click the modifier - Prefix
													driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l2+2)+"']")).click();
												}
												else
												{
													
												}
												
												
												
												if(excel.getData(0, i, 77).equals(mandatoryModifierCount))
												{
												
													if(!excel.getData(0, i, 53).equals("Not Available"))
													{
														String mandatoryModifierCount3 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
														
														mandatoryModifierCount3 = mandatoryModifierCount3.replace(" ", "");
																
														System.out.println("Minimum Modifier count is : "+mandatoryModifierCount3);
														
														test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount3);
														
														for(int l3 = 53; l3 == 63;l3++)
														{
															//Click the mandatory modifier group
															driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l3)+"']")).click();
															
															//Click the modifier
															driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+1)+"']")).click();
															
															if(!excel.getData(0, i, l3+2).equals("Not Available"))
															{
																//Click the modifier - Prefix
																driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l3+2)+"']")).click();
															}
															else
															{
																
															}
															
															
															
															if(excel.getData(0, i, 78).equals(mandatoryModifierCount))
															{
															
																if(!excel.getData(0, i, 31).equals("Not Available"))
																{
																	String mandatoryModifierCount4 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
																	
																	mandatoryModifierCount4 = mandatoryModifierCount4.replace(" ", "");
																			
																	System.out.println("Minimum Modifier count is : "+mandatoryModifierCount4);
																	
																	test.log(LogStatus.INFO, "Minimum Modifier count is : "+mandatoryModifierCount4);
																	
																	for(int l4 = 64; l4 == 74;l4++)
																	{
																		//Click the mandatory modifier group
																		driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, l4)+"']")).click();
																		
																		//Click the modifier
																		driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+1)+"']")).click();
																		
																		if(!excel.getData(0, i, l4+2).equals("Not Available"))
																		{
																			//Click the modifier - Prefix
																			driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, l4+2)+"']")).click();
																		}
																		else
																		{
																			
																		}
																		
																		
																		
																		if(excel.getData(0, i, 79).equals(mandatoryModifierCount))
																		{
																		
																			for (int j = 5; j == 17; j=j+3)
																			{
																				if(!excel.getData(0, i, j).equals("Not Available"))
																				{
																					//Click the optional modifier group
																					driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																					
																					//Click the modifier
																					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																					
																					if(!excel.getData(0, i, j+2).equals("Not Available"))
																					{
																						//Click the modifier - Prefix
																						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																					}
																					else
																					{
																						
																					}
																				}
																			
																			}
																			
																		}
																	}
																	
																}
																
															}
															
															for (int j = 5; j == 17; j=j+3)
															{
																if(!excel.getData(0, i, j).equals("Not Available"))
																{
																	//Click the optional modifier group
																	driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
																	
																	//Click the modifier
																	driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
																	
																	if(!excel.getData(0, i, j+2).equals("Not Available"))
																	{
																		//Click the modifier - Prefix
																		driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
																	}
																	else
																	{
																		
																	}
																}
															
															}
														}
														
													}
													
												}
												
												for (int j = 5; j == 17; j=j+3)
												{
													if(!excel.getData(0, i, j).equals("Not Available"))
													{
														//Click the optional modifier group
														driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
														
														//Click the modifier
														driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
														
														if(!excel.getData(0, i, j+2).equals("Not Available"))
														{
															//Click the modifier - Prefix
															driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
														}
														else
														{
															
														}
													}
												
												}
											}
											
										}
										
									}
									
									for (int j = 5; j == 17; j=j+3)
									{
										if(!excel.getData(0, i, j).equals("Not Available"))
										{
											//Click the optional modifier group
											driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
											
											//Click the modifier
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
											
											if(!excel.getData(0, i, j+2).equals("Not Available"))
											{
												//Click the modifier - Prefix
												driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
											}
											else
											{
												
											}
										}
									
									}
								}
								
							}
							
						}
						
						for (int j = 5; j == 17; j=j+3)
						{
							if(!excel.getData(0, i, j).equals("Not Available"))
							{
								//Click the optional modifier group
								driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
								
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
								
								if(!excel.getData(0, i, j+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
								}
								else
								{
									
								}
							}
						
						}
					}
					
				}
				
				
				for (int j = 5; j == 17; j=j+3)
				{
					if(!excel.getData(0, i, j).equals("Not Available"))
					{
						//Click the optional modifier group
						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
						
						//Click the modifier
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
						
						if(!excel.getData(0, i, j+2).equals("Not Available"))
						{
							//Click the modifier - Prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
						}
						else
						{
							
						}
					}
				}
					
			}
		}




		
		


	}
	
}














