package com.myword;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public WordCRUD(BufferedReader reader) throws IOException {
        list = new ArrayList<>();
        this.reader = reader;
    }


    @Override
    public Object add() throws IOException {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력: ");

        StringTokenizer wordToken = new StringTokenizer(reader.readLine());
        int level = Integer.parseInt(wordToken.nextToken());
        String word = wordToken.nextToken();

        System.out.print("뜻 입력: ");;
        String meaning = reader.readLine();

        return new Word(0, level, word, meaning);
    }

    public void addWord() throws IOException {
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        System.out.println("-----------------------------");
        for(int i=0; i<list.size(); ++i){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-----------------------------");
    }
}
