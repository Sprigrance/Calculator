public class MyExceptions extends Exception {

    public MyExceptions() {
        System.out.println("Что-то не в порядке");
    }

    public MyExceptions(String reason) {
        System.out.println(reason);
    }
}
