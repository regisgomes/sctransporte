package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.GenericModel;

public class Funcionario extends GenericModel{

	 @Id
	 @GeneratedValue
	 public Long id;

	 public Long getId() {
	    return id;
	 }

	 @Override
	 public Object _key() {
	    return getId();
	 }
}
