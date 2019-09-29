@rem see http://pitest.org/quickstart/maven/
call mvn test-compile org.pitest:pitest-maven:mutationCoverage %*
