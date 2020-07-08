package com.unsa.security;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.unsa.security.UserPermission.*;
public enum UserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE));
	
	private final Set<UserPermission> permissions;

	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<UserPermission> getPermissions() {
		return permissions;
	}
}
