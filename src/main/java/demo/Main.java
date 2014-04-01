package demo;

import java.io.File;
import java.nio.file.Paths;

import com.schematron.ant.SchematronResult;

public class Main {

	/**
	 * @param args
	 *            Arguments can be empty to perform the validation of the
	 *            default files (validating sample-file-to-validate.xml with schematron file sample-schematron.sch) or two
	 *            arguments can be used to define the schematron file (args[0])
	 *            which should be used to perform an analysis on a given file
	 *            (args[1])
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out
					.println("No arguments given - performing default validation.");
			SimpleValidator.performValidationOfDefaultFile();
		} else if (args.length != 2) {
			System.out
					.println("Invalid usage. Either call the program without "
							+ "an argument to perform the default validation "
							+ "or use exactly two arguments:");
			System.out.println("args[0]: path to schematron file");
			System.out.println("args[1]: path to file to validate");
		} else {
			File schematronFile = Paths.get(args[0]).toFile();
			File fileToValidate = Paths.get(args[1]).toFile();

			if (schematronFile.exists() && schematronFile.isFile()) {
				if (fileToValidate.exists() && fileToValidate.isFile()) {
					SchematronResult result = SimpleValidator
							.performValidation(schematronFile, fileToValidate);

					SimpleValidator.printResultsOnConsolte(result);
				} else {
					System.out
							.println("File to validate (args[1]) does not exist or is not a file.");
				}
			} else {
				System.out
						.println("Schematron file (args[0]) does not exist or is not a file.");
			}
		}
	}

}
