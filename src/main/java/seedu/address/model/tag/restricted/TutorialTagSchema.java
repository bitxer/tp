package seedu.address.model.tag.restricted;

public class TutorialTagSchema extends RegexTagSchema {
    public static final String VARIANT = "tut";
    public static final String TAG_PATTERN = "[A-Z]*\\d{1,2}";
    public static final String MESSAGE_CONSTRAINTS = "Tutorial tag expects format of uppercase letter followed by maximum 2 numbers. Valid: 'D24' and '8'";

    public TutorialTagSchema() {
        super(VARIANT, TAG_PATTERN);
    }

    @Override
    public String getConstraintViolationMessage() {
        return MESSAGE_CONSTRAINTS;
    }
}
