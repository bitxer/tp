package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Adds tag(s) to an existing Person
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "tag-add";

    //@formatter:off
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Attaches a tag to a person within the address book. "
        + "Parameters: "
        + "INDEX "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + "3 "
        + PREFIX_TAG + "friends "
        + PREFIX_TAG + "owesMoney";
    //@formatter:on

    public static final String MESSAGE_ADD_SUCCESS = "Successfully added tag(s) to Person: %1$s";
    public static final String MESSAGE_WARNING_DUPLICATE = "WARNING, the following tags were ignored as they already exist: %1$s";

    private Index targetPersonIndex;
    private Set<Tag> tagsToAdd;

    /**
     * @param index of the person in the filtered person list to edit
     * @param tags  a set of tag(s) to be added to the person located at index
     */
    public AddTagCommand(Index targetPersonIndex, Set<Tag> tags) {
        this.targetPersonIndex = targetPersonIndex;
        this.tagsToAdd = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetPersonIndex.getZeroBased());
        var duplicatedTags = findDuplicateTags(personToEdit.getTags());
        var newTags = appendTags(personToEdit.getTags());

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newTags);

        model.setPerson(personToEdit, editedPerson);

        var outMessage = String.format(MESSAGE_ADD_SUCCESS, Messages.format(editedPerson))
                + formatDuplicateTagMessage(duplicatedTags);
        return new CommandResult(outMessage);
    }

    /**
     * Formats a message to inform the user about duplicate tags that were ignored
     * during addition. If no duplicates exist, returns an empty string.
     *
     * @param duplicates List of duplicate tags that were already present in the
     *                   person's tags.
     * @return A formatted message string indicating the ignored duplicate tags.
     */
    private String formatDuplicateTagMessage(List<Tag> duplicates) {
        if (duplicates.size() == 0) {
            return "";
        }
        var sb = new StringBuilder();
        duplicates.stream().forEach(sb::append);
        return "\n" + String.format(MESSAGE_WARNING_DUPLICATE, sb.toString());
    }

    /**
     * Identifies which tags from the tagsToAdd set already exist in the person's
     * current tags. This helps determine which tags will be ignored during addition
     * (as they are duplicates).
     *
     * @param existing The set of tags currently assigned to the existing person.
     * @return A list of duplicate tags that are already present in the person's
     *         tags.
     */
    private List<Tag> findDuplicateTags(Set<Tag> existing) {
        return existing.stream().filter(t -> tagsToAdd.contains(t)).toList();
    }

    /**
     * Creates a new set of tags by combining the existing tags with the new tags to
     * be added.
     *
     * @param existing The set of tags currently assigned to the existing person.
     * @return A new set containing all tags from both existing and new tags, with
     *         duplicates removed.
     */
    private Set<Tag> appendTags(Set<Tag> existing) {
        var newTags = new HashSet<>(existing);
        newTags.addAll(tagsToAdd);
        return newTags;
    }

}
