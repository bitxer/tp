package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.restricted.RestrictedTag;
import seedu.address.model.tag.restricted.TagSchema;
import seedu.address.model.tag.restricted.TutorialTagSchema;

public class TagFactory {
    public static final String UNKNOWN_SCHEMA_MESSAGE = "Specified Restricted Tag of prefix %s is unknown";

    private static TagSchema getAssociatedSchema(String prefix) {
        return switch (prefix) {
            case TutorialTagSchema.VARIANT -> new TutorialTagSchema();
            default -> throw new IllegalArgumentException(String.format(UNKNOWN_SCHEMA_MESSAGE, prefix));
        };
    }

    public static AbstractTag create(String tag) throws IllegalArgumentException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (RestrictedTag.isRestrictedTag(trimmedTag)) {
            var schema = getAssociatedSchema(trimmedTag);
            return new RestrictedTag(schema, tag);
        }

        if (!Tag.isValidTagName(trimmedTag)) {
            throw new IllegalArgumentException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }
}
