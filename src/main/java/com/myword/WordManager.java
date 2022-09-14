package com.myword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordManager {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    WordCRUD wordCRUD;

    public WordManager() throws IOException {
        wordCRUD = new WordCRUD(reader);
    }

    public void start() throws IOException {
        while(true) {
            int menu = selectMenu();
            if(menu == 0) {System.out.println("\n 프로그램 종료! 다음에 만나요~");break;}
            else {
                switch(menu){
                    case 1: wordCRUD.listAll();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4: wordCRUD.addWord();
                        break;
                    case 5: wordCRUD.updateItem();
                        break;
                    case 6: //wordCRUD.
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("1~7번 중에 고르세요.");
                        break;
                }
            }
        }
    }


    public int selectMenu() throws IOException { //menu4
        System.out.print(
                "*** 영단어 마스터 ***\n" +
                "********************\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "********************\n" +
                "=> 원하는 메뉴는? ");

        return Integer.parseInt(reader.readLine());
    }
}
