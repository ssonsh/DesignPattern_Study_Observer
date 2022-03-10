public class Main {
    public static void main(String[] args) {

        Button button = new Button();
        button.onClickListener(new ButtonClickListener());

        button.action();


        Button newButton = new Button();
        newButton.onClickListener(new ClickListener() {
            @Override
            public void click() {
                System.out.println("new button ClickListener");
            }
        });

        newButton.action();

        Button newnewButton = new Button();
        newnewButton.onClickListener(() -> System.out.println("new new button ClickListener"));

        newnewButton.action();
    }
}
