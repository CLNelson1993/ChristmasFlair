# Adding diozero to IntelliJ project + *info on diozero's sample app
1. Download the distribution file here: [https://search.maven.org/remotecontent?filepath=com/diozero/diozero-distribution/1.3.5/diozero-distribution-1.3.5-bin.zip]. Extract the directory named diozero-distribution-1.3.5 to a temporary place.
2. Create a new directory in ChristmasFlair named libs. It should look something like this:
   C:\...\ChristmasFlair\libs\
3. In diozero-distribution-1.3.5, move all .jar files into the new libs directory.
4. In your IntelliJ project, the .jar files should be showing in libs now.
5. Right click on libs, click "Add as library..."
6. -Name = libs
   -Level = Project Library
   -Add to module: ChristmasFlair
7. if I didn't mislead you, diozero should now be integrated into your IntelliJ project.
8. To test it:
   -I've put together a class named LEDstrip_LessParameters.java (not sure if it functions yet but it will help here)
   -Make a new java class in IntelliJ named LEDstrip_LessParameters.
   -Copy & Paste my code into your new java class.
   -You should see no errors or warnings, which means diozero is working.

# *IMPORTANT INFO TO HELP GET STARTED (sample app)
The following link is a sample app that was made by the author of diozero themselves. We found it while digging through the library. Awesome find because it already contains some useful methods that apply to some of our tasks! Also, it's probably important to note that our Professor gave us full permission to use this. I basically copy/pasted the rainbow method to make LEDstrip_LessParameters.java -- LINK: [https://github.com/mattjlewis/diozero/blob/main/diozero-ws281x-java/src/main/java/com/diozero/ws281xj/sampleapps/WS281xTest.java]