package it.unisa.cineverse.model.bean;

import java.util.ArrayList;
import java.util.List;

public class SaleBean 
{
	private int id;
	private int posti_totali;
	private String screen_type;
	
	private List<PostiBean> posto;
	private List<ProiezioneBean> proiezioni;
	
	public SaleBean()
	{
		posto = new ArrayList<PostiBean>();
		proiezioni = new ArrayList<ProiezioneBean>();
	}
	
}
