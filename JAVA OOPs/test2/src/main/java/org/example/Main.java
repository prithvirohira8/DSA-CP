package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;




public class Main {
    void addFiles(File directory, List<File> textFiles){
        File[] files = directory.listFiles();
        for(File file: files){
            if(!file.isDirectory()) {
                String fileName = file.getName();
                if (fileName.substring(fileName.length() - 4, fileName.length() - 1).equals(".txt")) {
                    textFiles.add(file);
                }
            }
            addFiles(file, textFiles);
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
                if (fileName.substring(fileName.length() - 4, fileName.length() - 1).equals(".txt")) {
                    textFiles.add(file);
                }
            }
        }
        int total_files = textFiles.size();
        System.out.println("total_files = "+total_files);
//        int[] file_lines_count = new int[total_files];
//        int[] file_character_count = new int[total_files];
//        int[] file_words_count = new int[total_files];
//        List<List<String>> top20Words = new ArrayList<>();
//        List<List<Integer>> top20WordCounts = new ArrayList<>();
//        for(int i = 0; i < total_files; i++){
//            top20Words.add(new ArrayList<String>());
//            top20WordCounts.add(new ArrayList<Integer>());
//        }
//        try{
//            for(int i = 0; i < files.length; i++){
//                String filePath = directoryPath + "/"+files[i].getName();
//                System.out.println("file_name: "+filePath);
//                File temp = new File(filePath);
//                Scanner reader = new Scanner(temp);
//                HashMap<String, Integer> wordCount = new HashMap<>();
//                while(reader.hasNextLine()){
//                    file_lines_count[i] += 1;
//
//                    String line_data = reader.nextLine();
//
//                    file_character_count[i] += line_data.length();
//
//                    String[] words = line_data.split("\\W+");
//
//                    file_words_count[i] += words.length;
//
//                    for(String word: words){
//                        if(word.equals("")) continue;
//                        if(wordCount.containsKey(word)){
//                            wordCount.put(word, wordCount.get(word) + 1);
//                        }
//                        else{
//                            wordCount.put(word, 1);
//                        }
//                    }
//                }
//
//                List<WordCount> tempList= new ArrayList<>();
//                for(String key:wordCount.keySet()){
//                    int value = wordCount.get(key);
//                    System.out.println(key+", "+value);
//                    tempList.add(new WordCount(key, value));
//                }
//                Collections.sort(tempList, new Comparator<WordCount>() {
//                    @Override
//                    public int compare(WordCount o1, WordCount o2) {
//                        if(o1.count < o2.count) return 1;
//                        else if(o1.count > o2.count) return -1;
//                        else return 0;
//                    }
//                });
//                // getting the top 20 words;
//                for(int j = 0; j < tempList.size() && j < 20; j++){
//                    top20Words.get(i).add(tempList.get(j).word);
//                }
//                // getting the top 20 word counts;
//                for(int j = 0; j < tempList.size() && j < 20; j++){
//                    top20WordCounts.get(i).add(tempList.get(j).count);
//                }
//
//
//            }
//        }catch (FileNotFoundException e){
//            System.out.println("error");
//        }
//        System.out.println("total_files in the directory: "+total_files);
//        System.out.println("\nTotal Lines, Words & Characters in Each File: ");
//        for(int i = 0; i < file_lines_count.length; i++){
//            System.out.println("Lines in File "+(i+1)+": "+file_lines_count[i]);
//            System.out.println("Words in File "+(i+1)+": "+file_words_count[i]);
//            System.out.println("Characters in File "+(i+1)+"(including whitespaces): "+file_character_count[i]);
//        }
//
//        System.out.println();
//        System.out.println("Top 20 words in each file with count: ");
//        for(int i = 0; i < top20Words.size(); i++){
//            System.out.println("File "+(i+1));
//            for(int j = 0; j < top20Words.get(i).size(); j++){
//                System.out.println(top20Words.get(i).get(j)+" : "+top20WordCounts.get(i).get(j));
//            }
//            System.out.println();
//        }
//        System.out.println("Scanning directory: " + directoryPath + '\n');

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


//Purpose
//In this exercise, you will write a command line program that will scan files and produce text file statistics (e.g. line counts, etc.).   A console application shell will be provided as a starting point.
//You can search the web to look up Java library usage, but please do not use any AI coding assistance.
//
//Details
//The program should read all files in the given directory and all subdirectories.  For each file, it should read and count the following statistics:
//Total files read
//Total line count (includes empty lines)
//Total word count (use the regex word definition [a-zA-Z0-9_] and words reside completely on a given line)
//Total character count (including whitespace)
//Top 20 words by count, including the number of occurrences for each word (case insensitive)
//Input
//A single command line parameter that is a directory path to search.
//Output
//A console output report that contains each of the statistics listed above, once for each file and then a summary with combined counts across all files.  The output format is up to you, but it should look clean (e.g. columns aligned).
//Test Data
//Some text files are included in the given project in the ‘data’ subdirectory.  The project is already set up to pass in this directory as the program parameter when debugging.
//
//        Hints
//Focus on the simple stats first (e.g. lines, words) and then move on to the harder parts.
//As for help as needed.
//