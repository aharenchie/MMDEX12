package img;

public class AccessControl {
	
	/*画素値の範囲が0~255になるように制御するメソッド*/
	public static int getColor(int c){
		
		if(c < 0)
			c = 0;
		if(c > 255)
			c = 255;
		
		return c;
	}
	
	/*配列のアクセスが配列外に行かないように制御するメソッド*/
	public static int getPosition(int w,int h,int x,int y){
		
		if(x < 0)
			x = 0;
		if (x > w-1)
			x = w-1;
		
		if(y < 0)
			y = 0;
		if (y > h-1)
			y = h-1;
		    
		int p = y*w+x;
		return p;
	  }

}
