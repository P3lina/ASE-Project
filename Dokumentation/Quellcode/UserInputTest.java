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