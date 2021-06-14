import requests
import sys

print("Running version check", sys.argv[0])
kspigot_version = requests.get(sys.argv[1]).json()["name"]
print("KSpigot has version", kspigot_version)
shadowjar_version = requests.get(sys.argv[2]).json()["name"]
print("ShadowJar has version", shadowjar_version)
kotlin_version = requests.get(sys.argv[3]).json()["name"]
print("Kotlin has version", kotlin_version)
gradle_version = requests.get(sys.argv[4]).json()["name"]
print("Gradle has version", gradle_version)
"""
Here we are Building the latest gradle.properties file
"""
with open("./gradle.properties") as f:
    f.write("Does that work?")
    f.close()