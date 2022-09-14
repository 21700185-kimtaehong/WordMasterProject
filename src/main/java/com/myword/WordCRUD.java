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
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력: ");

        StringTokenizer wordToken = new StringTokenizer(reader.readLine());
        int level = Integer.parseInt(wordToken.nextToken());
        String word = wordToken.nextToken();

        System.out.print("뜻 입력: ");;
        String meaning = reader.readLine();

        return new Word(0, level, word, meaning);
    }

    public void addItem() throws IOException {
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    public void updateItem() throws IOException {
        ArrayList<Integer> idlist = null;
        do {
            System.out.print("=> 수정할 단어 검색: ");
            String keyword = reader.readLine();
            idlist = this.listAll(keyword);
            if(idlist.isEmpty()) System.out.println("목록에 단어가 없습니다. 다시 검색하세요.");
        } while(idlist.isEmpty());

        System.out.print("=> 수정할 번호 선택: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("=> 뜻 입력: ");
        String meaning = reader.readLine();

        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    public void deleteItem() throws IOException {
        ArrayList<Integer> idlist = null;
        do {
            System.out.print("=> 삭제할 단어 검색: ");
            String keyword = reader.readLine();
            idlist = this.listAll(keyword);
            if(idlist.isEmpty()) System.out.println("목록에 단어가 없습니다. 다시 검색하세요.");
        } while(idlist.isEmpty());

        System.out.print("=> 삭제할 번호 선택: ");
        int id = Integer.parseInt(reader.readLine());

        System.out.println("=> 정말로 삭제하실래요? (Y/n) ");
        String ans = reader.readLine();
        if(ans.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다.");
        }
        else System.out.println("취소되었습니다.");

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

    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();

        System.out.println("-----------------------------");
        int j = 0;
        for (int i = 0; i < list.size(); ++i) {
                String word = list.get(i).getWord();
                if (!word.contains(keyword)) continue;

                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
                idlist.add(i);
                j++;
        }
        System.out.println("-----------------------------");

        return idlist;
    }


}
