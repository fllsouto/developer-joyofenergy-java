debug:
	@echo "hello world"

app.tasks:
	gradle tasks

app.build:
	gradle build

app.run:
	java -jar build/libs/developer-joyofenergy-java.jar

app.test:
	gradle test

app.functionalTest:
	gradle functionalTest

app.check:
	gradle check

app.bootRun:
	gradle bootRun

