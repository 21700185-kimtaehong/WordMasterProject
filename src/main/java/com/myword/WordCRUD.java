package com.myword;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final String fname = "Dictionary.txt";

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
        Word one = (Word)this.add();
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

                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                idlist.add(i);
                j++;
        }
        System.out.println("-----------------------------");

        return idlist;
    }

    public void listAll(int level){
        ArrayList<Integer> idlist = new ArrayList<>();

        System.out.println("-----------------------------");
        int j = 0;
        for (int i = 0; i < list.size(); ++i) {
            int ilevel = list.get(i).getLevel();
            if (ilevel != level) continue;

            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("-----------------------------");

    }

    public void loadFile() {
        try{
            BufferedReader freader = new BufferedReader(new FileReader(fname));
            String wordLine;
            int count = 0;

            while(true){
                wordLine = freader.readLine();
                if(wordLine==null) break;

                String data[] = wordLine.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }

            freader.close();
            System.out.println("===> " + count + "개 로딩 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list){
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchLevel() throws IOException {
        System.out.print("=> 원하는 레벨은? (1~3)");
        int level = Integer.parseInt(reader.readLine());
        listAll(level);
    }

    public void searchWord() throws IOException {
        System.out.print("=> 원하는 단어는? ");
        String keyword = reader.readLine();
        listAll(keyword);
    }
}
