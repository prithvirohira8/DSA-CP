package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;




public class Main {
    static void addFiles(File directory, List<File> textFiles){
        File[] files = directory.listFiles();
        if(files == null) return;
        if(files.length == 0) return;
        for(File file: files){
            if(!file.isDirectory()) {
                String fileName = file.getName();
                if (fileName.substring(fileName.length() - 4, fileName.length()).equals(".txt")) {
                    textFiles.add(file);
                }
            }
            addFiles(file, textFiles);
        }
    }

    static int getTotalFiles(List<File> textFiles){
        return textFiles.size();
    }

    static void printTop20Words(List<List<String>> top20Words, List<List<Integer>> top20WordCounts){
        System.out.println("\nTop 20 words in each file with count: ");
        for(int i = 0; i < top20Words.size(); i++){
            System.out.println("File "+(i+1));
            for(int j = 0; j < top20Words.get(i).size(); j++){
                System.out.println(top20Words.get(i).get(j)+" : "+top20WordCounts.get(i).get(j));
            }
            System.out.println();
        }
    }

    static void printCounts(int[] file_lines_count, int[] file_words_count, int[] file_character_count){
        System.out.println("\nTotal Lines, Words & Characters in Each File: ");
        for(int i = 0; i < file_lines_count.length; i++){
            System.out.println("Lines in File "+(i+1)+": "+file_lines_count[i]);
            System.out.println("Words in File "+(i+1)+": "+file_words_count[i]);
            System.out.println("Characters in File "+(i+1)+"(including whitespaces): "+file_character_count[i]);
        }
    }
    public static void main(String[] args) {

        String directoryPath = "/Users/prithvirohira8/Desktop/DSA/JAVA OOPs/test1/txt_files";
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {

            System.out.println("Invalid directory path!");

            return;

        }

        File[] files = directory.listFiles();

        List<File> textFiles = new ArrayList<>();
        for(File file: files){
            if(!file.isDirectory()) {
                String fileName = file.getName();
                if (fileName.substring(fileName.length() - 4, fileName.length()).equals(".txt")) {
                    textFiles.add(file);
                }
            }
            addFiles(file, textFiles);
        }


        int total_files = getTotalFiles(textFiles);
        System.out.println("total_files = "+total_files);

        int[] file_lines_count = new int[total_files];
        int[] file_character_count = new int[total_files];
        int[] file_words_count = new int[total_files];
        List<List<String>> top20Words = new ArrayList<>();
        List<List<Integer>> top20WordCounts = new ArrayList<>();

        for(int i = 0; i < total_files; i++){
            top20Words.add(new ArrayList<String>());
            top20WordCounts.add(new ArrayList<Integer>());
        }

        // bussiness logic
        try{
            for(int i = 0; i < textFiles.size(); i++){
                File temp = textFiles.get(i);
                Scanner reader = new Scanner(temp);
                HashMap<String, Integer> wordCount = new HashMap<>();
                while(reader.hasNextLine()){
                    file_lines_count[i] += 1;

                    String line_data = reader.nextLine();

                    file_character_count[i] += line_data.length();

                    String[] words = line_data.split("\\W+");

                    file_words_count[i] += words.length;

                    for(String word: words){
                        if(word.equals("")) continue;
                        if(wordCount.containsKey(word)){
                            wordCount.put(word, wordCount.get(word) + 1);
                        }
                        else wordCount.put(word, 1);
                    }
                }

                List<WordCount> tempList= new ArrayList<>();
                for(String key:wordCount.keySet()){
                    int value = wordCount.get(key);
                    tempList.add(new WordCount(key, value));
                }
                Collections.sort(tempList, new Comparator<WordCount>() {
                    @Override
                    public int compare(WordCount o1, WordCount o2) {
                        if(o1.count < o2.count) return 1;
                        else if(o1.count > o2.count) return -1;
                        else return 0;
                    }
                });
                // getting the top 20 words;
                for(int j = 0; j < tempList.size() && j < 20; j++){
                    top20Words.get(i).add(tempList.get(j).word);
                }
                // getting the top 20 word counts;
                for(int j = 0; j < tempList.size() && j < 20; j++){
                    top20WordCounts.get(i).add(tempList.get(j).count);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("error");
        }
        printCounts(file_lines_count, file_words_count, file_character_count);
        printTop20Words(top20Words, top20WordCounts);

        System.out.println("Scanning directory: " + directoryPath + '\n');

    }
}

class WordCount{
    String word;
    int count;
    public WordCount(String word, int count){
        this.word = word;
        this.count = count;
    }
}