package com.decagon.interview.stageone.api;

import com.decagon.interview.stageone.model.Datum;
import com.decagon.interview.stageone.model.UserArticle;
import com.google.gson.Gson;
import org.apache.catalina.User;

import java.net.*;
import java.io.*;
import java.util.*;

public class ApiService {

    private static String API_URL = "https://jsonmock.hackerrank.com/api/article_users/search?page=";

    /*
     *This function would retrieve the names of the most active authors(using submission_count as the criteria)
     *according to a set threshold.
     *
     * */
    public static List<String> getUsernames(int threshold) {

        UserArticle userArticle = articles(String.valueOf(threshold));

        Map<Datum, Long> hashMap = mostActiveUsers(userArticle);
        List<String> userList = new LinkedList<>();

        for (Map.Entry<Datum, Long> m : hashMap.entrySet()) {
            userList.add("Username : " + m.getKey().getUsername() + ", total submission count : " + m.getValue() + "\n\n");
        }
        return userList;
    }
    /*
     *This function would retrieve the name of the author with the highest comment count.
     * */
    public static String getUsernameWithHighestCommentCount(int pageId){

        UserArticle userArticle = articles(String.valueOf(pageId));
        String output = userArticle.getData().get(0).getUsername();
        long maxComment = userArticle.getData().get(0).getCommentCount();

        for(int i = 1; i < userArticle.getData().size(); i++){

            if(maxComment < userArticle.getData().get(i).getCommentCount()){
                maxComment = userArticle.getData().get(i).getCommentCount();
                output = userArticle.getData().get(i).getUsername();
            }
        }
        return "Username : "+output+" , made the highest comment with a total of "+maxComment;
    }
    /*
     *The list of the authors sorted by when their record was created according to a set threshold.
     **/

    public static List<Datum> getUsernamesSortedByRecordDate(int threshold) {
        List<Datum> userList = articles(String.valueOf(1)).getData();
        userList.sort(Comparator.comparing(Datum::getCreatedAt));

        return userList;
    }

    /*
     * return the HashTable of active users by submmsion_count
     *
     * */
    private static Map<Datum, Long> mostActiveUsers(UserArticle userArticle) {
        HashMap<Datum, Long> userTable = new HashMap<>();

        for (Datum userDetails : userArticle.getData()) {

            if (userTable.containsKey(userDetails)) {
                long submissionCounter = userTable.get(userDetails);
                userTable.put(userDetails, submissionCounter + userDetails.getSubmissionCount());
            } else {
                userTable.put(userDetails, userDetails.getSubmissionCount());
            }
        }
        return sortByValue(userTable);
    }
    //Get this list of the aritcles by page
    public static UserArticle articles(String pageId) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(API_URL + pageId + "");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserArticle userArticle = new Gson().fromJson(sb.toString(), UserArticle.class);

        return userArticle;
    }
    /*
     * Sort Map by Value
     * */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
