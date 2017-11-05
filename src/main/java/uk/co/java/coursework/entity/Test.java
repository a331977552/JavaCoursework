package uk.co.java.coursework.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
				@NamedQuery(name=Test.FIND_ALL,query="SELECT t FROM Test t")
				,
				})

@XmlRootElement
@Table(name="test")
public class Test implements  Serializable{
	  private static final long serialVersionUID = 249872301293L;
	  	
	  public static final String FIND_ALL="FIND_ALL";
	  	@Id
	  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	  	
	  	
	  	@NotNull
	  	@Size(min=1,max=25)
	  	@Pattern(regexp = "[A-Za-z-']+", message = "Please use a name without numbers or specials")
	  	@Column(name="state")
	    private String state;
		@Column(name="abbr")
	    private String abbr;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public String getAbbr() {
	        return abbr;
	    }

	    public void setAbbr(String abbr) {
	        this.abbr = abbr;
	    }
}
