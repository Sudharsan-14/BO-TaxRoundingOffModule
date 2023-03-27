set projectLocation=C:\Automation\BO
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\jarfiles\*
java org.testng.TestNG %projectLocation%\testng_Settings_Account_User.xml
