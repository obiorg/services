# <img src="https://github.com/obiorg/obi-services/blob/main/src/main/resources/img/obi/obi-signet-light.png?raw=true" width="48" height="48"> OBI-SERVICES 
services is the main instance running background process for obi


## Introduction

OBI is devlop with Netbeans  IDE. As coding is in Java, it is not mendatory to use it. In the following document we considere using Netbeans IDE. Please adapt and suggest if solution is provide for other IDE.
<!-- TO DO: add more gloabl information here later -->


## Start a new developpement

> If you have already import obi-core in your local directory, you can jump to step 3. Otherwise, keep normal reading

**Prequise :**
1. **obi-core** should already be build and available in central repositories (.m2 maven)
> Note: you should care about version require by *obi-services* 

**STEP 01 : Create a directory "OBI" that will contain all OBI tools**

You need to specify a directory for your full project **OBI** in which you will have all your repositories.
Examples of dirotory : OBI, repos,...
In the following step we will use "OBI"

**STEP 02 : using netbean, create an empty project module maven**

1. To create new project : File > New Project (Ctrl+Maj+N) > Java With Maven > Select *POM Project* ; do Next
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

1. Right clic dependencies directory of clone project > Select "Dowload Declared Dependencies",
  > Note : only dependencies [sqlserver4.2](https://www.microsoft.com/en-us/download/details.aspx?id=54671]) need to be manual install by right clic on package dependencies > select "Manual Install". This dependencie is available either from resources directory of project "LIBS" or you can download from link above online.
2. Construct the project by apply build (right clic on project than build),
  > Note : it may also be necessary to build the main pom totaly.

> If you can not download dependencies from maven, please build pom project than try to build project.


**STEP 06 : generate documentation**

Generate documentation by right clic on project and select "Generate Javadoc"

**STEP 07 : insert project into main module (OBI)**

Go to Module OBI : right clic on directory module > select add existing module > select project *obi-services*

You can now close or open from module the project. OBI-SERVICES is now ready to compile and execute.


## Dependencies
- obi-core : build from sources
- [sqlserver 4.2](https://www.microsoft.com/en-us/download/details.aspx?id=54671])


## Divers




