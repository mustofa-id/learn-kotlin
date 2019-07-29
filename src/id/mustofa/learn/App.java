package id.mustofa.learn;

public class App {

    public String myName() {
        return "Habib Mustofa";
    }

    public Person getPerson() {
        return new Person(24, 1993);
    }

    private void printTag() {
        Main.printTag();
        Main.printTag("App#printTag()");
    }
}
