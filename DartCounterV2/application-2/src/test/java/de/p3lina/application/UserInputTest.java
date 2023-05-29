package de.p3lina.application;

import de.p3lina.application.user.UserInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class UserInputTest {

    @Test
    public void isValidDartValidCaseTest() {
        UserInput input = new UserInput("SBull");
        assertTrue(input.isValidDart(input));
    }

    @Test
    public void isValidDartInvalidCaseTest() {
        UserInput input = new UserInput("NotInPossibleDarts");
        assertFalse(input.isValidDart(input));
    }

    @Test
    public void prepareUserDartInputEmptyStringTest() {
        UserInput input = UserInput.prepareUserDartInput("");
        assertEquals("", input.toString());
    }

    @Test
    public void prepareUserDartInputZeroTest() {
        UserInput input = UserInput.prepareUserDartInput("0");
        assertEquals("Zero", input.toString());
    }

    @Test
    public void prepareUserDartInputNonDigitFirstCharacterTest() {
        UserInput input = UserInput.prepareUserDartInput("D12");
        assertEquals("D12", input.toString());
    }

    @Test
    public void prepareUserDartInputSBullTest() {
        UserInput input = UserInput.prepareUserDartInput("25");
        assertEquals("SBull", input.toString());
    }

    @Test
    public void prepareUserDartInputDBullTest() {
        UserInput input = UserInput.prepareUserDartInput("50");
        assertEquals("DBull", input.toString());
    }

    @Test
    public void prepareUserDartInputDefaultCaseTest() {
        UserInput input = UserInput.prepareUserDartInput("17");
        assertEquals("S17", input.toString());
    }
}
