# Welcome to Athena Web Automation Project

This maven project consists of following technologies/frameworks:
- Selenium
- Cucumber
- TestNG
- Allure

This combination allows you to easily write UI tests in Java.
Besides, to apply Page Object Model to design pattern.

## Changed log:

* Multiple browsers (Firefox, Chrome)
* BDD (Using Gherkin)
* Take screenshot (failed test or all test)
* Implement whole case of functional test
* Allure test report
* Run test by suite, class or scenario
* Run parallel and serial
* CI/CD

## Setup
![img.png](src/test/resources/img.png)

## UI Tests modules
Currently, there are following modules available (use them for tests of specific area and feel free to add new):
* core - includes all core module project (abstract, enum, util and constant)
* pages - includes all page object which implement web elements and actions
* stepDefinitions - define and implement all steps
* TestRunners - implement flow to run test

## Prefix of Element name
```
  /***************************
     * btn -> button
     * chk -> checkbox
     * dtp -> date picker
     * img -> image
     * lbl -> label
     * lst -> list box
     * mnu -> Menu
     * msg -> messages
     * rad -> radio button
     * tab -> tab
     * txt -> input form
     * tbl -> table
     * drpdwn -> dropdown
     ***************************/
```
Ex: The located element is input field, so the element name should be started with prefix txt.

## Build
### Local build
Build can be triggered from any athena-web-automation folder with the following command

```
mvn clean install
```
Or you can run all tests together from parent project.

### Running single test
Specify test you want to run by test parameter.

-Dbrowser=chrome/firefox (default: chrome)

-Dtest=TestSerialRunner (default: null means run all tests)

-DisHeadless=true (default: false)

-Dcucumber.filter.tags=@Test-1 && @Test-2 || @Test-3

-Dtimeout=15 (default: 10, unit: second)

-DpageLoadTimeout=10 (default: 5, unit: second)

```
mvn clean test allure:report -DisHeadless=true
```
Please note test suite name needs to be suffixed by Test not Tests. Otherwise it's not possible to run it separately.

### Debugging
Run test with Surefire plugin debug option.
Test execution should wait for attaching debugger to 5005 port. See example configuration of maven-debug inside IntelliJ IDEA.

## CI/CD
A maven project to run test is built on jenkins and is deployed to [jenkins-minh.herokuapp](https://jenkins-minh.herokuapp.com/job/seleniu-webdriver-cucumber/)

Warning: 
* login by user: admin and password: 721673bf483a4f63ac5e26beba7c8484
* job: https://jenkins-minh.herokuapp.com/job/seleniu-webdriver-cucumber/
* Please just view config for checking solution, DON'T TRIGGER job because it is deployed on free domain with limit memory.
It will be terminated immediately. 

By the way, we can build locally jenkins with [heroku-jenkins repository](https://github.com/mluu3/heroku-jenkins) 

## Report
### Cucumber - TestNG
Cucumber-TestNG report will be generated pretty html in ``target/cucumber-reports``, base on parse the JSON file.

### Allure - Cucumber JVM

Then execute ``mvn clean test allure:report`` goal. After tests executed allure JSON files will be placed in ``target/allure-results`` directory by default.
Report will be generated tо directory: ``target/allure-report/index.html``
[click here to see a report](https://jenkins-minh.herokuapp.com/job/seleniu-webdriver-cucumber/Allure_20Report/)

Warning:
* login by user: admin and password: 721673bf483a4f63ac5e26beba7c8484
* DON'T TRIGGER job.

    ![img.png](img.png)
Or, to generate a report from test result ``target/allure-result/``using one of the following command:
```
mvn allure:serve
```
Report will be generated into temp folder. Web server with results will start.

Additional information can be found [here](https://docs.qameta.io/allure/#_cucumber_jvm).
## Git workflow
This repository is switched to the standard gitflow model (as you are used to on other component repositories).

Summary:
* happy path: open PR to `develop` branch (for new features/tests or bugfixes related to `develop` stage)
* open PR to `master` branch in case of hot fixing tests on production
* following merge from the `master` to `develop` should be done (auto-merging is missing at the time of writing)
* merge of `develop` branch to `master` happens automatically before the production release

## Contributing:
The Selenium + Cucumber project welcomes contributions from everyone. There are a number of ways you can help:

### Bug Reports
When opening new issues or commenting on existing issues please make sure discussions are related to concrete technical issues with the project.

### Feature Requests
If you find that project is missing something or require a new script for new feature of Athena, feel free to open an issue with details describing what feature(s) you'd like added or changed.

If you'd like a hand at trying to implement the feature or test script yourself, please refer to the Code Contributions section of the document.

### Code Contributions
The Selenium + Cucumber project welcomes new contributors. Individuals making significant and valuable contributions over time are made Committers and given commit-access to the project.

This document will guide you through the contribution process.

Step 1: Fork
Fork the project on Github and check out your copy locally.

```
git clone git@github.com:<username>/selenium-webdriver-cucumber.git
cd selenium-webdriver-cucumber
git remote add upstream git@github.com:mluur/selenium-webdriver-cucumber.git
```
Step 2: Branch
Create a feature branch and start hacking:

```
git checkout -b <number ticket> upstream/develop
```
We practice HEAD-based development, which means all changes are applied directly on top of develop.

Step 3: Commit
First make sure git knows your name and email address:

```
git config --global user.name 'Minh Luu'
git config --global user.email 'ltqminh@gmail.com'
```
Writing good commit messages is important. A commit message should describe what changed, why, and reference issues fixed (if any). Follow these guidelines when writing one:

The first line should be around 50 characters or less and contain a short description of the change.
Keep the second line blank.
Include Prefix, number of ticket and description

Prefix:
* FEATURE: Implement new feature
* TEST: Implement test script
* TRIVIAL: minor change like change a hint or typo

A good commit message can look like this:
`git commit -m "FEATURE: AUAD-16 Impelement takescreenshot"`

Step 4: Rebase
Use git rebase (not git merge) to sync your work from time to time.

```
git fetch upstream
git rebase upstream/develop
```
Step 5: Test
Test script and features should have tests. Look at other tests to see how they should be structured.

Make sure test script doesn’t affect another and must be stable on both local and jenkins

Step 6: Push
```
git push origin <number ticket>
```
Go to https://github.com/eduloginc/athena-web-automation  and press the Pull Request and fill out the form.

Step 7: Integration
When code review is complete, a committer will take your PR and integrate it on Selenium's develop branch. Because we like to keep a linear history on the develop branch, we will normally squash and rebase your branch history.

Stages of a PR
The review labels (R) are:

* review
* merge
* close
