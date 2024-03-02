# services
services is the main instance running background process for obi


## Introduction

OBI is devlop with Netbeans  IDE. As coding is in Java, it is not mendatory to use it. In the following document we considere using Netbeans IDE. Please adapt and suggest if solution is provide for other IDE.
<!-- TO DO: add more gloabl information here later -->


## Start a new developpement

> If you have already import obi-core in your local directory, you can jump to step 3. Otherwise, keep normal reading

**STEP 01 : Create a directory "OBI" that will contain all OBI tools**

You need to specify a directory for your full project **OBI** in which you will have all your repositories.
Examples of dirotory : OBI, repos,...
In the following step we will use "OBI"

**STEP 02 : using netbean, create an empty project module maven**
1. To create new project : File > New Project (Ctrl+Maj+N) > Java With Maven > Select *POM Project" ; do Next
2. Specify Project Name *OBI", path directory in which to create project POM
3. Define the group id "org.obi" with version "0.0.0" (if still last version)
4. End up by changing package with *org.obi* before pressing finish

> This will create a direcoty contains only one file "pom.xml" which will keep information about the different project

**STEP 03 : copy last git reference to clone**
You will find, on the top corner of the page of the actual repository the possibility to copy url for clonning project.

Example : clic on code > copy path value (ex: https://github.com/obiorg/obi-services.git)

**STEP 04 : proceed cloning in to netbeans**
Go back to Netbeans IDE, use menu Team > git > clone
1. In the open window, past the URL in memory
2. Specify user and passord (Passord, from github main setting in menu Devlopper token)
3. Unsure correct path as defined directory **OBI** explain in STEP 01
4. Follow by next and while request activate checkout branches needed (ex: main, and dev)

**STEP 05 : download defined dependencies**
1. Right clic dependencies of clone project apprearing on left side and select Dowload Declared Dependencies,
2. Construct the project by apply build (right clic on project than build),
9. Generate documentation by right click on project and select "Generate Javadoc"
10. Go to Module OBI : right click and add existing module point to obi-core
Project library obi-core is ready to use


> If you can not download dependencies from maven, please build pom project than try to build project.

> Note : only dependencies sqlserver4.2 need to be recover from other sources libs from the project or download from

---
## Dependencies
sqlserver 4.2 (sqljdbc42)

---
## Divers




