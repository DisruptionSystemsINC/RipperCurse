# RipperCurse
A java script to download a modpack from Curseforge onto your linux machine


This script is to be run with Java 19.
Install it on ubuntu using `apt-get install openjdk-19-jre`

The script uses the Modpack-zip file downloaded from Curseforge and downloads the mods using wget.
Yes, the code is messy.
No, i won't fix the messy code.


To run, place this .jar and the modpack zip into your MC profile folder and run the jar using `java -jar CurseRipper.jar --ModFile [filename]`
Make sure you set this folder as your game folder for the instance, else you will launch from the default gamefolder
That's all, your machine will download the files from curseforge using wget and you are good to go
