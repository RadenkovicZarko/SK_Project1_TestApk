# Storage Management Library Test App

This GitHub repository features a TestApp project designed to demonstrate the practical application of two distinct storage management implementations against a single specification. It serves as an educational tool for the "Software Components" subject, showcasing how dependencies are managed and utilized within a Maven project framework. The project allows users to interactively choose between local file storage and Google Drive storage implementations, illustrating the versatility and modularity of software components in action.




# Getting Started

## Prerequisites
Java 11 or later
Maven or Gradle (for dependency management)
Access to Google Drive API (for the Google Drive implementation)


## Building from Source
To build the Storage Management Library from source and install it on your local machine, follow these steps:

1.Clone the repository to your local machine
2.Use Maven to clean the existing builds and compile the project: mvn clean install



After successfully building and installing the project, you can include it as a dependency in your Maven projects by adding the following to your pom.xml file (make sure to replace x.y.z with the actual version of the library you've installed):
<dependency>
    <groupId>com.yourdomain</groupId>
    <artifactId>storage-management-library</artifactId>
    <version>x.y.z</version>
</dependency>





# Testing
To test the Storage Management Library, you will need to download and install two additional repositories, along with the test repository. Follow the steps below to set up your environment for testing.

Step 1: Clone the Repositories
First, clone this repository as well as two additional repositories required for testing(SK_Project1_GoogleDriveImplementation, SK_Project1_LocalStorageImplementation, SK_Project1_Specification), also you should clone SK_Project1_TestApk repository. 

Step 2: Install the Projects
Navigate to each cloned repository's root directory and run the Maven install command. This will compile the projects and install them to your local Maven repository, making them available as dependencies.
Command for all three repositories (SK_Project1_Specification, SK_Project1_GoogleDriveImplementation, SK_Project1_LocalStorageImplementation) is mvn clean install

Step 3: Run main in project SK_Project1_TestApk
