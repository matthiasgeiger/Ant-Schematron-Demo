package demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

import com.schematron.ant.SchematronResult;
import com.schematron.ant.Validator;
import com.schematron.ant.ValidatorFactory;

public class SimpleValidator {

	public static final String VALIDATOR_FACTORY_PREPROCESSOR_XSLT2 = "xslt2";
	public static final String VALIDATOR_FACTORY_PREPROCESSOR_XSLT1 = "xslt";
	public static final String VALIDATOR_FACTORY_PREPROCESSOR_OLD = "old";

	public static final String VALIDATOR_FACTORY_FORMATTER_MESSAGE = "message";
	public static final String VALIDATOR_FACTORY_FORMATTER_TERMINATE = "terminate";
	public static final String VALIDATOR_FACTORY_FORMATTER_SVRL = "svrl";

	public static void performValidationOfDefaultFile() {

		Path pathToResourcesFolder = Paths.get("src").resolve("main")
				.resolve("resources");

		File sampleSchematronFile = pathToResourcesFolder.resolve(
				"sample-schematron.sch").toFile();

		File sampleFileToValidate = pathToResourcesFolder.resolve(
				"sample-file-to-validate.xml").toFile();

		printResultsOnConsolte(performValidation(sampleSchematronFile,
				sampleFileToValidate));

	}

	public static SchematronResult performValidation(File schematronFile,
			File fileToValidate) {
		// Create ValidatorFactory - the preprocessor XSLT version and the
		// formatter can be defined here
		// Preprocessor: XSLT1 is the default - to change to XSLT2 use
		// VALIDATOR_FACTORY_XSLT2 as first param
		// Formatter: svrl is the default - alternatives are
		// VALIDATOR_FACTORY_FORMATTER_MESSAGE and
		// VALIDATOR_FACTORY_FORMATTER_TERMINATE (Caution: not yet implemented
		// in ant-schematron-2010-04-14.jar - MESSAGE is used instead)
		ValidatorFactory factory = new ValidatorFactory(
				VALIDATOR_FACTORY_PREPROCESSOR_XSLT2,
				VALIDATOR_FACTORY_FORMATTER_SVRL);

		Source schematronSource = new StreamSource(schematronFile);

		try {
			Validator validator = factory.newValidator(schematronSource);

			Source sourceToValidate = new StreamSource(fileToValidate);

			// Parameter documentation is not the best in ant schematron:
			// mandatory parameter: Source xml - the XML to be validated
			// The following parameter were used to configure the
			// javax.xml.transform.Transformer object used internally - all
			// params can be null:
			// String fnp - file name which can be provided
			// String fdp - file path which can be provided
			// String anp - "archiveNameParameter"
			// String adp - "archiveDirParameter"
			// String encoding - encoding of the file
			try {
				return validator.validate(sourceToValidate, null, null, null,
						null, null);
			} catch (TransformerException e) {
				// only dummy exception handling
				System.out.println("Schematron validation of file failed:");
				e.printStackTrace();
			}
		} catch (InstantiationException | TransformerException
				| ClassNotFoundException | IllegalAccessException | IOException e) {
			// only dummy exception handling
			System.out.println("Validator creation failed:");
			e.printStackTrace();
		}

		return null;
	}

	public static void printResultsOnConsolte(SchematronResult result) {
		System.out.println("Is the file valid? " + result.isValid());
		System.out.println("Print failedMessages: "
				+ formatFailedMessages(result.getFailedMessage()));

		System.out.println("Print result as SVRL String:");
		System.out.println(result.getSVRLAsString());
	}

	private static String formatFailedMessages(String failedMessages) {
		return failedMessages.replaceAll("\\[assert\\]", "\n[assert]");
	}

}
