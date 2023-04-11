package org.example;
import kong.unirest.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        //Movies with avengers
        HttpResponse<String> response = Unirest.get("https://online-movie-database.p.rapidapi.com/auto-complete?q=avengers")
                .header("X-RapidAPI-Key", "77bff848fdmsh09570607c4cdcaap1922b9jsn605c84b5fb05")
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .asString();

        JSONObject data = new JSONObject(response.getBody());
        JSONArray movies = new JSONArray(data.getJSONArray("d"));

        System.out.println("Here are all movies with (avengers) in the title: ");
        for(int i = 0; i < movies.length(); i++)
        {
            JSONObject movie = movies.getJSONObject(i);
            String name = movie.getString("l");
            System.out.println(i+1 + ": " + name);
        }

        //Actors Birthdays
        HttpResponse<String> response2 = Unirest.get("https://online-movie-database.p.rapidapi.com/actors/list-born-today?month=7&day=27")
                .header("X-RapidAPI-Key", "061ca52d77msh5e26400ebea86fap190197jsn90bcbc170947")
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .asString();

        JSONArray actors = new JSONArray(response2.getBody());

        System.out.println("Actors whos birthdays are today: ");
        for(int j = 0; j < actors.length(); j++)
        {
            System.out.println(actors.get(j));
        }

        //find movies where actor at index 1 participated in
        String actor = actors.getString(1).substring(6,15);
        System.out.println("Movies actor in second position (" + actor + ") is in :");

        HttpResponse<String> response3 = Unirest.get("https://online-movie-database.p.rapidapi.com/actors/get-all-filmography?nconst="+actor)
                .header("X-RapidAPI-Key", "061ca52d77msh5e26400ebea86fap190197jsn90bcbc170947")
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .asString();

        JSONObject d = new JSONObject(response3.getBody());
        JSONArray actorMovies = new JSONArray(d.getJSONArray("filmography"));
        for(int k = 0; k < actorMovies.length(); k++)
        {
            JSONObject film = actorMovies.getJSONObject(k);
            System.out.println(k+1 +": " + film.getString("title"));
        }

        //get cast for avengers secret wars
        System.out.println("Cast for Avengers: Secret Wars is: " + movies.getJSONObject(4).getString("s"));




    }
}