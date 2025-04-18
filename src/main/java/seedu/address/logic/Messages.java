package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid! The "
            + "maximum index is %d";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; DOB: ")
                .append(person.getDateOfBirth())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Blood Type: ")
                .append(person.getBloodType())
                .append("; Appointment: ")
                .append(person.getAppointment());
        nokBuilder(builder, person);
        tagBuilder(builder, person);
        medicalHistoryBuilder(builder, person);
        return builder.toString();
    }

    private static StringBuilder nokBuilder(StringBuilder builder, Person person) {
        builder.append("; Next of Kin: ");
        if (person.getNextOfKin() == null) {
            builder.append("No next of kin");
        } else {
            builder.append(person.getNextOfKin());
        }
        return builder;
    }

    private static StringBuilder tagBuilder(StringBuilder builder, Person person) {
        builder.append("; Tags: ");
        if (person.checkIfTagsIsEmpty()) {
            builder.append("No tags");
        } else {
            person.getTags().forEach(builder::append);
        }
        return builder;
    }

    private static StringBuilder medicalHistoryBuilder(StringBuilder builder, Person person) {
        builder.append("; Medical History: ");
        if (person.checkIfMedicalHistoryIsEmpty()) {
            builder.append("No medical history");
        } else {
            person.getMedicalHistory().forEach(builder::append);
        }
        return builder;
    }

}
