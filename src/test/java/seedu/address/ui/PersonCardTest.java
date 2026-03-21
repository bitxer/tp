package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.TeachingStaff;
import seedu.address.model.person.TimeSlot;
import seedu.address.model.person.Username;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Tests {@link PersonCard}. Requires JavaFX toolkit (initialized once per JVM).
 */
public class PersonCardTest {

    @BeforeAll
    public static void initJfx() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Toolkit already started by another test
        }
    }

    @Test
    public void constructor_student_displaysPerson() {
        Person person = new PersonBuilder().withName("Amy").build();
        PersonCard card = new PersonCard(person, 3);
        assertEquals(person, card.person);
    }

    @Test
    public void constructor_teachingStaffWithoutSlots_displaysPosition() {
        Person person = new PersonBuilder().withName("Ben").withPosition(Position.PROFESSORS).build();
        PersonCard card = new PersonCard(person, 1);
        assertEquals(person, card.person);
    }

    @Test
    public void constructor_teachingStaffWithAvailability_displaysSlots() {
        Name name = new Name("Carol");
        Phone phone = new Phone("81234567");
        Email email = new Email("carol@example.com");
        Username username = new Username("carol");
        Position position = new Position(Position.TEACHING_ASSISTANT);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("friends"));
        Set<TimeSlot> slots = Set.of(new TimeSlot("mon-10-12"));
        TeachingStaff staff = new TeachingStaff(name, phone, email, username, position, tags, slots);
        PersonCard card = new PersonCard(staff, 2);
        assertEquals(staff, card.person);
    }

    @Test
    public void constructor_withTags_displaysTagLabels() {
        Person person = new PersonBuilder().withName("Dan").withTags("alpha", "beta").build();
        PersonCard card = new PersonCard(person, 5);
        assertEquals(person, card.person);
    }
}
