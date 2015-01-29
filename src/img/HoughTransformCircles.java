package img;

public class HoughTransformCircles {
	
	public static int OBJECT_NUM = 0;
	public static int[] pre_r = new int[255];
	public static int[] pre_c_x = new int[255];
	public static int[] pre_c_y = new int[255];
	 
	public static void run(int[] c,int w,int h){
		
		int XMAX = w;
		int YMAX = h;
		int RMAX = 30;
		
		
		int[][] prediction_r = new int[YMAX][XMAX];
		
		  //半径を計算しておく
	      for(int y=0;y<YMAX;y++)
	         for(int x=0;x<XMAX;x++)
	        	 prediction_r[y][x]=(int)(Math.sqrt(y*y+x*x)+0.5);
		
		int[][][] counter = new int[YMAX][XMAX][RMAX];//ある円周上にいくつの点があるか	
		int dist_x,dist_y,r;//x座標、y座標ごとの距離と半径	
		int c_xmax,c_ymax,rmax;
		int counter_max;
		
		c_xmax = c_ymax = rmax = counter_max = 0;
		
		 for(int y=0;y<h;y++){
			 for(int x=0;x<w;x++){
				 
				 //探索ピクセルにエッジがある時
				 if(Formatter.r(c[y*w+x]) == 0){
					 
					 //そのピクセルが円周上にあるような円を探す
					 for(int c_y = 0;c_y < YMAX;c_y++){
						 dist_y = Math.abs(y-c_y); //対象のy座標と円中心y座標(仮)の距離
						 
						//円中心y座標(仮)が半径Rより大きい場合、ループから抜ける
						 if(dist_y > RMAX) continue; 
	            			
						 for(int c_x = 0;c_x < XMAX;c_x++){
							 dist_x = Math.abs(x-c_x); //対象のx座標と円中心x座標(仮)の距離
	            				
							 r = prediction_r[dist_y][dist_x];//半径を決定
							 
							//最大半径サイズより大きい場合、ループから抜ける
							 if(r >= RMAX) continue; 
							 
							 counter[c_y][c_x][r]++;//半径rの円(c_x,c_y)に投票する
						 	}							 
	            		}	
					 
	            	}
	           	}
		 }
		 
		 boolean flag = true;
		 
		 //投票数の多い円候補を調べる
		 while(flag){	
			 
	         counter_max=0;   
	         
	         for(int c_y = 0;c_y < YMAX;c_y++){
	        	 for(int c_x = 0;c_x < XMAX;c_x++){
	        		 for(r = 0;r < RMAX;r++){	        			 
	        			 //現在の投票数が最大とされている投票より多いとき
	        			if(OBJECT_NUM == 0 && counter_max < counter[c_y][c_x][r]){
	        				
	        				counter_max = counter[c_y][c_x][r];
	        				c_xmax = c_x;
	        				c_ymax = c_y;
	        				rmax = r;		
	        			}else if(counter_max < counter[c_y][c_x][r] ){
	        				
	        				//以前に円と検出されたものへの投票数はスキップ
	        				for(int j = 0; j < OBJECT_NUM;j++)
	        					if(Math.abs(pre_c_x[j] - c_x) < 5 && Math.abs(pre_c_y[j] - c_y) < 5 )
	        						flag = false;
	        				
	        				if(flag){
	        				counter_max = counter[c_y][c_x][r];
	        				c_xmax = c_x;
	        				c_ymax = c_y;
	        				rmax = r;       
	        				}
	        			}
	        			
	        		 }					
	        	 } 	 
	        }
	         
	         //円と検出された中心座標と半径を代入
	         pre_c_x[OBJECT_NUM] = c_xmax;
	         pre_c_y[OBJECT_NUM] = c_ymax;	         
	         pre_r[OBJECT_NUM] = rmax;
	         
	         if(counter_max < 140){
	        	 flag = false;
	         }else{
	        	 flag = true;
	        	 OBJECT_NUM++;
	             System.out.println("---"+OBJECT_NUM+"個目---" );
		         System.out.println("("+c_xmax+","+ c_ymax +")");
	         }
	        
		 }
		return;
	}
	
	
}
