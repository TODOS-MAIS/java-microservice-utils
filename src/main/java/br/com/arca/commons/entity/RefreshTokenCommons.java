package br.com.arca.commons.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "RefreshTokenCommons")
@Table(name = "REFRESH_TOKEN")
public class RefreshTokenCommons extends RefreshTokenBase {
}
