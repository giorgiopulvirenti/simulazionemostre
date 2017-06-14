package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Studente implements Comparable<Studente>{
	int i;
		List<ArtObject> liosta= new ArrayList<ArtObject>();
		
		
		
		
		
		@Override
		public String toString() {
			return "Studente [i=" + i + ", numero=" + liosta.size() + "]";
		}
		public Studente(int i) {
			super();
			this.i = i;
		}
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public List<ArtObject> getLiosta() {
			return liosta;
		}
		public void setLiosta(List<ArtObject> liosta) {
			this.liosta = liosta;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
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
			Studente other = (Studente) obj;
			if (i != other.i)
				return false;
			return true;
		}
		@Override
		public int compareTo(Studente o) {
			
			return o.liosta.size()-this.liosta.size();
		}
		

}
