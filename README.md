# RipperCurse
A java script to download a modpack from Curseforge onto your linux machine


This script is to be run with Java 19.
Install it on ubuntu using `apt-get install openjdk-19-jre`

The script uses the Modpack-zip file downloaded from Curseforge and downloads the mods using wget.
Yes, the code is messy.
No, i won't fix the messy code.


To run, place this .jar and the modpack zip into you ./minecraft/versions/[profile name] folder and run the jar using `java -jar CurseRipper.jar --ModFile [filename]`
Later install the correct version of forge for your pack and set your game directory accordingly
That's all, your machine will download the files from curseforge using wget and you are good to go
