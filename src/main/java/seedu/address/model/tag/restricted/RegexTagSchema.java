package seedu.address.model.tag.restricted;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

public abstract class RegexTagSchema extends TagSchema {
    protected final Pattern pattern;

    public RegexTagSchema(String variant, String pattern) {
        super(variant);
        requireNonNull(pattern);
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean isTagValid(String tag) {
        return pattern.matcher(tag).matches();
    }
}
