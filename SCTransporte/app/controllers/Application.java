package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void cadastroVeiculo(String login, String pwd) {
    	
    	if (login == "rafael" && pwd == "123")
    		render();
    	
    }
    
}