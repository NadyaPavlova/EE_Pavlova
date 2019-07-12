package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.access.user.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity()
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name="id", sequenceName = "seq_user")
    private Long id_user;

    @Column(name = "LOGIN")
    private String  login;

    @Column(name = "PASSWORD")
    private String password;

    @Column (name = "LAST_NAME")
    private String lastName;

    @Column (name = "FIRST_NAME")
    private String firstName;

    @Column (name = "MIDDLE_NAME")
    private String middleName;

    @Column (name = "EMAIL")
    private String email;

    @Column (name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column (name = "MONEY")
    private BigDecimal money;

    @Column (name = "DISCOUNT")
    private Integer discount;

    @Column (name = "ADMIN")
    private Integer admin;


    public User(){
        money = new BigDecimal(2000);
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

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Integer getAdmin() {
        return admin;
    }
}
