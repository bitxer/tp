package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AnswerConfirmationCommand extends Command {

    public static final String COMMAND_WORD_YES = "Y";
    public static final String COMMAND_WORD_NO = "N";

    public static final String MESSAGE_UNKNOWN_ANSWER = """
            Unknown answer: %s.
            Expected answers are:
                %s (for yes)
                %s (for no)
            """;
    public static final String MESSAGE_COMMAND_CANCELLED =
            "Command Cancelled!";

    private final AnswerType answerType;

    public AnswerConfirmationCommand(AnswerType answerType) {
        this.answerType = answerType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_COMMAND_CANCELLED);
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public enum AnswerType {
        YES,
        NO
    }
}
