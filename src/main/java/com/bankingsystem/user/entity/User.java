package com.bankingsystem.user.entity;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long userId;

 @Column(nullable = false)
 private String name;

 @Column(nullable = false, unique = true)
 private String email;

 @Column(nullable = false, unique = true)
 private String phone;

 @Column(nullable = false)
 private String password;

 @Enumerated(EnumType.STRING)
 @Column(nullable = false)
 private Role role;

 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
 private Account account;
}
