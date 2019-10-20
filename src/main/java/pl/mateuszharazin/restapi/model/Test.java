package pl.mateuszharazin.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "test")
public class Test {


    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_test", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

    @OneToOne(mappedBy = "application")
    private ApplicationTest applicationTest;

    public Test(String name) {
        this.name = name;
    }

    public Test(String name, TestType testType) {
        this.name = name;
        this.testType = testType;
    }


}
