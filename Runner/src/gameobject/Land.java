package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import userinterface.GameWindow;

import util.Resource;

public class Land {
	
	public static final int LAND_POSY = GameWindow.SCREEN_HEIGHT - 103;
	
	private List<ImageLand> listLand;
	private BufferedImage imageLand1;
	private BufferedImage imageLand2;
	private BufferedImage imageLand3;
        Random random = new Random();
	
	private MainCharacter mainCharacter;
	
	public Land(int width, MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
		imageLand1 = Resource.getResouceImage("data/land1.png");
		imageLand2 = Resource.getResouceImage("data/land2.png");
		imageLand3 = Resource.getResouceImage("data/land3.png");
		int numberOfImageLand = width / imageLand1.getWidth() + 2;
		listLand = new ArrayList<>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = i * imageLand1.getWidth();
			imageLand.image = getImageLand();
			listLand.add(imageLand);
		}
	}
	
	public void update(){
		for(ImageLand imageLand : listLand){
                    imageLand.posX -= mainCharacter.getSpeedX();
                }
             
                ImageLand firstElement = listLand.get(0);
                if (firstElement.posX + imageLand1.getWidth() < 0){
                    firstElement.posX = listLand.get(listLand.size() - 1).posX + imageLand1.getWidth();
                    listLand.add(firstElement);
                    listLand.remove(0);
                }                
                
	}
        
	public void draw(Graphics g) {
		for(ImageLand imgLand : listLand) {
			g.drawImage(imgLand.image, (int) imgLand.posX, LAND_POSY, null);
		}
	}
        
        private BufferedImage getImageLand() {
        int i = random.nextInt(3);
            switch(i){
                case 0: return imageLand1;
                case 1: return imageLand2;
                default: return imageLand3;
            }
        }
	
	private class ImageLand {
		float posX;
		BufferedImage image;
	}
	
}
