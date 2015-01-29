package img;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

public class ObjectDetection extends Applet{
	
      
	Image img;
	
	BufferedImage bimg; //入力画像

	HoughTransformCircles htf;
	
	int w,h;
	int c;
	
	public void init(){

		img = getImage(getCodeBase(),"./pic/写真.JPG");
					
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img,0);
								
		try{
			mt.waitForAll();
		} catch (InterruptedException e){
			
		}
		
		bimg = createBufferedImage(img); //ImageがらBufferedImageへ変換
				
		w = bimg.getWidth();
		h = bimg.getHeight();
		
		main();
	}
	
	public void main(){
		 int[] c = new int [w*h];    //入力画像の画素値を格納する配列
		 int[] new_c = new int[w*h]; //処理した画素値を格納する配列
		 
		//入力画像の画素値を配列で取得
		c = bimg.getRGB(0,0,w,h,null,0,w);

		/*エッジを検出しやすくする
		new_c = translateRGB.ToneCurve(c, w, h);
		*/
		
		/*カラー画像をグレイ化する*/
		new_c = translateRGB.ToGlay(new_c,w,h);
	
		/*エッジ検出
		new_c = CreateLine.sobelFilter(new_c,w,h);
		*/
		
		/*円を検出
		htf = new HoughTransformCircles();
		htf.run(new_c, w, h);
		*/
	}
	
	
	
	/*Image -> BufferedImageするメソッド
	 *  Graphicsを経由してコピーする。
	 */
	public static BufferedImage createBufferedImage(Image img){   
		BufferedImage bimg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
    	Graphics g = bimg.getGraphics();
    	g.drawImage(img, 0, 0, null);
    	g.dispose();
    	return bimg;
	}
	
	public void paint(Graphics g){
		int x,y;
		g.drawImage(img, 0, 0, this);
		
		/*円検出の結果
		g.setColor(Color.red);		
		for(int i = 0;i <htf.OBJECT_NUM;i++){
			x = htf.pre_c_x[i];
			y = htf.pre_c_y[i];
			g.fillOval(x,y,5,5);
		}
		*/
		
	}
}
