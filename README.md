# RipperCurse
A java script to download a modpack from Curseforge onto your linux machine

Some Packs do not work, as their manifest.JSON is different from the one this program searches for
(For whatever reason the JSON is not standardized, screw you Curse)

This script is to be run with Java 11.
Install it on ubuntu using `apt-get install openjdk-11-jre` or `apt-get install default-jre`

The script uses the Modpack-zip file downloaded from Curseforge and downloads the mods using wget.
Yes, the code is messy.
No, i won't fix the messy code.


To run, CLI place this .jar and the modpack zip into your MC profile folder and run the jar using `java -jar RipperCurseCLI.jar --ModFile [filename]`

To run GUI version, mark the jarfile as executeable using `chmod a+x RipperCurse.jar`, then simply double-click the file to run it.
Make sure you set this folder as your game folder for the instance, else you will launch from the default gamefolder
That's all, your machine will download the files from curseforge using wget and you are good to go


Changelog:

16.05.2023: GUI Version using swift added.
Download it in the releases section



Confirmed working Modpacks:

Forever Stranded
Manufactio
