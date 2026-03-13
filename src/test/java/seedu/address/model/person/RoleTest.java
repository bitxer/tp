package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid roles
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("-")); // hyphens not allowed
        assertFalse(Role.isValidRole("TA@CS")); // @ not allowed
        assertFalse(Role.isValidRole("TA#1")); // hash not allowed
        assertFalse(Role.isValidRole("TA!")); // exclamation not allowed
        assertFalse(Role.isValidRole("TA_CS")); // underscores not allowed
        assertFalse(Role.isValidRole("TA/CS")); // slashes not allowed
        assertFalse(Role.isValidRole(" Teaching")); // leading space not allowed

        // valid roles
        assertTrue(Role.isValidRole("Teaching Assistant")); // alphanumeric with spaces
        assertTrue(Role.isValidRole("T")); // one character
        assertTrue(Role.isValidRole("1")); // single digit
        assertTrue(Role.isValidRole("Professor of Computer Science")); // long role
        assertTrue(Role.isValidRole("TA2")); // alphanumeric no spaces
        assertTrue(Role.isValidRole("Level 3 Tutor")); // digits mixed with words
    }

    @Test
    public void equals() {
        Role role = new Role("Teaching Assistant");

        // same values -> returns true
        assertTrue(role.equals(new Role("Teaching Assistant")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("Professor")));
    }
}
