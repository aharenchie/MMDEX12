package img;

import img.AccessControl;

public class CreateLine {
	
	/*ソーベルフィルタ*/
	public static int[] sobelFilter(int[] c,int w,int h){
		
		 int[] new_c = new int[w*h]; //処理した画素値を格納する配列
		 int new_rgb = 0;
		 int r,sumr;
		 
		 int[] weightH = {-1, 0, 1, -2, 0, 2, -1, 0, 1}; 
		 int[] weightV = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
		 int i,j;
		 
		 int weightSize = 3;
		 
		 double dataTmpHr,dataTmpVr,dataTmpSumr;
		 		 
		 for(int y=0;y<h;y++){
	            for(int x=0;x<w;x++){
	            	
	            	 dataTmpHr = dataTmpVr = 0;
	            	 	            	
	            	for(j=-1; j<=1; j++){
	            		for(i=-1; i<=1; i++){
	            			
	            			r = Formatter.r(c[AccessControl.getPosition(w,h,x+i,y+j)]);
	            			            			
	            			dataTmpHr += weightH[(j+1)*weightSize+(i+1)] * r;	 //横方向           		            			
	            			dataTmpVr += weightV[(j+1)*weightSize+(i+1)] * r;    //縦方向
	            		}
	            	}
	            	
	            	dataTmpSumr = Math.sqrt(dataTmpHr * dataTmpHr + dataTmpVr * dataTmpVr);	           
	            	sumr = AccessControl.getColor((int)dataTmpSumr);
	            	   
	            	// 二値化
	            	if(sumr > 90){
	            		new_rgb = Formatter.rgb(0,0,0);
	            	}else{
	            		new_rgb = Formatter.rgb(255,255,255);
	            	}
	            	new_c[y*w+x] = new_rgb;
	            }
		 }
		 
		 	return new_c; 
	}
	
}
