package pl.mateuszharazin.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "test_type")
public class TestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_type_id")
    private int id;

    @NotNull
    @Unique
    @Column(name = "test_type_name")
    private String testTypeName;

    @NotNull
    @Column(name = "test_type_desc")
    private String testTypeDesc;

    @OneToMany(mappedBy = "testType", cascade = CascadeType.ALL)
    private Set<Test> tests;

    @OneToMany(mappedBy = "testType", cascade = CascadeType.ALL)
    private Set<Application> applications;

}
