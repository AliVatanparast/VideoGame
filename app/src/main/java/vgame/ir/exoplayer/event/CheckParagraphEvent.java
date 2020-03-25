package vgame.ir.exoplayer.event;

public class CheckParagraphEvent {

    private long position;

    public CheckParagraphEvent(long position) {
        this.position = position;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
