public class Button {
    private ClickListener clickListener;

    public void onClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void action(){
        this.clickListener.click();
    }
}
