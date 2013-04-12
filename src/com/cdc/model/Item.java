package com.cdc.model;

public class Item {
	
	private String title;
	private String names;
	private String types;
	private String lengths;
	private String view;
	private String req;
	private String prop;
	private String form;
	
	public Item() {
		
	}

	public Item(String names, String types, String lengths) {
		super();
		this.names = names;
		this.types = types;
		this.lengths = lengths;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getLengths() {
		return lengths;
	}

	public void setLengths(String lengths) {
		this.lengths = lengths;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
}
