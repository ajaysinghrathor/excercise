package com.excercise.feed.service;

import com.excercise.feed.entity.Portfolio;
import com.excercise.feed.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("/data")
public class DataService {

    @Autowired private PortfolioRepository repository;

    @GET
    @Path("/feeds")
    @Produces(MediaType.APPLICATION_JSON)
    public Response feeds() {
        int status = 200;
        List<Portfolio> data = null;
        try{
            data = repository.findAll();
            //data = data.subList(0,100);
            if(data.isEmpty()){
                status = 201;
            }
        }catch (Exception e){
            status = 500;
            data = new ArrayList<>();
        }
        return Response
                .status(status)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(data)
                .build();
    }
}
