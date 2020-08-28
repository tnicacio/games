package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import java.util.Random;
import userinterface.GameWindow;
import util.Animation;
import util.Resource;

public class Birds extends Enemy {

    private int posX;
    private int posY;
    private int width;
    private int height;

//	private BufferedImage image;
    private MainCharacter mainCharacter;
    private Animation birdFlap;
    Random random = new Random();
    private Rectangle rectBound;

    public Birds(MainCharacter mainCharacter, int posX, int posY, int width, int height, BufferedImage image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
//		this.image = image;
        this.mainCharacter = mainCharacter;
        rectBound = new Rectangle();
        birdFlap = new Animation(90);
        birdFlap.addFrame(Resource.getResouceImage("data/BFrame1.png"));
        birdFlap.addFrame(Resource.getResouceImage("data/BFrame2.png"));
    }

    @Override
    public void update() {
        birdFlap.updateFrame();
        posX -= mainCharacter.getSpeedX() * 1.5;
        posY += (Math.sin(posX / 60) * 3) + 0.6;
//        int i = random.nextInt(3);
//        switch (i) {
//            case 0:
//                posY += (Math.asin(posX / 300) + Math.cos(posX / 30) * 3) + 1;
//                break;
//            case 1:
//                posY += (Math.asin(posX / 300) + Math.sin(posX / 60) * 2) + 1;
//                break;
//            default:
//                posY += (Math.sin(posX / 60) * 3) + 1;
//                break;
//        }
    }

    public void draw(Graphics g) {
        g.drawImage(birdFlap.getFrame(), posX, posY + birdFlap.getFrame().getHeight(), null);
//        g.setColor(Color.RED);
//        g.drawRect(rectBound.x, rectBound.y, rectBound.width, rectBound.height);
    }

    public Rectangle getBound() {
        rectBound = new Rectangle();
        rectBound.x = (int) posX + (birdFlap.getFrame().getWidth() - width) / (2 + (mainCharacter.score / 100));
        rectBound.y = (int) posY + birdFlap.getFrame().getHeight();
        rectBound.width = width;
        rectBound.height = height;
        return rectBound;
    }

    @Override
    public boolean isOutOfScreen() {
        if (posX < -birdFlap.getFrame().getWidth()) {
            return true;
        }
        return false;
    }

}
