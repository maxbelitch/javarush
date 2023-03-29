import java.util.ArrayList;

public class Solution {
    public static class Rectangle {
        public int xStart;
        public int xEnd;
        public int yStart;
        public int yEnd;

        public Rectangle(int xStart, int xEnd, int yStart, int yEnd) {
            this.xStart = xStart;
            this.xEnd = xEnd;
            this.yStart = yStart;
            this.yEnd = yEnd;
        }
        public String toString(){
            return "Прямоугольник xStart = " + xStart + " xEnd = " + xEnd + " yStart = " + yStart + " yEnd = " + xEnd;
        }
    }

    public static int getRectangleCount(byte[][] a) {
        ArrayList<Rectangle> list = new ArrayList<>();

        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                if (a[y][x] == 1) {
                    Rectangle rectangle = getRectangle(a,y,x);
                    if(isNewRectangle(list,rectangle)){
                        list.add(rectangle);
                    }
                }
            }
        }
        return list.size();
    }
    public static boolean isNewRectangle(ArrayList<Rectangle> rectangles, Rectangle newRectangle){
        if(rectangles.size() == 0) return true;
        for(Rectangle rectangle : rectangles){
            if(newRectangle.xStart >= rectangle.xStart && newRectangle.xEnd <= rectangle.xEnd){
                if(newRectangle.yStart >= rectangle.yStart && newRectangle.yEnd <= rectangle.yEnd){
                    return false;
                }
            }
        }
        return true;

    }
    public static Rectangle getRectangle(byte[][] a, int yStart, int xStart){
        //ищем xEnd
        int xEnd = 0;
        for(int i = xStart; i < a[yStart].length; i++){
            if(a[yStart][i] == 1){
                xEnd = i;
            }else break;
        }
        //Ищем yEnd
        int yEnd = 0;
        for(int i = yStart; i < a.length; i++){
            if(a[i][xStart] == 1){
                yEnd = i;
            }else break;
        }
        return new Rectangle(xStart,xEnd,yStart,yEnd);
    }

    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0,0},
                {1, 1, 0, 1,0},
                {1, 1, 0, 1,0},
                {1, 1, 0, 1,0},
                {1, 1, 0, 1,0}
        };
        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
    }
}
