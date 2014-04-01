1 Ant Schematron Demo

This small project shows the usage of Ant Schematron two perform a schematron validation on XML files.

1.1 Usage
A demo Schematron file (src/main/resources/sample-schematron.sch) and a XML file to be validated (src/main/resources/sample-file-to-validate.xml) is shipped with the project.

To test the validation simply run:

```
runDemo
```

In order to perform a validation on your own XMLs using a custom Schematron File just call:

```
runDemo path/to/schematron.sch path/to/file-to-validate.xml
```

To work with the code use gradlew to generate project files for IntelliJ or Eclipse by calling:
```
gradlew idea

gradlew eclipse
```