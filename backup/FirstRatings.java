package com.company.backup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 在这里给出对类 FirstRatings 的描述。
 *
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class FirstRatings
{
    public ArrayList<Movie> loadMovies(String filename) throws IOException{
        ArrayList<Movie> myMoviesList = new ArrayList<Movie>();


        File csvData = new File(filename);
        //Reader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);

        CSVParser parser;

        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
//        List<CSVRecord> noHeadersLine = parser.stream.skip(1).collect(toList());

//        List<CSVRecord> noHeadersLine = parser.getRecords().stream.skip(1).collect(toList());
        for (CSVRecord record : parser.getRecords().stream()
                .filter(record -> record.getRecordNumber() != 1)
                .collect(Collectors.toList())) {

//            List<CSVRecord> noHeadersLine = records.stream.skip(1).collect(toList());

            String id = record.get(0);
            String title = record.get(1);
            String year = record.get(2);
            String country = record.get(3);
            String genre = record.get(4);
            String director = record.get(5);
            int minutes = 0;
            if(record.get(6).length() > 0){
                minutes = Integer.valueOf(record.get(6));
            }

            String poster = record.get(7);

            Movie movie = new Movie(id, title, year, genre, director,
                     country,  poster, minutes);

//            System.out.println("========movie to string==================");
//
//            System.out.println(movie.toString());
////            System.out.println(movie.toString);
//            System.out.println(movie);
//            System.out.println("========movie to string===end===============");
            myMoviesList.add(movie);

        }

        return myMoviesList;
    }

    public ArrayList<Rater> loadRaters(String filename) throws IOException{
        ArrayList<Rater> myRaterList = new ArrayList<Rater>();

        Map<String, ArrayList<Rating>> ratorRatings= new HashMap<String,ArrayList<Rating>>();


        File csvData = new File(filename);
        //Reader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);

        CSVParser parser;

        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
//        List<CSVRecord> noHeadersLine = parser.stream.skip(1).collect(toList());

//        List<CSVRecord> noHeadersLine = parser.getRecords().stream.skip(1).collect(toList());
        for (CSVRecord record : parser.getRecords().stream()
                .filter(record -> record.getRecordNumber() != 1)
                .collect(Collectors.toList())) {

//            List<CSVRecord> noHeadersLine = records.stream.skip(1).collect(toList());

            String id = record.get(0);
            String movieId = record.get(1);
            double rating = Double.valueOf(record.get(2));
            String time = record.get(3);

            Rating myRating = new Rating(movieId, rating);

            if(ratorRatings.containsKey(id)){
                ArrayList<Rating> currentRatings = ratorRatings.get(id);
                currentRatings.add(myRating);

                ratorRatings.put(id, currentRatings);

            }else{
                ArrayList<Rating> currentRatings = new ArrayList<Rating>();
                currentRatings.add(myRating);

                ratorRatings.put(id, currentRatings);

            }



        }

        List<Rating> myRatings = new ArrayList<Rating>();

        for(Map.Entry<String, ArrayList<Rating>> entry: ratorRatings.entrySet()){
            String ratorId = entry.getKey();
            ArrayList<Rating> myRating = entry.getValue();

            Rater rater = new Rater(ratorId);
            for(Rating rating: myRating){
                rater.addRating(rating.getItem(), rating.getValue());
            }
            myRaterList.add(rater);

        }

        return myRaterList;
    }

    public void testLoadMovies() throws IOException {

        Map<String, Integer> directMovieCount = new HashMap<String, Integer>();
        ArrayList<Movie> myMoviesList = new ArrayList<Movie>();
//        String fileName = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmovies_short.csv";
        String fileName = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmoviesfull.csv";
        myMoviesList = loadMovies(fileName);

        int comdyCount = 0;
        int mLongCount = 0;

        System.out.println("=========movie count===========");

        System.out.println(myMoviesList.size());


        for(Movie movie: myMoviesList){
//            System.out.println(movie.toString());
            String genre = movie.getGenres();
            String directorList = movie.getDirector();
            String[] directors = directorList.split(",");
            int minutes = movie.getMinutes();

            if(genre.contains("Comedy")){
                comdyCount++;
            }
            if(minutes > 150){
                mLongCount++;
            }
            for(String director: directors){

                String stripDirector = director.trim();
                int count = directMovieCount.containsKey(stripDirector) ? directMovieCount.get(stripDirector) : 0;
                directMovieCount.put(stripDirector, count + 1);

            }

        }
        System.out.println("----------comedy count: " + String.valueOf(comdyCount));
        System.out.println("------long movie---- count: " + String.valueOf(mLongCount));
        System.out.println("------count of movies---- count: " + String.valueOf(directMovieCount));

        int max = Collections.max(directMovieCount.values());
        List<String> directorNames = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: directMovieCount.entrySet()){
            if(entry.getValue() == max){
                directorNames.add(entry.getKey());
            }
        }
        for(String directName: directorNames){
            System.out.println("------------name of directors who direct max movies: " + directName);
        }
        System.out.println("------------maximum number of movies by any director " + String.valueOf(max));

    }

    public void testLoadRaters() throws IOException {

        Map<String, Integer> directMovieCount = new HashMap<String, Integer>();
        ArrayList<Rater> myRatersList = new ArrayList<Rater>();
//        String fileName = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratedmovies_short.csv";
        String fileName = "/Users/yi/Downloads/DukeRecommander/src/com/company/data/ratings.csv";
        myRatersList = loadRaters(fileName);

        int comdyCount = 0;
        int mLongCount = 0;

        int numOfRatingsForMovie = 0;
        String specialMovieId = "1798709";

        System.out.println(myRatersList.size());

        String raterId = "193";

        List<Integer> ratignsCount = new ArrayList<Integer>();
        List<String> ItemsratignsCount = new ArrayList<String>();

        Set<String> differentMovies = new HashSet<>();


        for(Rater rater: myRatersList){
//            System.out.println(movie.toString());
            String id = rater.getID();
            int numRatings = rater.numRatings();
            ratignsCount.add(numRatings);

            if(id.equals(raterId)){
                System.out.println("rater id " + id + " number of rating " + String.valueOf(numRatings));
            }



            List<String> itemList = rater.getItemsRated();
            for(String item: itemList){

//                System.out.println("rating movie item id " + item);
//                System.out.println("rating given  " + String.valueOf(rater.getRating(item)));
                if(specialMovieId.equals(item)){
                    numOfRatingsForMovie++;
                }
                differentMovies.add(item);



            }

        }
        int maxRatingNum = Collections.max(ratignsCount);

        for(Rater raterCount: myRatersList){
            String idCount = raterCount.getID();
            int numRatingsCount = raterCount.numRatings();
            if(numRatingsCount == maxRatingNum){
                System.out.println("----with--max numer of rating: " + String.valueOf(maxRatingNum));
                System.out.println("----with--max numer of rating, the rater id is " + idCount);
                ItemsratignsCount.add(idCount);
            }


        }
        System.out.println("----------how many raters have this maximum number of ratings : " + String.valueOf(ItemsratignsCount.size()));
        System.out.println("---the number of ratings a particular movie----" + String.valueOf(numOfRatingsForMovie));
        System.out.println("----------how many different movies have been rated by all these raters : " + String.valueOf(differentMovies.size()));



    }
    // 实例变量 - 用你自己的变量替换下面的例子
    private int x;

    /**
     * 类 FirstRatings 的对象的构造函数
     */
    public FirstRatings()
    {
        // 初始化实例变量
        x = 0;
    }

    /**
     * 一个方法的例子 - 使用你自己的说明替代它
     *
     * @参数 y，方法的一个样本参数
     * @返回 x，y的和
     */
    public int sampleMethod(int y)
    {
        // 在这里加入你的代码
        return x + y;
    }
}

