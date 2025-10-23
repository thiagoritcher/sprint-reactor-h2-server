package br.com.ritcher.server.lib;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Model<T> implements Persistable<T>{


	@Transient
	private boolean unpersisted = false;
	
	public Model<T> setNew() {
		this.unpersisted = true;
		return this;
	}
	
	@Override
	@JsonIgnore
	public boolean isNew() {
		return unpersisted;
	}

}
