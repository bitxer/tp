package seedu.address.model.tag.restricted;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

import seedu.address.model.tag.AbstractTag;
import seedu.address.model.tag.TagType;

public class RestrictedTag extends AbstractTag {
    public static final String MESSAGE_CONSTRAINTS = "Attempted to create RestrictedTag with missing delimiter ':'";
    public static final String DELIMITER = ":";

    private final TagSchema schema;

    public RestrictedTag(TagSchema schema, String tag) {
        super(tag);
        var p = TagParts.parse(tag);
        checkArgument(p.isEmpty(), MESSAGE_CONSTRAINTS);

        var parts = p.get();
        checkArgument(schema.isTagValid(parts.prefix), schema.getConstraintViolationMessage());
        this.schema = schema;
    }

    @Override
    public TagType getTagType() {
        return TagType.TAG;
    }

    public static boolean isRestrictedTag(String test) {
        return test.contains(DELIMITER);
    }

    private static class TagParts {
        public final String prefix;
        public final String value;

        public TagParts(String prefix, String value) {
            this.prefix = prefix;
            this.value = value;
        }

        public static Optional<TagParts> parse(String tag) {
            var res = tag.split(DELIMITER, 2);
            if (res.length != 2) {
                return Optional.empty();
            }
            return Optional.of(new TagParts(res[0], res[1]));
        }
    }
}
