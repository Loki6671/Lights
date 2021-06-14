package com.project.task.dao;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.project.task.models.Room;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebInitParam;
import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DaoRooms {
    public ArrayList<Room> rooms= new ArrayList<>();
    private static int count=0;
    public String location(String ip) throws IOException, GeoIp2Exception {

        String dbLoc="GeoLite2-City_20210608\\GeoLite2-City.mmdb";
        File db=new File(dbLoc);
        DatabaseReader dbr= new DatabaseReader.Builder(db).build();
        InetAddress ipA= InetAddress.getByName(ip);
        CityResponse response= dbr.city(ipA);
        String country = response.getCountry().getName();

        return country;



    }


    public void init(){
        try {

                FileInputStream fis= new FileInputStream("rooms.bin");
                ObjectInputStream ois= new ObjectInputStream(fis);
                ArrayList<Room> rooms1=(ArrayList<Room>) ois.readObject();
                rooms=rooms1;
                count=rooms1.size();


            }catch(IOException e ){
            System.out.println("У нас первый пользователь!");

            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        public  void destroy(List<Room> rooms){
            try {
                FileOutputStream fos= new FileOutputStream("rooms.bin");
                ObjectOutputStream oos= new ObjectOutputStream(fos);
                oos.writeObject(rooms);

            }catch (IOException e){
                e.printStackTrace();

            }
        }
        public List<Room> showRooms(){
            init();
            destroy(rooms);
            return rooms;

        }
        public Room  showID(int id){
            init();
//          return rooms.stream().filter(room -> room.getId()==id ).filter(room -> room.getCountry()==ip).findAny().orElse( null);
            return rooms.stream().filter(room -> room.getId()==id ).findAny().orElse( null);
        }
        public void save(Room room) {
            init();
            room.setId(++count);
            room.setLight("off");
            rooms.add(room);
            destroy(rooms);
    }
    public void  changeState(String id){
        init();

        int num = Integer.parseInt(id);

        if(rooms.get((num-1)).isLight().equals("off")){
            rooms.get((num-1)).setLight("onn");
            System.out.println("+");
        }
        else {
            rooms.get(num-1).setLight("off");
        }

        destroy(rooms);
    }
    public boolean checkCountry(String ip, int id) throws IOException, GeoIp2Exception {
        init();
        String userCountry=location(ip);
        Room selectRoom=rooms.get(id-1);
        if (userCountry.equals(selectRoom.getCountry())){
            return  true;
        }
        else {
            return  false;
        }
    }



}


