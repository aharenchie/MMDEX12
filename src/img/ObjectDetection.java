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
	BufferedImage new_img;//出力画像
	
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
			
		//出力画像のために、新しいBufferedImageを生成
		new_img= new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		/*カラー画像をグレイ化する*/
		c = translateRGB.ToGlay(c,w,h);
		
		//c = translateRGB.ToneCurve(c, w, h);
		/*エッジ検出*/
		//c = CreateLine.sobelFilter(c,w,h);		
		
		new_c = HoughTransform.run(c, w, h);
		
		new_c = c;
		//計算結果をセットする
		new_img.setRGB( 0,0, w, h, new_c, 0, w ); 
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
		g.drawImage(new_img, 0, 0, this);
		g.setColor(Color.blue);
		/*
		g.drawOval(522-23,228-23,23*2,23*2);
		g.drawOval(564-29,564-29,29*2,29*2);
		
		g.drawOval(334-28,334-28,28*2,28*2);
		g.drawOval(354-28,212-28,28*2,28*2);
	*/
		g.drawOval(522-22,229-22,22*2,22*2);
		g.drawOval(564-29,563-29,29*2,29*2);
		
	
	}
}
