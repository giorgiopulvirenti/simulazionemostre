package it.polito.tdp.artsmia.db;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;

public class Exhibitions {
	
int id;
String department;
String title;
int begin;
int end;
List <ArtObject> objects=new ArrayList<ArtObject>();
public Exhibitions(int id, String department, String title, int begin, int end) {
	super();
	this.id = id;
	this.department = department;
	this.title = title;
	this.begin = begin;
	this.end = end;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getBegin() {
	return begin;
}
public void setBegin(int begin) {
	this.begin = begin;
}
public int getEnd() {
	return end;
}
public void setEnd(int end) {
	this.end = end;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Exhibitions other = (Exhibitions) obj;
	if (id != other.id)
		return false;
	return true;
}
@Override
public String toString() {
	return "Exhibitions [title=" + title + "]";
}

public void addObject(ArtObject a){
	if(!objects.contains(a))
	this.objects.add(a);
}
public List<ArtObject> getObjects() {
	return objects;
}

}
