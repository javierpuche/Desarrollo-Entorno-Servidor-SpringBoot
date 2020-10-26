package des.springboot_hibernate.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name = "PROFESOR")
public class Profesor implements Serializable {


	private static final long serialVersionUID = -8668594760203621162L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ID_PROFESOR")
	private Long idProfesor;
	
	@Column(name = "NICKNAME")
	private String username;

	@Column(name = "NOMBRE")
	private String nombreProfesor;

	@Column(name = "APELLIDOS")
	private String apellidosProfesor;
	
	@OneToOne(mappedBy="profesor" ,fetch = FetchType.LAZY,optional= true)
	private Imagen imagen;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Email> emails = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROFESOR_MODULO", 
	joinColumns = @JoinColumn(name = "ID_PROFESOR"),
	inverseJoinColumns = @JoinColumn(name = "ID_MODULO"))
	private Set<Modulo> modulos = new HashSet<>();

	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public boolean addEmails(Email email) {
		email.setProfesor(this);
		return getEmails().add(email);
	}

	public void removeEmails(Email email) {
		getEmails().remove(email);
	}
	
	public boolean addModulo(Email email) {
		email.setProfesor(this);
		return getEmails().add(email);
	}

	public void removeModulo(Modulo modulo) {
		this.modulos.remove(modulo);
		modulo.getProfesores().remove(this);
	}
//		public void removeBooks() {
//		Iterator<Book> iterator = this.books.iterator();
//		while (iterator.hasNext()) {
//		Book book = iterator.next();
//		book.getAuthors().remove(this);
//		iterator.remove();
//		}
//		}
//	

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public String getApellidosProfesor() {
		return apellidosProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public void setApellidosProfesor(String apellidosProfesor) {
		this.apellidosProfesor = apellidosProfesor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidosProfesor == null) ? 0 : apellidosProfesor.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + ((idProfesor == null) ? 0 : idProfesor.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((modulos == null) ? 0 : modulos.hashCode());
		result = prime * result + ((nombreProfesor == null) ? 0 : nombreProfesor.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Profesor other = (Profesor) obj;
		if (apellidosProfesor == null) {
			if (other.apellidosProfesor != null)
				return false;
		} else if (!apellidosProfesor.equals(other.apellidosProfesor))
			return false;
		if (emails == null) {
			if (other.emails != null)
				return false;
		} else if (!emails.equals(other.emails))
			return false;
		if (idProfesor == null) {
			if (other.idProfesor != null)
				return false;
		} else if (!idProfesor.equals(other.idProfesor))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (modulos == null) {
			if (other.modulos != null)
				return false;
		} else if (!modulos.equals(other.modulos))
			return false;
		if (nombreProfesor == null) {
			if (other.nombreProfesor != null)
				return false;
		} else if (!nombreProfesor.equals(other.nombreProfesor))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", username=" + username + ", nombreProfesor=" + nombreProfesor
				+ ", apellidosProfesor=" + apellidosProfesor + ", imagen=" + imagen + ", emails=" + emails
				+ ", modulos=" + modulos + "]";
	}
	
}