package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message = "First Name filed is necessary.")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name filed is necessary.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email is necessary.")
    @Email(message = "Email is invalid.")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Password is necessary.")
    @Length(min = 8, message = "Minimum length is 8 characters.")
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_table_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Set<Application> applications;
}
