package com.todolist.domain;

import com.todolist.domain.enums.SocialType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author choijaeyong on 25/03/2019.
 * @project todolist
 * @description
 */

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long idx;

  // Unique 할까 말까.
  @Id
  private String email;

  private String name;

  @ManyToMany
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name="user_email", referencedColumnName = "email"),
    inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
  private Set<Role> roleSet = new HashSet<>();

  @OneToMany(mappedBy = "user")
  private Set<Board> boardSet = new HashSet<>();


  // 시큐리티 때문에 추가
  private String password;

  private String principal;

  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  private LocalDateTime createdDate;
  private LocalDateTime updateDate;

  @Builder
  public User(String email, String name, String password, String principal,
      SocialType socialType, LocalDateTime createdDate, LocalDateTime updateDate) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.principal = principal;
    this.socialType = socialType;
    this.createdDate = createdDate;
    this.updateDate = updateDate;
  }
}
