# rapiddweller-benerator-ce

[rapiddweller 'Benerator'](https://www.benerator.de) is a software solution to 
generate, anonymize, pseudonymize and migrate data for
development, testing and training purposes.

[[_TOC_]]

## Introduction

[rapiddweller 'Benerator'](https://www.benerator.de) allows creating realistic and valid 
high-volume test data, used for testing (unit/integration/load) and showcase setup.

Metadata constraints are imported from systems and/or configuration files. Data can imported from
and exported to files and systems, anonymized or generated from scratch. Domain packages provide
reusable generators for creating domain-specific data as names and addresses internationalizable
in language and region. It is strongly customizable with plugins and configuration options.

rapiddweller 'Benerator' is build for Java 1.8 and Java 11. The development is ongoing for Java 11.

## Prerequisites

- Java 1.8 or Java 11 JDK (we recommend [adoptopenjdk](https://adoptopenjdk.net/))
- [Maven](https://maven.apache.org/)

Check your local setup
```bash
java -version
mvn -version 
```

## Installation

1. Choose how to install:  
    
    a) Download a Prebuilt Distribution from **Project Overview > Releases**   
    (current release is `1.0.0`, cp. rapiddweller-benerator-ce-1.0.0+jdk8-dist.zip) 
    and unzip the downloaded file in an appropriate directory, 
    e.g. /Developer/Applications or C:\Program Files\Development.
    
    b) Checkout repository and build your own rapiddweller-benerator-ce using   
    maven command `mvn clean install`
    
    
2. Set **BENERATOR_HOME**  
   Create an environment variable BENERATOR_HOME that points to the path you extracted benerator to.
   
    - Windows Details: Open the System Control Panel, choose Advanced Settings - Environment Variables. 
      Choose New in the User Variables section. Enter BENERATOR_HOME as name and the path as value 
      (e.g. C:\Program Files\Development\rapiddweller-benerator-ce-1.0.0+jdk8). Click OK several times.
    - Mac/Unix/Linux Details: Add an entry that points to benerator, 
      e.g.: `export BENERATOR_HOME=/Developer/Applications/rapiddweller-benerator-ce-1.0.0+jdk8`


3. On Unix/Linux/Mac systems: **Set permissions**   
   Open a shell on the installation's root directory and execute
    `chmod a+x bin/*.sh`  
   

4. Mac OS X configuration **Set JAVA_HOME**  
   On Mac OS X you need to provide benerator with an explicit configuration of the JAVA_HOME path. 
   See http://developer.apple.com/qa/qa2001/qa1170.html for a good introduction to the OS X way of setting up Java. 
   It is based on aliases conventions. If you are not familiar with that, you should read the article. 
   If Java 8 (or newer) is the default version you will use, you can simply define JAVA_HOME by adding the 
   following line to your .profile: in your user directory:
   `export JAVA_HOME=/Library/Java/Home`
   If it does not work or if you need to use different Java versions, it is easier to 'hard-code' 
   JAVA_HOME like this:
   `export  JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/`
   
    **Note**: We currently recommend following projects for your proper Mac OS X setup:         
    -  [brew](https://brew.sh/)
    -  [adoptopenjdk](https://adoptopenjdk.net/)
    -  [jenv](https://www.jenv.be/)
   
## Run

If you followed above installation steps carefully, run your benerator scripts from commandline.

1. Start benerator from command line
```bash
benerator <YOUR_BENERATOR_SCRIPT>.xml
```

To get started please find some demo scripts in the folder 
[/src/demo/resources/demo](/src/demo/resources/demo) of this repository.

## Docs

There are various sources to get you started or extend your benerator knowledge:
- Download the [Benerator Manual](https://www.benerator.de/ce/1.0.0/rapiddweller-benerator-ce-manual-1.0.0.pdf) 
  from our website.
- Read the docs on our [Docs site](https://www.benerator.de/ce/1.0.0/doc/) or 
  create your own docs using maven `mvn site:site`. 
  The generated docs include Javadoc, Test Reports and more.
- Checkout the maintainers website [www.benerator.de](https://www.benerator.de/) for additional support resources.  
  

## Getting Involved

If you would like to reach out to the maintainers, contact us via our 
[Contact-Form](https://www.benerator.de/contact-us) or email us at 
[solution.benerator@rapiddweller.com](mailto://solution.benerator@rapiddweller.com).


## Contributing

Please see our [Contributing](CONTRIBUTING.md) guidelines. 
For releasing see our [release creation guide](RELEASE.md). 
And check out the maintainers [website!](https://rapiddweller.com)