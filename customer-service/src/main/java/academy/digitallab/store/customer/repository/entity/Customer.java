package academy.digitallab.store.customer.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name="tbl_customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El número de cédula no puede ser vacío")
    @Size( min = 10 , max = 10, message = "Des ingresar los 10 dígitos de tu cédula")
    @Column(name = "number_id" , unique = true ,length = 10, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Column(name="last_name", nullable=false)
    private String lastName;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "no es un dirección de correo válida")
    @Column(unique=true, nullable=false)
    private String email;

    @Column(name="photo_url")
    private String photoUrl;


    @NotNull(message = "La región no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    @NotNull(message = "El campo no puede estar vacío")
    @JoinColumn(name = "blood_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Blood blood;

    private String state;
}
