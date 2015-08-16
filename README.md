#Mineflow Factions Java Repository #

This is where all core Factions functionality and most plugins are controlled.

---

### Modules ###

* **FactionsFly** - custom implementation of a /fly command that can only be used in your Faction territory. This module also provides support for adding a cost to use, auto disabling when an enemy comes within X blocks of you, and auto disabling during combat.
* **MFExtras** - small fixes and add-ons for Mineflow Factions.

---

### Maven Setup ###

In your `~/.m2/settings.xml` file, add the following inside of the `<settings>` block:

    :::xml
    <settings xmlns="http://maven.apache.org/POM/4.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
      <servers>
        <server>
          <id>mineflow-ftp</id>
          <username>user</username>
          <password>password</password>
        </server>
      </servers>
    </settings>

Where 'user' and 'password' are your credentials for the Mineflow FTP maven repository.

Also, you'll need to run `BuildTools.jar` to generate local repositories for spigot. The output from building the
spigot/craftbukkit sources are irrelevant - you just need the repositories required for building to be regenerated in
your `~/.m2/repository` folder.

Then you need to either install Maven 2 or use the installation that BuildTools downloads for you. You may have to point
your IDE to the installation folder and/or set the `$M2_HOME` environment variable.

---

### Continuous Integration ###

The Jenkins server handles deploying code and generating artifacts to use in both test and live server builds. However, if you want to update the Mineflow maven repository, then you can run 'mvn deploy' on the project you want to deploy. The POM files for projects don't have to be touched if you followed the steps from *Maven Setup* properly.

---

### Versions ###

* **Java** - 1.8.0_45
* **Spigot** - git-Spigot-44c59bf-1880a9c (MC: 1.8.7) (Implementing API version 1.8.7-R0.1-SNAPSHOT)
* **Vault** - 1.5.3-b37

---

### Who do I talk to? ###

* **Zak** - can help with administrative problems on the remote machine
* **Dragon** - can help with spigot/bungee server issues