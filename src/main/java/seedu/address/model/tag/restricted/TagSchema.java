package seedu.address.model.tag.restricted;

public abstract class TagSchema {
    protected final String variant;

    public TagSchema(String variant) {
        this.variant = variant;
    }

    public String getVariant() {
        return variant;
    }

    public abstract boolean isTagValid(String tag);

    public abstract String getConstraintViolationMessage();
}
