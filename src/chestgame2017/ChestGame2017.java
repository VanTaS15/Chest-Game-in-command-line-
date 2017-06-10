/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chestgame2017;

//import static chestgame2017.ChestGame_aiplaying2.BOARD;
//import static chestgame2017.ChestGame_aiplaying2.aiPlaying;
//import static chestgame2017.ChestGame_aiplaying2.move2;
//import static chestgame2017.ChestGame_aiplaying2.printBoard;
//import static chestgame2017.ChestGame_aiplaying2.sBOARD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Random;

/**
 *
 * @author VanTa
 */
public class ChestGame2017 {

    /**
     * @param args the command line arguments
     */
    
    private int color;
	public static final int COLOR_WHITE = 0;
	public static final int COLOR_BLACK = 1;
	
	private int type;
	public static final int TYPE_ROOK_BLACK=-50;
        public static final int TYPE_ROOK_WHITE=50 ;

	public static final int TYPE_BISHOP_BLACK = -30;
        public static final int TYPE_BISHOP_WHITE = 30;
        
	public static final int TYPE_QUEEN_BLACK = -90;
        public static final int TYPE_QUEEN_WHITE = 90;
        
	public static final int TYPE_KING_BLACK = -30;
	public static final int TYPE_KING_WHITE = 30;
        
	public static final int TYPE_PAWN_BLACK = -10;
	public static final int TYPE_PAWN_WHITE = 10;
        
   
    public static int[][] BOARD= new int[][]{
        {TYPE_ROOK_WHITE,TYPE_BISHOP_WHITE,TYPE_KING_WHITE,TYPE_QUEEN_WHITE,TYPE_BISHOP_WHITE,TYPE_ROOK_WHITE},
        {TYPE_PAWN_WHITE,TYPE_PAWN_WHITE,TYPE_PAWN_WHITE,TYPE_PAWN_WHITE,TYPE_PAWN_WHITE,TYPE_PAWN_WHITE},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {TYPE_PAWN_BLACK,TYPE_PAWN_BLACK,TYPE_PAWN_BLACK,TYPE_PAWN_BLACK,TYPE_PAWN_BLACK,TYPE_PAWN_BLACK},
        {TYPE_ROOK_BLACK,TYPE_BISHOP_BLACK,TYPE_QUEEN_BLACK,TYPE_KING_BLACK,TYPE_BISHOP_BLACK,TYPE_ROOK_BLACK}
        
    };
    
    public static String[][] sBOARD= new String[][]{
        {"WR","WB","WK","WQ","WB","WR"},
        {"WP","WP","WP","WP","WP","WP"},
        {"  ","  ","  ","  ","  ","  "},
        {"  ","  ","  ","  ","  ","  "},
        {"BP","BP","BP","BP","BP","BP"},
        {"BR","BB","BQ","BK","BB","BR"}
        
    };
    
    
    
    public static void printBoard(){
        int Row=1;
        System.out.println("  a  b  c  d  e  f  ");
     for(int i=0;i<6;i++){
            System.out.println(" +--+--+--+--+--+--+");
           System.out.print(Row++);
            for(int j=0;j<6;j++){
                
                System.out.print("|"+sBOARD[i][j]);
            
        }
            System.out.print("|");
            System.out.print("\n");
            
     }
     System.out.println(" +--+--+--+--+--+--+");
    System.out.println("  a  b  c  d  e  f  ");
    }
    public static boolean move(String row, String col,String tRow, String tCol){
        int Col=0,tarCol= 0;
        int Row=Integer.valueOf(row);
        int tarRow=Integer.valueOf(tRow);
        //Define value char
        switch(col){
            case "a": Col=0;break;
            case "b": Col=1;break;
            case "c": Col=2;break;
            case "d": Col=3;break;
            case "e": Col=4;break;
            case "f": Col=5;break;
            default: break;        
        }//System.out.print("COL="+Col);
       
        switch(tCol){
            case "a": tarCol=0;break;
            case "b": tarCol=1;break;
            case "c": tarCol=2;break;
            case "d": tarCol=3;break;
            case "e": tarCol=4;break;
            case "f": tarCol=5;break;
            default: break;        
        }//System.out.print("TARCOL="+tarCol);
        //Position Restriction FOR BLACK
        boolean obstacle=false;
        switch(sBOARD[Row-1][Col]){
            case "BP": 
                if(((tarRow-1)-(Row-1))==-1 && (tarCol==Col) ){
                    if(BOARD[tarRow-1][tarCol]==0){
                        BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                        BOARD[Row-1][Col]=0;
               
                        sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                        sBOARD[Row-1][Col]="  ";
                        return true;
                        
                    }
                    else{
                        
                        System.out.println("Invalid move! ");
                        return false;
                        
                    }
                    //break;
                }
                else{
                    if( ((((tarRow-1)==(Row-1)-1) && (tarCol==Col-1) ) || ( ((tarRow-1)==(Row-1)-1) && (tarCol==Col+1) )) && (  BOARD[tarRow-1][tarCol]>0 )){
                            if(BOARD[tarRow-1][tarCol]>0){
                                BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                                BOARD[Row-1][Col]=0;
               
                                sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                                sBOARD[Row-1][Col]="  ";
                                return true;
                            }else{
                                System.out.println("Invalid move! ");
                                return false;
                                
                            }
                            
                        }else{
                        System.out.println("Invalid move! ");
                        return false;
                    }
                    
                   
                }
                //break;
                
            case "BR":
                
                
                if(( (tarRow-1)==(Row-1) )||(tarCol==Col )){
                    
                    if(tarCol==Col){
                        if((tarRow-1)<(Row-1)){
                            System.out.print(Row);
                            for(int k=(Row-1)-1;k>(tarRow-1);k--){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }else{
                            for(int k=(Row-1)+1;k<(tarRow-1);k++){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarCol>Col){
                            for(int k=Col+1;k<tarCol;k++){
                               if(BOARD[tarRow-1][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }else
                        {
                            for(int k=Col-1;k>tarCol;k--){
                                if(BOARD[tarRow-1][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }
                    }
                    
   
                        
                        if(obstacle ){
                             System.out.println("Invalid move obstacle true! \n ");
                             return false;
                             
                            
                        }else if(BOARD[tarRow-1][tarCol]>=0){
                            BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                            BOARD[Row-1][Col]=0;
               
                            sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                            sBOARD[Row-1][Col]="  "; 
                            return true;
                        }
 
                        break;
                    
                   
                }else{
                    System.out.println("Invalid move! ");
                    return false;
                }    
                
                
            
            case "BB": 
                if(  !(((tarRow-1)==(Row-1) )||(tarCol==Col ))){
                    if(BOARD[tarRow-1][tarCol]>=0){
                        BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                            BOARD[Row-1][Col]=0;
               
                            sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                            sBOARD[Row-1][Col]="  "; 
                            return true;
                    }
                }else{
                    System.out.println("Invalid move! ");
                    return false;
                }
            break;
            
            case "BQ": 
                
                
                
                
                
                if((((tarRow-1)==(Row-1) )||(tarCol==Col ))||!(((tarRow-1)==(Row-1) )||(tarCol==Col ))){
                    if(tarCol==Col){
                        if((tarRow-1)<(Row-1)){
                            //System.out.print(Row);
                            for(int k=(Row-1)-1;k>(tarRow-1);k--){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }else{
                            for(int k=(Row-1)+1;k<(tarRow-1);k++){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarCol>Col){
                            for(int k=Col+1;k<tarCol;k++){
                               if(BOARD[tarRow-1][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }else
                        {
                            for(int k=Col-1;k>tarCol;k--){
                                if(BOARD[tarRow-1][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }
                    }
                    
                    
                    
                    
                    
                   if((BOARD[tarRow-1][tarCol]>=0) && obstacle==false){
                    BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                    BOARD[Row-1][Col]=0;
               
                    sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                    sBOARD[Row-1][Col]="  "; 
                    return true;
                    
                }else{
                     System.out.println("Invalid move! ");  
                     return false;
                   } 
                }else {
                   System.out.println("Invalid move! ");
                   return false;
                }
                
            
            
            
            case "BK": 
                if(((tarRow-1)-(Row-1))==-1){
                   if(BOARD[tarRow-1][tarCol]>=0){
                       BOARD[tarRow-1][tarCol]=BOARD[Row-1][Col];
                    BOARD[Row-1][Col]=0;
               
                    sBOARD[tarRow-1][tarCol]=sBOARD[Row-1][Col];
                    sBOARD[Row-1][Col]="  "; 
                    return true;
                   } else
                   {
                        System.out.println("Invalid move! ");
                        return false;
                   }
                }else
                {
                     System.out.println("Invalid move! ");
                     return false;
                }
            
            
            default: break; 
            
        }
  
    return false;
    }
    
    public static boolean move2(int Row ,int Col, int tarRow, int tarCol){
       //System.out.print("TARCOL="+tarCol);
        //Position Restriction FOR BLACK
        boolean obstacle=false;
        switch(sBOARD[Row][Col]){
            case "BP": 
                if(((tarRow)-(Row))==-1 && (tarCol==Col) ){
                    if(BOARD[tarRow][tarCol]==0){
                        BOARD[tarRow][tarCol]=BOARD[Row][Col];
                        BOARD[Row][Col]=0;
               
                        sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                        sBOARD[Row][Col]="  ";
                        return true;
                        
                    }
                    else{
                        
                        
                        return false;
                        
                    }
                    //break;
                }
                else{
                    if( ((((tarRow)==(Row)-1) && (tarCol==Col-1) ) || ( ((tarRow)==(Row)-1) && (tarCol==Col+1) )) && (  BOARD[tarRow][tarCol]>0 )){
                            if(BOARD[tarRow][tarCol]>0){
                                BOARD[tarRow][tarCol]=BOARD[Row][Col];
                                BOARD[Row][Col]=0;
               
                                sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                                sBOARD[Row][Col]="  ";
                                return true;
                            }else{
                                System.out.println("Invalid move! ");
                                return false;
                                
                            }
                            
                        }else{
                       
                        return false;
                    }
                    
                   
                }
                //break;
                
            case "BR":
                
                
                if(( (tarRow)==(Row) )||(tarCol==Col )){
                    
                    if(tarCol==Col){
                        if((tarRow)<(Row)){
                            System.out.print(Row);
                            for(int k=(Row)-1;k>(tarRow);k--){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }else{
                            for(int k=(Row)+1;k<(tarRow);k++){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarCol>Col){
                            for(int k=Col+1;k<tarCol;k++){
                               if(BOARD[tarRow][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }else
                        {
                            for(int k=Col-1;k>tarCol;k--){
                                if(BOARD[tarRow][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }
                    }
                    
   
                        
                        if(obstacle ){
                             System.out.println("Invalid move obstacle true! \n ");
                             return false;
                             
                            
                        }else if(BOARD[tarRow][tarCol]>=0){
                            BOARD[tarRow][tarCol]=BOARD[Row][Col];
                            BOARD[Row][Col]=0;
               
                            sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                            sBOARD[Row][Col]="  "; 
                            return true;
                        }
 
                        break;
                    
                   
                }else{
                    
                    return false;
                }    
                
                
            
            case "BB": 
                if(  !(((tarRow)==(Row) )||(tarCol==Col ))){
                    if(BOARD[tarRow][tarCol]>=0){
                        BOARD[tarRow][tarCol]=BOARD[Row][Col];
                            BOARD[Row][Col]=0;
               
                            sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                            sBOARD[Row][Col]="  "; 
                            return true;
                    }
                }else{
                    
                    return false;
                }
            break;
            
            case "BQ": 
                
                
                
                
                
                if((((tarRow)==(Row) )||(tarCol==Col ))||!(((tarRow)==(Row) )||(tarCol==Col ))){
                    if(tarCol==Col){
                        if((tarRow-1)<(Row)){
                            //System.out.print(Row);
                            for(int k=(Row)-1;k>(tarRow);k--){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }else{
                            for(int k=(Row)+1;k<(tarRow);k++){
                                if(BOARD[k][tarCol]<0){
                                    obstacle=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarCol>Col){
                            for(int k=Col+1;k<tarCol;k++){
                               if(BOARD[tarRow][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }else
                        {
                            for(int k=Col-1;k>tarCol;k--){
                                if(BOARD[tarRow][k]<0){
                                   obstacle=true;
                               } 
                            }
                        }
                    }
                    
                    
                    
                    
                    
                   if((BOARD[tarRow-1][tarCol]>=0) && obstacle==false){
                    BOARD[tarRow][tarCol]=BOARD[Row][Col];
                    BOARD[Row][Col]=0;
               
                    sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                    sBOARD[Row][Col]="  "; 
                    return true;
                    
                }else{
                     
                     return false;
                   } 
                }else {
                  
                   return false;
                }
                
            
            
            
            case "BK": 
                if(((tarRow)-(Row))==-1){
                   if(BOARD[tarRow][tarCol]>=0){
                       BOARD[tarRow][tarCol]=BOARD[Row][Col];
                    BOARD[Row][Col]=0;
               
                    sBOARD[tarRow][tarCol]=sBOARD[Row][Col];
                    sBOARD[Row][Col]="  "; 
                    return true;
                   } else
                   {
                        
                        return false;
                   }
                }else
                {
                    
                     return false;
                }
            
            
            default: break; 
            
        }
  
    return false;
    }
    
    
    
    
    
    

    public static boolean aiPlaying(int rowValue ,int colValue, int tarRowValue, int tarColValue){
        boolean obstacle2=false;
    switch(sBOARD[rowValue][colValue]){
        case "WP": 
             if(((tarRowValue)-(rowValue))==-1 && (tarColValue==colValue) ){
                    if(BOARD[tarRowValue][tarColValue]==0){
                        BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                        BOARD[rowValue][colValue]=0;
               
                        sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                        sBOARD[rowValue][colValue]="  ";
                        return true;
                        
                    }
                    else{
                        
                       
                        return false;
                        
                    }
                    //break;
                }else{
                    if( (((tarRowValue==rowValue+1) && (tarColValue==colValue-1) ) || ( (tarRowValue==rowValue+1) && (tarColValue==colValue+1) )) && (  BOARD[tarRowValue][tarColValue]<0 )){
                            if(BOARD[tarRowValue][tarColValue]<0){
                                BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                                BOARD[rowValue][colValue]=0;
               
                                sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                                sBOARD[rowValue][colValue]="  ";
                                return true;
                                
                            }else{
                               
                                return false;
                                
                            }
                            
                        }else{
                        
                        return false;
                    }
                    
                   
                }
  
        case "WR": 
            if(( (tarRowValue)==(rowValue) )||(tarColValue==colValue )){
                    
                    if(tarColValue==colValue){
                        if((tarRowValue)<(rowValue)){
                            //System.out.print(Row);
                            for(int k=(rowValue)-1;k>(tarRowValue);k--){
                                if(BOARD[k][tarColValue]>0){
                                    obstacle2=true;
                                }
                            }
                        }else{
                            for(int k=(rowValue)+1;k<(tarRowValue);k++){
                                if(BOARD[k][tarColValue]>0){
                                    obstacle2=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarColValue>colValue){
                            for(int k=colValue+1;k<tarColValue;k++){
                               if(BOARD[tarRowValue][k]>0){
                                   obstacle2=true;
                               } 
                            }
                        }else
                        {
                            for(int k=colValue-1;k>tarColValue;k--){
                                if(BOARD[tarRowValue][k]>0){
                                   obstacle2=true;
                               } 
                            }
                        }
                    }
                    
   
                        
                        if(obstacle2){
                             System.out.println("Invalid move obstacle true! \n ");
                             return false;
                             
                            
                        }else if(BOARD[tarRowValue][tarColValue]<=0){
                                BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                                BOARD[rowValue][colValue]=0;
               
                                sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                                sBOARD[rowValue][colValue]="  "; 
                            return true;
                        }
 
                    
                   
                }else{
                    
                    return false;
                } 
            
            
        case "WB": 
             if(  !(((tarRowValue)==(rowValue) )||(tarColValue==colValue ))){
                    if(BOARD[tarRowValue][tarColValue]<=0){
                                BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                                BOARD[rowValue][colValue]=0;
               
                                sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                                sBOARD[rowValue][colValue]="  ";  
                            return true;
                    }
                }else{
                   
                    return false;
                }
        case "WQ": 
            
                if((((tarRowValue)==(rowValue) )||(tarColValue==colValue ))||!(((tarRowValue)==(rowValue) )||(tarColValue==colValue ))){
                    if(tarColValue==colValue){
                        if((tarRowValue)<(rowValue)){
                            //System.out.print(Row);
                            for(int k=(rowValue)-1;k>(tarRowValue);k--){
                                if(BOARD[k][tarColValue]<0){
                                    obstacle2=true;
                                }
                            }
                        }else{
                            for(int k=(rowValue)+1;k<(tarRowValue);k++){
                                if(BOARD[k][tarColValue]<0){
                                    obstacle2=true;
                                }
                            }
                        }
                    }else{ 
                        if(tarColValue>colValue){
                            for(int k=colValue+1;k<tarColValue;k++){
                               if(BOARD[tarRowValue][k]<0){
                                   obstacle2=true;
                               } 
                            }
                        }else
                        {
                            for(int k=colValue-1;k>tarColValue;k--){
                                if(BOARD[tarRowValue][k]<0){
                                   obstacle2=true;
                               } 
                            }
                        }
                    }
                    
                    
                    
                    
                    
                   if((BOARD[tarRowValue][tarColValue]<=0) && obstacle2==false){
                    BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                    BOARD[rowValue][colValue]=0;
               
                     sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                     sBOARD[rowValue][colValue]="  "; 
                    return true;
                    
                }else{
                     
                     return false;
                   } 
                }else {
                   
                   return false;
                }
            
        case "WK": 
            if(((tarRowValue)-(rowValue))==1){
                   if(BOARD[tarRowValue][tarColValue]<=0){
                       BOARD[tarRowValue][tarColValue]=BOARD[rowValue][colValue];
                    BOARD[rowValue][colValue]=0;
               
                     sBOARD[tarRowValue][tarColValue]=sBOARD[rowValue][colValue];
                     sBOARD[rowValue][colValue]="  ";  
                    return true;
                   } else
                   {
                        
                        return false;
                   }
                }else
                {
                     
                     return false;
                }
            
            
        default: return false;
     
    }
    
    
        
    }
    
    
    public static void main(String[] args) throws InterruptedException, IOException {
        boolean stateVal=false;
        
        
        
         BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose a game: ");
            System.out.println("(1) for  Player vs AI (2) for AI VS AI ");
            String game = bufferRead.readLine();
            int game2=parseInt(game);
            
        //Player vs AI
if(game2==1){
        printBoard();
        int rowValue = 0;    
            int colValue=0; 
    
            int tarRowValue=0 ;
            int tarColValue=0; 
            int BK=0;
            int WK=0;
            int BKwon=10;
             
   while(BK!=1&&WK!=1){  
       
       //if(BK==1&&WK==0){
            //BKwon    
            //}
       
       
       
       
        boolean aip=false;
        System.out.println("<<<<<<<Chose a piece>>>>>>> ");

    try{
        //BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Choose row: ");
        String row = bufferRead.readLine();
        System.out.print("Choose column: ");
        String col = bufferRead.readLine();
        System.out.print("Choose Target row: ");
        String targetRow= bufferRead.readLine();
        System.out.print("Choose Target Column: ");
        String targetCol = bufferRead.readLine();
        //System.out.println(piecei+piecej);
        
        
        stateVal=move(row,col,targetRow,targetCol);
        
        printBoard();
        /* 
        while (true){
            Random rand = new Random();
           rowValue = rand.nextInt(6);
             colValue = rand.nextInt(6); 
    
             tarRowValue = rand.nextInt(6);
             tarColValue = rand.nextInt(6);
             System.out.print("Ai played this: ("+rowValue+", "+colValue+"), to: ("+tarRowValue+", "+tarColValue+")"+"\n");
        }*/
        if(stateVal){
           
            while(aip==false){
            Random rand = new Random(); 
             rowValue = rand.nextInt(6);
             colValue = rand.nextInt(6); 
    
             tarRowValue = rand.nextInt(6);
             tarColValue = rand.nextInt(6);
                
             aip=aiPlaying(rowValue,colValue,tarRowValue,tarColValue);    
            }
             System.out.print("Ai played this: ("+rowValue+", "+colValue+"), to: ("+tarRowValue+", "+tarColValue+") \n");
             Thread.sleep(1000);
             printBoard();
            }
        
         
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
   }
       
    }else{
    // AI VS AI
    
    
    printBoard();
        int rowValue = 0;    
            int colValue=0; 
    
            int tarRowValue=0 ;
            int tarColValue=0; 
            int BK=0;
            int WK=0;
            int BKwon=10;
            
   while(BK!=1&&WK!=1){  
       
       //if(BK==1&&WK==0){
            //BKwon    
            //}
       
       
       
       
        boolean aip=false;
        boolean aip2=false;
        System.out.println("<<<<<<<Chose a piece>>>>>>> ");

    try{
        //BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Choose row: ");
        //String row = bufferRead.readLine();
       // System.out.print("Choose column: ");
       // String col = bufferRead.readLine();
      //  System.out.print("Choose Target row: ");
       // String targetRow= bufferRead.readLine();
      //  System.out.print("Choose Target Column: ");
       // String targetCol = bufferRead.readLine();
        //System.out.println(piecei+piecej);
        
        
        while(aip2==false){
            Random rand = new Random(); 
             rowValue = rand.nextInt(6);
             colValue = rand.nextInt(6); 
    
             tarRowValue = rand.nextInt(6);
             tarColValue = rand.nextInt(6);
                
             aip2=move2(rowValue,colValue,tarRowValue,tarColValue);    
            
             
             
        }
        System.out.print("Ai black played this: ("+rowValue+", "+colValue+"), to: ("+tarRowValue+", "+tarColValue+") \n");
        Thread.sleep(2000);
        
        stateVal=move2(rowValue,colValue,tarRowValue,tarColValue);
        
        System.out.print(stateVal);
        printBoard();
        /* 
        while (true){
            Random rand = new Random();
           rowValue = rand.nextInt(6);
             colValue = rand.nextInt(6); 
    
             tarRowValue = rand.nextInt(6);
             tarColValue = rand.nextInt(6);
             System.out.print("Ai played this: ("+rowValue+", "+colValue+"), to: ("+tarRowValue+", "+tarColValue+")"+"\n");
        }*/
        if(!stateVal){
           
            while(aip==false){
            Random rand2 = new Random(); 
             rowValue = rand2.nextInt(6);
             colValue = rand2.nextInt(6); 
    
             tarRowValue = rand2.nextInt(6);
             tarColValue = rand2.nextInt(6);
                
             aip=aiPlaying(rowValue,colValue,tarRowValue,tarColValue);    
            }
             System.out.print("Ai white played this: ("+rowValue+", "+colValue+"), to: ("+tarRowValue+", "+tarColValue+") \n");
             Thread.sleep(1000);
             printBoard();
            }
        
         
    }catch(Exception e){
        
    }
    //catch(IOException e)
   // {
        //e.printStackTrace();
            //System.out.print("valid");
   //}
   }
    
    
    
    
    
    
}

    
    
    
    
    
    
    
    
    
    }  
}
