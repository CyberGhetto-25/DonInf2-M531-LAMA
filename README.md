# DonInf2 Starting Code
This some boiler plate code for the DonInf2 course project. Use this code as starting point for your project.

## Setup
### 1. Repository On Github
First you need to get a copy of this repository, the best approach is to fork this repository. a fork is a github specific functionality, it creates your own copy of a repository. You need a github account in order to fork a repository.
**Only one team member should perform that**
- Login to your github account
- At the top right of the github window use the **Fork** button > **Create new fork**
- Name it <M53-X-groupname> according to your class and group
- Share the repository with your group members
    - Go to Settings
    - Collaborators and team
    - (Manage access) > Add people

### 2. Setup your local workspace

Now, each team member can configure his/her local workspace.

#### 2.1 OPTIONAL install git command line
If you want to learn command line tools as well, you can install the git command line on your machine.
[instructions here](https://github.com/git-guides/install-git)

#### 2.2 Repository local setup

An easy option is to use the Github Desktop application
- Download the app and install it
- Open it and log in with your github account
- At the top left click **Add** and choose **Clone Repository**
- Pick your newly created repository and choose a directory to clone it on your machine.

In the directory you chose above you should now have a new directory named after you repository name.

Alternatively you can do it on the command line with git directly
- In your github repository click the **Code** button and select SSH and copy the URL
- Open a console in the directory you want your repository
- clone the repository with the command
```
git clone <copied-url>
```

## Running the project

### IDE
Use your favorite IDE to run the project. Open the repository directory in your IDE and run the main which is found in `src/main/Main.java`.

### command line
Alternatively, you can run the project with the `buildAndRun.sh` script. Open a terminal or console in the repository directory and run the following command

```
./buildAndRun.sh
```

To run the script, you need java installed on your computer.
If running a Windows computer you also need bash shell installed (for example Git For Windows https://gitforwindows.org/).

## Examples
A few string manipulation examples are found in the `src/exemples` folder and called in the main. Feel free to remove them.

## About this README
Feel free to edit this README as well, it's yours :)
The format of this file is [markdown](https://markdownlivepreview.com/)

Hello it's a me, Mario!!!!