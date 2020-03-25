package vgame.ir.exoplayer;

public class ExoEvent {

  public String downloded;
  public String percent;
  public String url;

  public ExoEvent(String downloded, String percent, String url) {
    this.downloded = downloded;
    this.percent = percent;
    this.url = url;
  }

  public String getDownloded() {
    return downloded;
  }

  public void setDownloded(String downloded) {
    this.downloded = downloded;
  }

  public String getPercent() {
    return percent;
  }

  public void setPercent(String percent) {
    this.percent = percent;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
