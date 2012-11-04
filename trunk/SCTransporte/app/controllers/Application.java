package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        List<Usuario> usuarios = Usuario.all().fetch();
        render(usuarios);
    }
    
    public static void cadastroVeiculo() {
    	render();
    }

}