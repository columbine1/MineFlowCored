#Mineflow Factions Java Repository #

This is where all core Factions functionality and most plugins are controlled.

---

### Modules ###

* **MineflowCore** - provides all core functionality for Mineflow Factions in addition to smaller fixes and add-ons.

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

### Who do I talk to? ###

* **Luke** - will answer any development-related question given you are contributing to this repository
* **Donovon** - can help with administrative problems on the remote machine
* **Matthew** - can help with spigot/bungee server issues