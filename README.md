# README #


### CONTENTS OF THIS FILE ###


Appium Automation framework for app automation on Android 

# Table of Contents<div id="table of content"/>
* [Tools & Libraries](#tools-and-libraries)
* [Pre-requisites](#pre-requisites)
* [Setup](#setup)
* [Running tests](#running-tests)
* [Pre-conditions](#pre-conditions)
* [Reporting](#reporting)

## General info
This project is for Automation Suite of Srimandir App.

## Tools and Libraries <div id="tools-and-libraries"/>
1. Appium (For writing automation scripts)
2. Testng (For validations and assertions)
3. Maven (For repository management)
4. Android Studio(For Platform tools and ADB )


## Pre-requisites (**In this order**) <div id="pre-requisites"/>
1. Java should be installed and JAVA_HOME env should be set up (JDK8 and above)
2. Install Maven **brew install maven**
3. Android Studio for Platform tools and ADB 
4. ANDROID_HOME and ANDROID_SDK paths should be set in terminal profile
6. Android Studio for emulators 
7. NodeJS (Install through brew : **brew install node**)
8. Appium (Install through node : **npm install -g appium**)


## Setup<div id="setup"/>
- Clone the project from the following link :- https://bitbucket.org/apps-for-bharat/venus/src/TestYantra/
- Import the project as Maven project in the IDE.
- Resolve the maven dependencies for the first import.


#### Pre-conditions :- <div id="pre-conditions"/>
For image comparision use Nexus 5X as emulator device for image comparision.


## Running the test cases <div id="running-tests"/>
Here We run tests in two Ways 
1.By running any test case or test class directly from the respective test class.
2.Runnning from Maven CL using Maven command.
mvn test-Dtest=#Name of Script#Name of method -Dlanguage="language"
Ex:mvn test -Dtest=Tests#SMPK_TC_2067_verifyThatUserIsNavigatedToAajKiSujaavScreenonclickingAajtabFromtheBottomNavigationBar -Dlanguage="hindi" 


## Reporting 
- For reporting, ExtentReport library is used and ExtentReportListener is the implemented class for this.
- If the main test case is executed from the Base class, then the ExtentReportListener is executed by default.

