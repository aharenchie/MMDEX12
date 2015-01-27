package img;

import img.AccessControl;

public class CreateLine {
	
	/*ソーベルフィルタ*/
	public static int[] sobelFilter(int[] c,int w,int h){
		
		 int[] new_c = new int[w*h]; //処理した画素値を格納する配列
		 int new_rgb = 0;
		 int r,g,b;
		 int sumr,sumg,sumb;
		 
		 int[] weightH = {-1, 0, 1, -2, 0, 2, -1, 0, 1}; 
		 int[] weightV = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
		 int i,j;
		 
		 int weightSize = 3;
		 
		 double dataTmpHr,dataTmpHg,dataTmpHb; 
		 double dataTmpVr,dataTmpVg,dataTmpVb; 
		 double dataTmpSumr,dataTmpSumg,dataTmpSumb;
		 
		 for(int y=0;y<h;y++){
	            for(int x=0;x<w;x++){
	            	
	            	 dataTmpHr =  dataTmpHg = dataTmpHb = 0;
	            	 dataTmpVr =  dataTmpVg = dataTmpVb = 0;
	            	
	            	for(j=-1; j<=1; j++){
	            		for(i=-1; i<=1; i++){
	            			
	            			r = Formatter.r(c[AccessControl.getPosition(w,h,x+i,y+j)]);
	            			g = Formatter.g(c[AccessControl.getPosition(w,h,x+i,y+j)]);
	            			b = Formatter.b(c[AccessControl.getPosition(w,h,x+i,y+j)]);
	            			
	            			dataTmpHr += weightH[(j+1)*weightSize+(i+1)] * r;
	            			dataTmpHg += weightH[(j+1)*weightSize+(i+1)] * g;
	            			dataTmpHb += weightH[(j+1)*weightSize+(i+1)] * b;
	            			
	            			dataTmpVr += weightV[(j+1)*weightSize+(i+1)] * r;
	            			dataTmpVg += weightV[(j+1)*weightSize+(i+1)] * g;
	            			dataTmpVb += weightV[(j+1)*weightSize+(i+1)] * b;
	            		}
	            	}
	            	
	            	dataTmpSumr = Math.sqrt(dataTmpHr * dataTmpHr + dataTmpVr * dataTmpVr);
	            	dataTmpSumg = Math.sqrt(dataTmpHg * dataTmpHg + dataTmpVg * dataTmpVg);
	            	dataTmpSumb = Math.sqrt(dataTmpHb * dataTmpHb + dataTmpVb * dataTmpVb);
	            	
	            	sumr = AccessControl.getColor((int)dataTmpSumr);
	            	sumg = AccessControl.getColor((int)dataTmpSumg);
	            	sumb = AccessControl.getColor((int)dataTmpSumb);
	            	
	            	if(sumr > 90 && sumg > 90 && sumb > 90){
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
