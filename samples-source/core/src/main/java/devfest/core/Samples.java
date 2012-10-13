package devfest.core;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import playn.core.Game;

/**
 * A dispatcher to the actual samples.
 * 
 * <p>
 * This allows us to serve several PlayN games with a single GWT application which is embedded in
 * the presentation.html
 * </p>
 * 
 * @author <a href="mailto:jb@barop.de">Johannes Barop</a>
 * 
 */
public class Samples implements Game {

  /**
   * Array of all samples.
   */
  private final Game[] samples = {
      new Sample01(), new Sample02()
  };

  /**
   * The samples which is currently executed.
   * 
   * -1 means no running sample.
   */
  private int currentSample = -1;

  /**
   * Set the sample which should be executed.
   */
  public void setCurrentSample(final int currentSample) {
    log().info("setCurrentSample('" + currentSample + "')");

    this.currentSample = currentSample;
    init();
  }

  @Override
  public void init() {
    log().info("init() - " + currentSample);

    if (validCurrentSample()) {
      graphics().rootLayer().clear();
      samples[currentSample].init();
    }
  }

  @Override
  public void paint(final float alpha) {
    if (validCurrentSample()) {
      samples[currentSample].paint(alpha);
    }
  }

  @Override
  public void update(final float delta) {
    if (validCurrentSample()) {
      samples[currentSample].update(delta);
    }
  }

  @Override
  public int updateRate() {
    if (validCurrentSample()) {
      return samples[currentSample].updateRate();
    } else {
      return 25;
    }
  }

  /**
   * @return If {@link #currentSample} points to a valid sample in {@link #samples}.
   */
  private boolean validCurrentSample() {
    return currentSample >= 0 && currentSample < samples.length;
  }

}
