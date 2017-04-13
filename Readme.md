My task is:
Create base automation framework and create several E2E scenarios for ‘IdeaBox’ - lohika’s internal project 
    * Create manual E2E scenario: standard ‘create idea’ flow (Gherkin way)
    * Create several negative scenario - Gherkin way 

  Done. Please check https://github.com/Yendvor/IdeaBoxTestUI.git
 Java + Webdriver(+PageObject) + Cucumber was used for this task.
 Framework is based on TestNG.

 What was done:
 1. All precondition steps are implemented via REST API, used RestAssured.
2. Implemented Logging to the qa-testing-log.txt.
3. Implemented Reporting:
	3.1 cucumber-reporting plugin in Jenkins
	3.2 cucumber-reporting CustomReporter (used for local runs).
4. Used maven for building and running tests: 	mvn clean test  5. Created docker file with Jenkins to run tests:
	* contains chrome&firefox browsers in docker
	* shared directory to store jenkins data for restarts
	* jenkins is available on localhost:8080 
	* tests on Jenkins are using headless run via Xvfb plugin
6. Environment tests will be run against is configured in Configuration.java.
   For now “dev”& “dev1” environments both are set to:
        http://mb-win7.vlab.lohika.com
7. Running tests via command line: 
    * run tests with firefox from local machine (Mac OS for my case):
         $ mvn test -Dbrowser=firefox -Drunner=local 
    * run tests with default env(dev), browser(chrome) and runner(jenkins):
         $ mvn test
	* run tests against environment “dev1”: 
	    $ mvn test -Denv=dev1 
8. Screenshots are added to final reports (in case of failures)
 9. Support for Chrome and FF (default is Chrome)
10. Rerun failed test is implemented via custom listener.
   If we need to rerun failed test it is configured in testNG.xml. 
   <listeners>
          <listener class-name="com.ideabox.tests.utils.RetryListener"/>
  	</listeners>
 For now retry number is set to 2 times.
