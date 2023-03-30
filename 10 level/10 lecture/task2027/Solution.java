import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'e', 'd', 'e', 'r', 'e', 'k'},
                {'u', 'h', 'a', 'm', 'h', 'o'},
                {'l', 'n', 'o', 'o', 'o', 'v'},
                {'m', 'о', 'm', 'm', 'i', 'h'},
                {'p', 'e', 'e', 'e', 'e', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home");
        for(Word word : list){
            System.out.println(word);
        }
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        if(words != null){
            for(int i = 0; i < words.length; i++){
                Word word = getWord(crossword, words[i]);
                list.add(word);
            }
        }
        
        return list;
    }
    
    //Ходим по двумерному массиву и ищем координату начала буквы слова.
    public static Word getWord(int[][] crossword, String strWord){
        Word word = new Word(strWord);
        for(int y = 0; y < crossword.length; y++){
            for(int x = 0; x < crossword[y].length; x++){
                if(crossword[y][x] == (int)strWord.toCharArray()[0]){
                    //Если нашли совпадение пересылаем данные в другой метод
                    if(getStarted(crossword,word,strWord,y,x)) return word;
                }
            }
        }
        return null;
    }
    public static boolean getStarted(int[][] crossword, Word word, String strWord, int yStart, int xStart){
        int xEnd = xStart;
        int yEnd = yStart;
        char[] charArray = strWord.toCharArray();
        //Ходим вправо
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart, xStart+i-1,0,1,i)){
                break;
            }else{
                xEnd = xEnd + 1;
                yEnd = yEnd + 0;
                
                
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        xEnd = xStart;
        yEnd = yStart;
        //Ходим влево
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart, xStart-i+1,0,-1,i)){
                break;
            }else{
                xEnd = xEnd - 1;
                yEnd = yEnd + 0;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        xEnd = xStart;
        yEnd = yStart;
        //Ходим вверх
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart+i-1, xStart,1,0,i)){
                break;
            }else{
                xEnd = xEnd + 0;
                yEnd = yEnd + 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        xEnd = xStart;
        yEnd = yStart;
        //Ходим вниз
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart-i+1, xStart,-1,0,i)){
                break;
            }else{
                xEnd = xEnd + 0;
                yEnd = yEnd - 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        xEnd = xStart;
        yEnd = yStart;
        //Ходим по диоганали вверх в право.
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart-i+1, xStart+i-1,-1,1,i)){
                break;
            }else{
                xEnd = xEnd + 1;
                yEnd = yEnd - 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        
        xEnd = xStart;
        yEnd = yStart;
        //Ходим по диоганали вверх в лево.
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart-i+1, xStart-i+1,-1,-1,i)){
                break;
            }else{
                xEnd = xEnd - 1;
                yEnd = yEnd - 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        
        xEnd = xStart;
        yEnd = yStart;
        //Ходим по диоганали вниз в лево.
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart+i-1, xStart-i+1,1,-1,i)){
                break;
            }else{
                xEnd = xEnd - 1;
                yEnd = yEnd + 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        
        xEnd = xStart;
        yEnd = yStart;
        //Ходим по диоганали вниз в право.
        for(int i = 1; i < charArray.length;i++){
            if(!reflex(crossword, word, strWord, yStart+i-1, xStart+i-1,1,1,i)){
                break;
            }else{
                xEnd = xEnd + 1;
                yEnd = yEnd + 1;
            }
            if(i == charArray.length - 1){
                word.endX = xEnd;
                word.endY = yEnd;
                word.startX = xStart;
                word.startY = yStart;
                return true;
            }
        }
        return false;
        
    }
    
    public static boolean reflex(int[][] crossword, Word word, String strWord, int yStart, int xStart, int moveY, int moveX, int countLetter){
        if(canIgoNext(crossword,yStart + moveY, xStart + moveX)){
            return (int)crossword[yStart+moveY][xStart + moveX] == strWord.toCharArray()[countLetter];
        }else return false;
    
    }
    public static boolean canIgoNext(int[][] crossword, int y, int x){
        if(y > crossword.length - 1 || y < 0) return false;
        if(x > crossword[y].length - 1 || x < 0) return false;
        return true;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
