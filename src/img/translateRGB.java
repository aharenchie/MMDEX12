package img;

public class translateRGB {
	
	/*画像をグレイ化するメソッド*/
	public static int[] ToGlay(int[] c,int w,int h){
		
		int[] new_c = new int[w*h];
		int r,g,b,gray;
		 for(int i=0;i<w*h;i++){
			 r = Formatter.r(c[i]);
			 g = Formatter.g(c[i]);
			 b = Formatter.b(c[i]);
			 gray = (g*6+r*3+b)/10;
			 new_c[i]=Formatter.rgb(gray,gray,gray);
		 }

		return new_c;
	}
	
	/*トーンカーブメソッド */
	public static int[] ToneCurve(int[] c,int w,int h){
		int[] new_c = new int[w*h];
		int new_r,new_g,new_b;
		
		for(int i=0;i<w*h;i++){
			
			if(Formatter.r(c[i]) > 210){
				new_r = 255;
			}else{
				new_r = Formatter.r(c[i]);
			}
			
			if(Formatter.g(c[i]) > 210){
				new_g = 255;
			}else{
				new_g = Formatter.g(c[i]);
			}
			
			if(Formatter.b(c[i]) > 210){
				new_b = 255;
			}else{
				new_b = Formatter.b(c[i]);
			}
	
			new_c[i]=Formatter.rgb(new_r,new_g,new_b);
		}
		
		return new_c;
	}
}
