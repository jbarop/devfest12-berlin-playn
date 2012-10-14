package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

/**
 * Presentation Sample 04.
 * 
 * @author <a href="mailto:jb@barop.de">Johannes Barop</a>
 * 
 */
public class Sample04 implements Game {

  class Ball {
    final public ImageLayer layer;

    public Ball(final Image image, final float x, final float y) {
      layer = graphics().createImageLayer(image);
      layer.setOrigin(image.width() / 2f, image.height() / 2f);
      layer.setTranslation(x, y);
    }
  }

  Image bgImage;

  float couldX;
  float couldY;
  Image cloudImage;
  ImageLayer cloudLayer;

  Image ballImage;
  GroupLayer ballLayer;

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

    // add bals on click
    ballImage = assets().getImage("images/ball.png");
    ballLayer = graphics().createGroupLayer();
    graphics().rootLayer().add(ballLayer);
    pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(final Pointer.Event event) {
        Ball ball = new Ball(ballImage, event.x(), event.y());
        ballLayer.add(ball.layer);
      }
    });
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
