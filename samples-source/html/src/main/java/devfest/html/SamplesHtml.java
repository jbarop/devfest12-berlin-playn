package devfest.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;
import devfest.core.Samples;

public class SamplesHtml extends HtmlGame {

  private final Samples samples = new Samples();

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("samples/");
    PlayN.run(samples);
    exportSetCurrentSample(samples);
  }

  private native void exportSetCurrentSample(Samples samples) /*-{
    $wnd.setCurrentSample = function(sample) {
      return samples.@devfest.core.Samples::setCurrentSample(I)(sample);
    };
  }-*/;

}
