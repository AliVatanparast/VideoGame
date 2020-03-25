package vgame.ir.data.model;

public class Paragraph {

    private String text;
    private String time;
    private long timeSec;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(long timeSec) {
        this.timeSec = timeSec;
    }
}
