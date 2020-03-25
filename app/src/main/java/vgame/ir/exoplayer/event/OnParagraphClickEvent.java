package vgame.ir.exoplayer.event;

public class OnParagraphClickEvent {

    private long position;

    public OnParagraphClickEvent(long position) {
        this.position = position;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
