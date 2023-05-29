@BeforeEach
    public void setup() {
        userCommunicationService = new UserCommunicationService();
    }

@Test
public void getUserInputTest() {
    String testInput = "T20\nS20\nD20\n";
    InputStream in = new ByteArrayInputStream(testInput.getBytes());

    System.setIn(in);

    ScannerStore.setScanner(new Scanner(System.in));


    String[] input = testInput.split("\n");
    for(String inputLine : input) {
        UserInput userInput = userCommunicationService.getUserInput();
        assertEquals(inputLine, userInput.toString());
    }


    System.setIn(System.in);
}