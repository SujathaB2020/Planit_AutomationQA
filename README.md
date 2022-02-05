# Planit_AutomationQA

# Introduction
This Automation Test is created using Java + Selenium Web Driver, Which can be used across different web based applications.

# Project:
- project : AutomationQA
- package : com.planit

# Prerequisites:

- Java jdk-12.0.1
- ChromeDriver drivers
- Selenium WebDriver jars
- TestNG jars

# Eclipse Instructions:

**Prerequisites:**

Install Eclipse, Chrome drivers, Selenium jar files, TestNG jar files and optionally the GitHub plugin.

* Import project into Eclipse
* Replace the chrome driver path with the path of chrome driver where it is installed on your device in all java files

# Run Instructions:
* Right-click on each java file
* Run As > TestNG Test

# Command line excecution instructions:

**Prerequisites:**

* Create a folder named 'lib' inside project
* Copy all selenium and testNG jar files into lib folder

# Run Instructions:
* Open command prompt on windows
* Run below command to set class path
* set classpath = {project bin folder path};{lib folder path}\\*
* Run below command to execute test
* java org.testng.TestNG {filename}.xml
