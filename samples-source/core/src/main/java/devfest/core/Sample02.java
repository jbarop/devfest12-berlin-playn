package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

/**
 * Presentation Sample 02.
 * 
 * @author <a href="mailto:jb@barop.de">Johannes Barop</a>
 * 
 */
public class Sample02 implements Game {

  @Override
  public void init() {
    // add background
    Image bgImage = assets().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    // add cloud
    Image cloudImage = assets().getImage("images/cloud.png");
    ImageLayer cloudLayer = graphics().createImageLayer(cloudImage);
    graphics().rootLayer().add(cloudLayer);
  }

  @Override
  public void paint(final float alpha) {
  }

  @Override
  public void update(final float delta) {
  }

  @Override
  public int updateRate() {
    return 25;
  }

}
