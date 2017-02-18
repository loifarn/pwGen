public class logic {
    private String password = "";
    private String specialCharacters[] = {"@", "~", "\\", "/", "+", "-", "*", "(", "=", ")", "{", "}"};

    public void pwGenerator(int length, boolean numbers, boolean sChars) {

        for (int i = 0; i < 10 ; i++) {
            System.out.print(specialCharacters[i]);
        }

        if (numbers) {
            password = password+Integer.toString((int)(Math.random()*9));
        }

        if (sChars) {
            password = password+Integer.toString((int)(Math.random()*9));
        }

        for (int i = 0; i < length ; i++) {
            //password = password+Integer.toString((int)(Math.random()*9));
        }

        System.out.print(password);

    }
}
