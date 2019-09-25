package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor

@Entity
@Table(name = "userRole")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRoleId")
    private int id;

    @Column(name = "userRoleName")
    private String roleName;

    @Column(name = "userRoleDesc")
    private String roleDesc;
}
