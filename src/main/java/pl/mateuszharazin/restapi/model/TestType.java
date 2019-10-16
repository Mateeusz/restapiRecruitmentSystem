//package pl.mateuszharazin.restapi.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//
//@Entity
//@Table(name = "test_type")
//public class TestType {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "test_type_id")
//    int id;
//
//    @NotNull
//    @Column(name = "test_type_name")
//    String testTypeName;
//
//    @NotNull
//    @Column(name = "test_type_desc")
//    String testTypeDesc;
////
////    @OneToOne(mappedBy = "testType")
////    private Application application;
//
//}
