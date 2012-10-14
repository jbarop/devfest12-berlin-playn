package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

/**
 * Presentation Sample 06.
 * 
 * @author <a href="mailto:jb@barop.de">Johannes Barop</a>
 * 
 */
public class Sample06 implements Game {

  class Ball {
    final public ImageLayer layer;
    final public Body body;

    public Ball(final Image image, final float x, final float y) {
      float radius = 0.5f;
      layer = graphics().createImageLayer(image);
      layer.setOrigin(image.width() / 2f, image.height() / 2f);
      layer.setTranslation(x, y);
      layer.setScale((radius * 2) / image.width(), (radius * 2) / image.height());

      // init ball physics
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DYNAMIC;
      bodyDef.position = new Vec2(x, y);
      body = world.createBody(bodyDef);
      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.shape = new CircleShape();
      fixtureDef.shape.m_radius = radius;
      fixtureDef.density = 1.0f;
      fixtureDef.friction = 0.3f;
      body.createFixture(fixtureDef);
      body.setTransform(new Vec2(x, y), 0.0f);
    }

    public void paint(final float delta) {
      layer.setTranslation(body.getPosition().x, body.getPosition().y);
      layer.setRotation(body.getAngle());
    }
  }

  Image bgImage;

  float couldX;
  float couldY;
  Image cloudImage;
  ImageLayer cloudLayer;

  Image ballImage;
  GroupLayer ballLayer;
  ArrayList<Ball> balls = new ArrayList<Ball>();

  final float physUnitPerScreenUnit = 1 / 26.666667f;
  World world;

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
    ballLayer.setScale(1f / physUnitPerScreenUnit);
    graphics().rootLayer().add(ballLayer);
    pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(final Pointer.Event event) {
        Ball ball = new Ball(ballImage, event.x() * physUnitPerScreenUnit, event.y() * physUnitPerScreenUnit);
        balls.add(ball);
        ballLayer.add(ball.layer);
      }
    });

    // init physics
    world = new World(new Vec2(0.0f, 10.0f), true);
    world.setWarmStarting(true);
    world.setAutoClearForces(true);

    // add bounds
    float physWidth = physUnitPerScreenUnit * 640;
    float physHeight = physUnitPerScreenUnit * 480;
    Body ground = world.createBody(new BodyDef());

    PolygonShape bottom = new PolygonShape();
    bottom.setAsEdge(new Vec2(0, physHeight), new Vec2(physWidth, physHeight));
    ground.createFixture(bottom, 0.0f);

    PolygonShape top = new PolygonShape();
    top.setAsEdge(new Vec2(0, 0), new Vec2(physWidth, 0));
    ground.createFixture(top, 0.0f);

    PolygonShape left = new PolygonShape();
    left.setAsEdge(new Vec2(0, 0), new Vec2(0, physHeight));
    ground.createFixture(left, 0.0f);

    PolygonShape right = new PolygonShape();
    right.setAsEdge(new Vec2(physWidth, 0), new Vec2(physWidth, physHeight));
    ground.createFixture(right, 0.0f);
  }

  @Override
  public void paint(final float delta) {
    // animate the cloud
    couldX += delta;
    if (couldX > bgImage.width() - cloudImage.width()) {
      couldX = -cloudImage.width();
    }
    cloudLayer.setTranslation(couldX, couldY);
    for (Ball ball : balls) {
      ball.paint(delta);
    }
  }

  @Override
  public void update(final float delta) {
    world.step(0.033f, 10, 10);
  }

  @Override
  public int updateRate() {
    return 25;
  }

}
