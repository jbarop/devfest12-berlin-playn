package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

/**
 * Presentation Sample 03.
 * 
 * @author <a href="mailto:jb@barop.de">Johannes Barop</a>
 * 
 */
public class Sample03 implements Game {

  Image bgImage;

  float couldX;
  float couldY;
  Image cloudImage;
  ImageLayer cloudLayer;

  @Override
  public void init() {
    // add background
    bgImage = assets().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    // add cloud
    cloudImage = assets().getImage("images/cloud.png");
    cloudLayer = graphics().createImageLayer(cloudImage);
    graphics().rootLayer().add(cloudLayer);
    couldX = 30.0f;
    couldY = 10.0f;
    cloudLayer.setTranslation(couldX, couldY);
  }

  @Override
  public void paint(final float delta) {
    // animate the cloud
    couldX += delta;
    if (couldX > bgImage.width() - cloudImage.width()) {
      couldX = -cloudImage.width();
    }
    cloudLayer.setTranslation(couldX, couldY);
  }

  @Override
  public void update(final float delta) {
  }

  @Override
  public int updateRate() {
    return 25;
  }

}
