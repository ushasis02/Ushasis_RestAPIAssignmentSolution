package com.gl.EmpMgm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude="users")
@ToString(exclude="users")
public class Role {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int roleId;
	
	private String roleName;
	
	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn (name="role_Id"),
			inverseJoinColumns = @JoinColumn (name = "user_Id")
			)
	@JsonIgnore
	private Set<User> users;
	
	public Set<User> getUsers(){
		if(this.users == null) {
			this.users = new HashSet<>();
		}
		return this.users;
	}
	
}
