package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void menu(String login, String pwd) {
    	Usuario user = Usuario.find("login", login).first();
    	if(user != null && user.getPass().equals(pwd)){
    		render(user);
    	}
    	else{
    		index();
    	}
    }

}