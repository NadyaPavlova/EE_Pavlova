package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="User")
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUser")
    @SequenceGenerator(name="idUser", sequenceName = "seq_user")
    @Column(name="idUser")
    private Long idUser;

    @Column(name = "login")
    private String  login;

    @Column(name = "password")
    private String password;

    @Column (name = "lastName")
    private String lastName;

    @Column (name = "firstName")
    private String firstName;

    @Column (name = "middleName")
    private String middleName;

    @Column (name = "email")
    private String email;

    @Column (name = "phoneNumber")
    private String phoneNumber;

    @Column (name = "money")
    private BigDecimal money;

    @Column (name = "discount")
    private Integer discount;

    @Column (name = "admin")
    private Integer admin;


    public User(){
        money = new BigDecimal(3000);
        discount = 3;
        admin = 0;
    }

    public User(String email, String password , String firstName, String middleName, String lastName, String phoneNumber){
        this.login = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        money = new BigDecimal("2000");
        discount = 3;
        admin = 0;

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer isAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getAdmin() {
        return admin;
    }
}
